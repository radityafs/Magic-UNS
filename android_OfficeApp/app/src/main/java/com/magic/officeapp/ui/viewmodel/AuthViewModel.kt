package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.User
import com.magic.officeapp.data.model.response.DetailUserResponse
import com.magic.officeapp.data.repository.AuthRepository
import com.magic.officeapp.utils.constants.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _isLogged = MutableStateFlow(false)
    val isLogged get() = _isLogged

    private val _userData = MutableStateFlow<DetailUserResponse?>(null)
    val userData get() = _userData

    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Empty)
    val loginState: StateFlow<Result<LoginResponse>> get() = _loginState

    private val _loginDetail = MutableStateFlow<Result<DetailUserResponse>>(Result.Empty)
    val loginDetail: StateFlow<Result<DetailUserResponse>> get() = _loginDetail

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    init {
        isUserLoggedIn()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            _loginState.value = repository.login(email, password)
            if (_loginState.value is Result.Success) {
                getDetails((_loginState.value as Result.Success).data.jwt)
            }
        }
    }

    private fun getDetails(
        token: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val data = repository.getDetails(token)
            _loginDetail.value = data

            if(data is Result.Success) {
                var userDataTemp = data.data
                userDataTemp = userDataTemp.copy(jwt = token)

                _userData.value = userDataTemp
                saveUserData(userDataTemp)
                _isLogged.value = true
            }
            else {
                _userData.value = null
            }

            _loading.value = false
        }
    }

    private fun isUserLoggedIn() {
        viewModelScope.launch {
            _loading.value = true
            _isLogged.value = repository.getUserData().let {
                _userData.value = it
                it != null
            }

            _loading.value = false
        }
    }

    private fun saveUserData(user: DetailUserResponse) {
        viewModelScope.launch {
            repository.saveUserData(user)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearUserData()
            _isLogged.value = false
            _userData.value = null
        }
    }
}