package com.magic.officeapp.data.api

import retrofit2.http.POST

interface ApiAnnoucementInterface {
    @POST("/api/announcements")
    suspend fun add()
}