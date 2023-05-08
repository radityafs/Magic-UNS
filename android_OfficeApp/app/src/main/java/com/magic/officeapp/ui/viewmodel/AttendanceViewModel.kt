package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.magic.officeapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _isAttendanceToday = MutableStateFlow(false)
    val isAttendanceToday get() = _isAttendanceToday

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading

}