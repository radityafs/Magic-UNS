package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.request.PayrollRequest
import com.magic.officeapp.data.model.response.*
import com.magic.officeapp.data.repository.PayrollRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.magic.officeapp.utils.constants.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayrollViewModel @Inject constructor(
    private val repository: PayrollRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading

    private val _insertPayrollDataResponse =
        MutableStateFlow<Result<AddPayrollResponse>>(Result.Empty)
    val insertPayrollDataResponse get() = _insertPayrollDataResponse

    private val _allPayrollDataState =
        MutableStateFlow<Result<PayrollResponse>>(Result.Empty)
    val allPayrollDataState get() = _allPayrollDataState

    private val _payrollDetailState =
        MutableStateFlow<Result<PayrollDetailResponse>>(Result.Empty)
    val payrollDetailState get() = _payrollDetailState

    private val _allPayrollByUserState =
        MutableStateFlow<Result<PayrollResponse>>(Result.Empty)
    val allPayrollByUserState get() = _allPayrollByUserState

    private val _allPayrollData = MutableStateFlow<List<PayrollResponseDataItem?>?>(null)
    val allPayrollData get() = _allPayrollData

    private val _allPayrollByUser = MutableStateFlow<List<PayrollResponseDataItem?>?>(null)
    val allPayrollByUser get() = _allPayrollByUser

    private val _payrollDetail = MutableStateFlow<PayrollDetailResponse?>(null)
    val payrollDetail get() = _payrollDetail


    fun insertPayroll(
        payrollRequestData: PayrollRequest,
    ) {
        viewModelScope.launch {
            _loading.value = true

            val data = repository.insertPayroll(
                payrollRequestData
            )

            _insertPayrollDataResponse.value = data
            _loading.value = false
        }
    }

    fun getAllPayrollData(
    ) {
        viewModelScope.launch {
            _loading.value = true
            val data = repository.getAllPayrollData()
            _allPayrollDataState.value = data

            if(data is Result.Success){
                _allPayrollData.value = data.data?.data
            }

            _loading.value = false
        }
    }

    fun getAllPayrollbyUser(
        id: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val data = repository.getPayrollByUser(id)
            _allPayrollByUserState.value = data


            if(data is Result.Success){
                _allPayrollByUser.value = data.data?.data
            }

            _loading.value = false
        }
    }

    fun getPayrollById(
        id: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val data = repository.getPayrollById(id)
            _payrollDetailState.value = data

            if(data is Result.Success){
                _payrollDetail.value = data.data
            }

            _loading.value = false
        }
    }
}