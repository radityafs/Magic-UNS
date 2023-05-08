package com.magic.officeapp.data.repository

import com.magic.officeapp.data.api.ApiAttendanceInterface
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.utils.constants.Result
import javax.inject.Inject

class AttendanceRepository @Inject constructor(
    private val apiService: ApiAttendanceInterface
) {

    suspend fun getAttendanceToday(
        userId : String,
        startDate: String,
        endDate: String
    ): Result<List<AttendanceResponse>> {
        return try {
            Result.Success(apiService.getAttendanceByRange(userId, startDate, endDate))
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }


}