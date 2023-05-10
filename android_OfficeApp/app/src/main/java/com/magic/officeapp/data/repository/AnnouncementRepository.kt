package com.magic.officeapp.data.repository

import android.util.Log
import javax.inject.Inject
import com.magic.officeapp.data.api.ApiAnnouncementInterface
import com.magic.officeapp.data.model.request.AddAnnouncementData
import com.magic.officeapp.data.model.request.AddAnnouncementRequest
import com.magic.officeapp.data.model.response.AddAnnouncementResponse
import com.magic.officeapp.utils.constants.Result

class AnnouncementRepository @Inject constructor(
    private val apiService: ApiAnnouncementInterface
) {
    suspend fun addAnnouncement(
        title: String,
        description: String,
        role: List<Int?>?,
        userId: Int?,
        date: String,
    ) : Result<AddAnnouncementResponse> {
        return try {
            val data = AddAnnouncementRequest(
                data = AddAnnouncementData(
                    title = title,
                    description = description,
                    jobRoles = role,
                    user = userId,
                    date = date,
                )
            )
            Result.Success(apiService.add(data))
        } catch (err:Exception) {
            Log.e("err", err.message.toString())
            Result.Error(err.message.toString())
        }
    }
}