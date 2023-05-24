package com.magic.officeapp.ui.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.data.model.response.AttendanceResponseDataItem
import com.magic.officeapp.data.model.response.LocationResponse
import com.magic.officeapp.data.model.response.UserListResponseItem
import com.magic.officeapp.data.repository.AttendanceRepository
import com.magic.officeapp.utils.Attendance
import com.magic.officeapp.utils.attendanceSummary
import com.magic.officeapp.utils.attendanceSummaryHR
import com.magic.officeapp.utils.constants.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val repository: AttendanceRepository
) : ViewModel() {

    private val _attendanceSummary = MutableStateFlow(Attendance(0, 0, 0, 0, 0, 0, 0))
    val attendanceSummary get() = _attendanceSummary

    private val _todayAttendance = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val todayAttendance get() = _todayAttendance

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading

    private val _location = MutableStateFlow<Result<LocationResponse>>(Result.Empty)
    val location get() = _location

    private val _insertAttendance = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val insertAttendance get() = _insertAttendance

    private val _attendanceState = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val attendanceState get() = _attendanceState

    private val _allAttendanceState = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val allAttendanceState get() = _allAttendanceState

    private val _allAttendanceData = MutableStateFlow(emptyList<AttendanceResponseDataItem>())
    val allAttendanceData get() = _allAttendanceData

    private val _allAttendanceSummary = MutableStateFlow(Attendance(0, 0, 0, 0, 0, 0, 0))
    val allAttendanceSummary get() = _allAttendanceSummary

    private val _checkoutAttendance = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val checkoutAttendance get() = _checkoutAttendance

    private val _attendanceUserByDate = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val attendanceUserByDate get() = _attendanceUserByDate

    init {
        getLocation()
    }

    private fun getLocation() {
        viewModelScope.launch {
            _loading.value = true
            _location.value = repository.getLocation()
            _loading.value = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllAttendance(listEmployee: List<UserListResponseItem>) {
        viewModelScope.launch {
            _loading.value = true
            var data = repository.getAllAttendance()
            _allAttendanceState.value = data

            if (data is Result.Success) {
                _allAttendanceData.value = data.data.data as List<AttendanceResponseDataItem>
                val data = attendanceSummaryHR(
                    listEmployee,
                    data.data.data as List<AttendanceResponseDataItem>
                )
                val tempSummary = Attendance(0, 0, 0, 0, 0, 0, 0)
                data.forEach {
                    tempSummary.id = 0
                    tempSummary.present += it.present
                    tempSummary.absent += it.absent
                    tempSummary.late += it.late
                    tempSummary.early += it.early
                    tempSummary.overtime += it.overtime
                }
                _allAttendanceSummary.value = tempSummary
            }

            _loading.value = false
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getAttendanceUser(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val attendance = repository.getAttendanceUser(userId)
                _attendanceState.value = attendance

                if (attendance is Result.Success) {
                    getSummaryAttendance(attendance.data.data as List<AttendanceResponseDataItem>)
                }

                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                _attendanceState.value = Result.Error(e.message.toString())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSummaryAttendance(listAttendance: List<AttendanceResponseDataItem>) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val attendance = attendanceSummary(listAttendance)
                _attendanceSummary.value = attendance
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                _attendanceState.value = Result.Error(e.message.toString())
            }
        }
    }

    fun getAttendanceUserByDate(userId: String, startDate: String, endDate: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val attendance = repository.getAttendanceUserByRange(userId, startDate, endDate)
                _attendanceUserByDate.value = attendance
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                _attendanceUserByDate.value = Result.Error(e.message.toString())
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getAttendanceToday(userId: String) {
        val formatDate = SimpleDateFormat("yyyy-MM-dd")
        formatDate.timeZone = TimeZone.getTimeZone("UTC")

        val currentDate = formatDate.format(Date())
        val nextDate = formatDate.format(System.currentTimeMillis() + 86400000)

        viewModelScope.launch {
            _loading.value = true
            try {
                val attendance = repository.getAttendanceUserByRange(userId, currentDate, nextDate)
                _todayAttendance.value = attendance
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                _todayAttendance.value = Result.Error(e.message.toString())
            }
        }
    }

    fun checkOutAttendance(id: String) {
        viewModelScope.launch {
            val utc = TimeZone.getTimeZone("UTC")
            val calendar = Calendar.getInstance(utc)
            val millis = calendar.timeInMillis

            _loading.value = true
            try {
                _checkoutAttendance.value = repository.checkOutAttendance(id, millis.toString())
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
            }
        }
    }

    fun insertAttendance(userId: String, latitude: String, longitude: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.insertAttendance(userId, latitude, longitude)
                getAttendanceToday(userId)

                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
            }
        }
    }

}