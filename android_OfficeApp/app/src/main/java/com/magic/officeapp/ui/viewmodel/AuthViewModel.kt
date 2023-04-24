package com.magic.officeapp.ui.viewmodel

import android.util.MutableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isLogged = MutableStateFlow(false)
    val isLogged get() = _isLogged

    fun isUserLoggedIn() {
        viewModelScope.launch {
            _isLogged.value = repository.getUserData().let {
                it?.confirmed != null
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearUserData()
        }
    }
}