package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.request.LoginRequest
import com.magic.officeapp.data.model.request.RegisterEmployeeRequest
import com.magic.officeapp.data.model.response.JobRolesResponse
import com.magic.officeapp.data.model.response.RegisterEmployeeResponse
import com.magic.officeapp.data.model.response.UserListResponse
import com.magic.officeapp.data.model.response.UserListResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiEmployeeInterface {
    @GET("/api/users/?populate=role,job_role&filters[role][name]=Employee")
    suspend fun getEmployeeList(
        @Header("Authorization") token: String
    ): List<UserListResponseItem>

    @GET("/api/job-roles/?filters[name][\$ne]=HR")
    suspend fun getJobRoles(
        @Header("Authorization") token: String
    ): JobRolesResponse

    @POST("api/auth/local/register")
    suspend fun addEmployee(
        @Body registerRequest: RegisterEmployeeRequest
    ): RegisterEmployeeResponse
}