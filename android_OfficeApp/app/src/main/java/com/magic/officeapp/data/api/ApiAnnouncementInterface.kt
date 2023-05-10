package com.magic.officeapp.data.api

import androidx.compose.ui.semantics.Role
import com.magic.officeapp.data.model.request.AddAnnouncementRequest
import com.magic.officeapp.data.model.response.AddAnnouncementResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAnnouncementInterface {
    @POST("/api/announcements")
    suspend fun add(
        @Body request: AddAnnouncementRequest
    ): AddAnnouncementResponse
}