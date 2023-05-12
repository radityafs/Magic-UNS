package com.magic.officeapp.data.repository

import com.magic.officeapp.data.api.ApiAttendanceInterface
import com.magic.officeapp.data.api.ApiEmployeeInterface
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.request.LoginRequest
import com.magic.officeapp.data.model.request.RegisterEmployeeRequest
import com.magic.officeapp.data.model.response.JobRolesResponse
import com.magic.officeapp.data.model.response.RegisterEmployeeResponse
import com.magic.officeapp.data.model.response.UserListResponse
import com.magic.officeapp.data.model.response.UserListResponseItem
import com.magic.officeapp.utils.constants.Result
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val apiService: ApiEmployeeInterface
) {
    suspend fun getEmployeeList(
        token: String
    ): Result<List<UserListResponseItem>> {
        return try {
            Result.Success(
                apiService.getEmployeeList(
                    "Bearer $token"
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getJobRoles(
        token: String
    ): Result<JobRolesResponse> {
        return try {
            Result.Success(
                apiService.getJobRoles(
                    "Bearer $token"
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun addEmployee(
        registerRequest: RegisterEmployeeRequest
    ): Result<RegisterEmployeeResponse> {
        return try {
            Result.Success(
                apiService.addEmployee(
                    registerRequest = registerRequest
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

}