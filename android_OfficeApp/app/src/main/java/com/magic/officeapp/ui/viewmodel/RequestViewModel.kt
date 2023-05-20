package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.request.RequestUpdate
import com.magic.officeapp.data.model.request.RequestUpdateData
import com.magic.officeapp.data.model.response.UserListResponseItem
import com.magic.officeapp.data.model.response.request.*
import com.magic.officeapp.data.repository.RequestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.magic.officeapp.utils.constants.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val repository: RequestRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading

    private val _requestDataState = MutableStateFlow<Result<RequestsResponse>>(Result.Empty)
    val requestDataState get() = _requestDataState

    private val _addRequestState = MutableStateFlow<Result<AddRequestResponse>>(Result.Empty)
    val addRequestState get() = _addRequestState

    private val _getAllRequestsResponse = MutableStateFlow<Result<GetAllRequestsResponse>>(Result.Empty)
    val getAllRequestsResponse get() = _getAllRequestsResponse

    private val _requestDetail = MutableStateFlow<Result<GetRequestByIdResponse>>(Result.Empty)
    val requestDetail get() = _requestDetail

    private val _requestList = MutableStateFlow(emptyList<GetAllRequestsDataItem?>())
    val requestList get() = _requestList


    fun updateRequestStatus(
        id: Int,
        isApproved: String,
        feedback : String,
    ) {
        viewModelScope.launch {
            _loading.value = true
            val request = RequestUpdate(
                data = RequestUpdateData(
                    isApproved = isApproved,
                    feedback = feedback
                )
            )
            var data = repository.updateRequestStatus(id, request)
            _addRequestState.value = data
            _loading.value = false
        }
    }
    fun getRequestDetail(
        id: Int,
    ) {
        viewModelScope.launch {
            _loading.value = true
            _requestDetail.value = repository.getRequestById(id)
            _loading.value = false
        }
    }
    fun getAllRequests(
    ) {
        viewModelScope.launch {
            _loading.value = true
            val data = repository.getAllRequests()
            _getAllRequestsResponse.value = data
            if(data is Result.Success){
                _requestList.value = data.data.data as List<GetAllRequestsDataItem?>
            }
            _loading.value = false
        }
    }

    fun getUserRequest(
        userId: String,
    ) {
        viewModelScope.launch {
            _loading.value = true
            var data = repository.getUserRequest(userId)
            _requestDataState.value = data
            _loading.value = false
        }
    }

    fun createRequest(
        userId: String,
        requestType: String,
        note: String,
        date : String?
    ) {
        viewModelScope.launch {
            _loading.value = true
            var data = repository.createRequest(userId, requestType, note, date)
            _addRequestState.value = data
            _loading.value = false
        }
    }

}