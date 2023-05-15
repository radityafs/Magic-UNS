package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.response.UserListResponseItem
import com.magic.officeapp.data.model.response.request.AddRequestResponse
import com.magic.officeapp.data.model.response.request.RequestsResponse
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