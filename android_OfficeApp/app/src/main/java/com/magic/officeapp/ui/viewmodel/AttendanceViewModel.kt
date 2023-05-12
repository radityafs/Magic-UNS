package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.data.model.response.LocationResponse
import com.magic.officeapp.data.repository.AttendanceRepository
import com.magic.officeapp.data.repository.AuthRepository
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

    private val _attendanceState = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val attendanceState get() = _attendanceState

    private val _todayAttendance = MutableStateFlow<Result<AttendanceResponse>>(Result.Empty)
    val todayAttendance get() = _todayAttendance

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading

    private val _location = MutableStateFlow<Result<LocationResponse>>(Result.Empty)
    val location get() = _location

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

    fun getAttendanceUser(userId: String){
        viewModelScope.launch {
            _loading.value = true
            try {
                val attendance = repository.getAttendanceUser(userId)
                _attendanceState.value = attendance
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                _attendanceState.value = Result.Error(e.message.toString())
            }
        }
    }

    fun getAttendanceToday(userId: String) {
        val formatDate = SimpleDateFormat("yyyy-MM-dd")
        formatDate.timeZone = TimeZone.getTimeZone("UTC")

        val currentDate = formatDate.format(Date())
        val nextDate = formatDate.format(System.currentTimeMillis() + 86400000)

        viewModelScope.launch {
            _loading.value = true
            try {
                val attendance = repository.getAttendanceToday(userId, currentDate, nextDate)
                _todayAttendance.value = attendance
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
                _todayAttendance.value = Result.Error(e.message.toString())
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