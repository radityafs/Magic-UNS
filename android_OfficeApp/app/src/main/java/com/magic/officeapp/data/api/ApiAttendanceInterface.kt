package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.request.AttendanceRequest
import com.magic.officeapp.data.model.request.CheckoutAttendanceRequest
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.data.model.response.LocationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiAttendanceInterface {

    @GET("/api/location")
    suspend fun getApiLocation(): LocationResponse

    @GET("/api/attendances")
    suspend fun getAttendanceByUserId(
        @Header("Authorization") token: String = "",
        @Query("populate") populate: String = "user",
        @Query("filters[user][id][\$eq]") userId: String,
        @Query("sort") sort: String = "createdAt:desc",
        @Query("pagination[pageSize]") pageSize: Int = 100
    ): AttendanceResponse

    @GET("/api/attendances/?populate[user][populate]=*")
    suspend fun getAllAttendance(
        @Query("sort") sort: String = "updatedAt:desc",
        @Query("pagination[pageSize]") pageSize: Int = 250
    ) : AttendanceResponse

    @GET("/api/attendances")
    suspend fun getAttendanceByRange(
        @Header("Authorization") token: String = "",
        @Query("populate") populate: String = "user",
        @Query("filters[user][id][\$eq]") userId: String,
        @Query("filters[publishedAt][\$gte]") startDate: String,
        @Query("filters[publishedAt][\$lte]") endDate: String,
        @Query("sort") sort: String = "updatedAt:desc",
        @Query("pagination[pageSize]") pageSize: Int = 100
    ): AttendanceResponse

    @POST("/api/attendances")
    suspend fun postAttendance(
        @Header("Authorization") token: String = "",
        @Body attendanceRequest: AttendanceRequest
    ): AttendanceResponse

    @PUT("/api/attendances/{id}")
    suspend fun updateCheckOut(
        @Path("id") attendanceId: String,
        @Body checkoutAttendanceRequest: CheckoutAttendanceRequest
    ): AttendanceResponse
}