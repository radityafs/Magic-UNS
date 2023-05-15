package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.request.RequestsRequest
import com.magic.officeapp.data.model.response.request.AddRequestResponse
import com.magic.officeapp.data.model.response.request.RequestsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiRequestInterface {
    @GET("api/requests")
    suspend fun getRequests(
        @Header("Authorization") token: String ="",
        @Query("filters[user][id]") id: String,
        @Query("sort") sort: String = "createdAt:desc"
    ) : RequestsResponse

    @POST("api/requests")
    suspend fun createRequest(
        @Header("Authorization") token: String = "",
        @Body request: RequestsRequest
    ) : AddRequestResponse

}