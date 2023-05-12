package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.request.AttendanceRequest
import com.magic.officeapp.data.model.response.AttendanceResponse
import com.magic.officeapp.data.model.response.LocationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiAttendanceInterface {

    @GET("/api/location")
    suspend fun getApiLocation(): LocationResponse

    @GET("/api/attendances/?populate=user&sort=publishedAt:desc&pagination[pageSize]=100")
    suspend fun getAttendance(
        @Header("Authorization") token: String = ""
    ): AttendanceResponse

    @GET("/api/attendances")
    suspend fun getAttendanceByUserId(
        @Header("Authorization") token: String = "",
        @Query("populate") populate: String = "user",
        @Query("filters[user][id][\$eq]") userId: String,
        @Query("sort") sort: String = "publishedAt:desc",
        @Query("pagination[pageSize]") pageSize: Int = 100
    ): AttendanceResponse

    @GET("/api/attendances/?populate=user&filters[user][id][\$eq]={userId}&sort=publishedAt:desc&pagination[pageSize]=100")
    suspend fun getAttendance(
        @Header("Authorization") token: String = "",
        userId: String
    ): AttendanceResponse

    @GET("/api/attendances")
    suspend fun getAttendanceByRange(
        @Header("Authorization") token: String = "",
        @Query("populate") populate: String = "user",
        @Query("filters[user][id][\$eq]") userId: String,
        @Query("filters[publishedAt][\$gte]") startDate: String,
        @Query("filters[publishedAt][\$lte]") endDate: String,
        @Query("sort") sort: String = "publishedAt:desc",
        @Query("pagination[pageSize]") pageSize: Int = 100
    ): AttendanceResponse

    @POST("/api/attendances")
    suspend fun postAttendance(
        @Header("Authorization") token: String = "",
        @Body attendanceRequest: AttendanceRequest
    ): AttendanceResponse
}