package com.magic.officeapp.data.api

import com.magic.officeapp.data.model.request.AttendanceRequest
import com.magic.officeapp.data.model.response.AttendanceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiAttendanceInterface {
    @GET("/api/attendances/?populate=user&sort=publishedAt:desc&pagination[pageSize]=100")
    suspend fun getAttendance(): List<AttendanceResponse>

    @GET("/api/attendances/?populate=user&filters[publishedAt][\$gte]={startDate}&filters[publishedAt][\$lte]={endDate}&sort=publishedAt:desc&pagination[pageSize]=100")
    suspend fun getAttendanceByRange(
        startDate: String, endDate: String
    ): List<AttendanceResponse>

    @GET("/api/attendances/?populate=user&filters[user][id][\$eq]={userId}&sort=publishedAt:desc&pagination[pageSize]=100")
    suspend fun getAttendance(
        userId: String
    ): List<AttendanceResponse>

    @GET("/api/attendances/?populate=user&filters[user][id][\$eq]={userId}&filters[publishedAt][\$gte]={startDate}&filters[publishedAt][\$lte]={endDate}&sort=publishedAt:desc&pagination[pageSize]=100")
    suspend fun getAttendanceByRange(
        userId: String, startDate: String, endDate: String
    ): List<AttendanceResponse>

    @POST("/api/attendances")
    suspend fun postAttendance(
        @Body attendanceRequest: AttendanceRequest
    ): AttendanceResponse
}