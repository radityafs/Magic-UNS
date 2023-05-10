package com.magic.officeapp.data.repository

import com.magic.officeapp.data.api.ApiAttendanceInterface
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.request.AttendanceRequest
import com.magic.officeapp.data.model.request.DataAttendanceRequest
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.data.model.response.LocationResponse
import com.magic.officeapp.utils.constants.Result
import javax.inject.Inject

class AttendanceRepository @Inject constructor(
    private val apiService: ApiAttendanceInterface
) {
    suspend fun getAttendanceToday(
        userId: String, startDate: String, endDate: String
    ): Result<AttendanceResponse> {
        return try {
            Result.Success(
                apiService.getAttendanceByRange(
                    userId = userId, startDate = startDate, endDate = endDate
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getLocation(): Result<LocationResponse> {
        return try {
            Result.Success(apiService.getApiLocation())
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun insertAttendance(
        userId: String,
        latitude: String,
        longitude: String,
    ): Result<AttendanceResponse> {
        return try {
            Result.Success(
                apiService.postAttendance(
                    AttendanceRequest(
                        data = DataAttendanceRequest(
                            user = userId.toInt(),
                            latitude = latitude,
                            longitude = longitude,
                            permit = false
                        )
                    )
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }


}