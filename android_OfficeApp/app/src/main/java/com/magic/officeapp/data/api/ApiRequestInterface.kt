package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.request.RequestUpdate
import com.magic.officeapp.data.model.request.RequestsRequest
import com.magic.officeapp.data.model.response.request.AddRequestResponse
import com.magic.officeapp.data.model.response.request.GetAllRequestsData
import com.magic.officeapp.data.model.response.request.GetAllRequestsResponse
import com.magic.officeapp.data.model.response.request.GetRequestByIdResponse
import com.magic.officeapp.data.model.response.request.RequestsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequestInterface {

    @PUT("api/requests/{id}")
    suspend fun updateRequest(
        @Header("Authorization") token: String ="",
        @Path("id") id: Int,
        @Body request: RequestUpdate
    ) : AddRequestResponse

    @GET("api/requests")
    suspend fun getRequests(
        @Header("Authorization") token: String ="",
        @Query("filters[user][id]") id: String,
        @Query("sort") sort: String = "createdAt:desc"
    ) : RequestsResponse

    @GET("api/requests/{id}?populate[user][populate]=*")
    suspend fun  getRequestById(
        @Header("Authorization") token: String ="",
        @Path("id") id: Int,
    ) : GetRequestByIdResponse

    @GET("/api/requests?populate[user][populate]=*")
    suspend fun getAllRequests(
        @Header("Authorization") token: String ="",
        @Query("sort") sort: String = "createdAt:desc"
    ) : GetAllRequestsResponse

    @POST("api/requests")
    suspend fun createRequest(
        @Header("Authorization") token: String = "",
        @Body request: RequestsRequest
    ) : AddRequestResponse

}