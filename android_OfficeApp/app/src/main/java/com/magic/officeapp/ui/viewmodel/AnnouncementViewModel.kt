package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.response.AddAnnouncementResponse
import com.magic.officeapp.data.repository.AnnouncementRepository
import com.magic.officeapp.utils.constants.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val repository: AnnouncementRepository
) : ViewModel() {

    private var _loading = MutableStateFlow(false)
    val loading get()= _loading

    private val _addAnnouncementState = MutableStateFlow<Result<AddAnnouncementResponse>>(Result.Empty)
    val addAnnouncementState get() = _addAnnouncementState

    fun addAnnouncement(
        title: String,
        description: String,
        date: String,
        Role: Int?,
        userId: Int?,
    ) {
        viewModelScope.launch {
            _loading.value = true
            _addAnnouncementState.value = repository.addAnnouncement(
                title = title,
                description = description,
                date = date,
                role = listOf(Role),
                userId = userId,
            )
            _loading.value = false
        }
    }
}