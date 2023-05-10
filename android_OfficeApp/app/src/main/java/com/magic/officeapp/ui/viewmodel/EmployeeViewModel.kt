package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.magic.officeapp.data.model.request.RegisterEmployeeRequest
import com.magic.officeapp.data.model.response.*
import com.magic.officeapp.data.repository.AuthRepository
import com.magic.officeapp.data.repository.EmployeeRepository
import com.magic.officeapp.utils.constants.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading

    private val _employeeData = MutableStateFlow(emptyList<UserListResponseItem>())
    val employeeData get() = _employeeData

    private val _employeeDataResponse =
        MutableStateFlow<Result<List<UserListResponseItem>>>(Result.Empty)
    val getEmployeeListResponse get() = _employeeDataResponse

    private val _insertEmployeeDataResponse =
        MutableStateFlow<Result<RegisterEmployeeResponse>>(Result.Empty)
    val insertEmployeeDataResponse get() = _insertEmployeeDataResponse

    private val _jobRolesData = MutableStateFlow<Result<JobRolesResponse>>(Result.Empty)
    val jobRolesData get() = _jobRolesData

    fun getEmployeeList(
        token: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            var data = repository.getEmployeeList(token)
            _employeeDataResponse.value = data

            if (data is Result.Success) {
                _employeeData.value = data.data
            }

            _loading.value = false
        }
    }

    fun insertJobRoles(
        user: RegisterEmployeeRequest
    ) {
        viewModelScope.launch {
            _loading.value = true
            val data = repository.addEmployee(user)
            _insertEmployeeDataResponse.value = data
            _loading.value = false
        }
    }

    fun getJobRoles(
        token: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val data = repository.getJobRoles(token)
            _jobRolesData.value = data
            _loading.value = false
        }
    }
}