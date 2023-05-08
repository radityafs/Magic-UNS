package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiEmployeeInterface {
    @POST("api/auth/local")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}