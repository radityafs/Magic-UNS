package com.magic.officeapp.data.api

import androidx.compose.ui.semantics.Role
import com.magic.officeapp.data.model.request.AddAnnouncementRequest
import com.magic.officeapp.data.model.response.AddAnnouncementResponse
import com.magic.officeapp.data.model.response.announcement.AnnouncementsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiAnnouncementInterface {
    @POST("/api/announcements")
    suspend fun add(
        @Header("Authorization") token: String = "",
        @Body request: AddAnnouncementRequest
    ): AddAnnouncementResponse

    @GET("/api/announcements?populate=*&filters[user][id][\$null]=true&sort=publishedAt:desc")
    suspend fun getAnnouncements(
        @Header("Authorization") token: String = ""
    ): AnnouncementsResponse

    @GET("/api/announcements")
    suspend fun getAnnouncements(
        @Header("Authorization") token: String = "",
        @Query("populate") populate: String = "*",
        @Query("filters[\$or][0][user][id][\$eq]") userId: String,
        @Query("filters[\$or][1][job_roles][id][\$eq]") jobRoleId: String,
        @Query("sort") sort: String = "publishedAt:desc",
    ): AnnouncementsResponse

}