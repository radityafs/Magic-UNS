package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.request.LoginRequest
import com.magic.officeapp.data.model.response.DetailUserResponse
import com.magic.officeapp.data.model.response.UserListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiAuthInterface {

    @POST("api/auth/local/?populates=")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("api/users/me/?populate=role")
    suspend fun getDetails(
        @Header("Authorization") token: String
    ): DetailUserResponse



}