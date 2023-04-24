package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiAuthInterface {

    @POST("api/auth/local")
    suspend fun getPopularMovies(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("api/auth/local/register")
    suspend fun getNowPlayingMovies(
        @Body loginRequest: LoginRequest
    ): LoginResponse

}