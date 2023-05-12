package com.magic.officeapp.data.repository

import com.magic.officeapp.data.api.ApiRequestInterface
import com.magic.officeapp.data.model.request.Data
import com.magic.officeapp.data.model.request.RequestsRequest
import com.magic.officeapp.data.model.response.request.AddRequestResponse
import com.magic.officeapp.data.model.response.request.RequestsResponse
import com.magic.officeapp.utils.constants.Result

import javax.inject.Inject

class RequestRepository @Inject constructor(
    private val apiService: ApiRequestInterface
) {

    suspend fun getUserRequest(
        userId: String,
    ): Result<RequestsResponse> {
        return try {
            Result.Success(
                apiService.getRequests(
                    id = userId
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun createRequest(
        userId: String,
        requestType: String,
        note: String,
        date : String?
    ): Result<AddRequestResponse> {
        return try {
            Result.Success(
                apiService.createRequest(
                    request = RequestsRequest(
                        data = Data(
                            user = userId.toInt(),
                            requestType = requestType,
                            note = note,
                            requestDate = date
                        )
                    )
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

}