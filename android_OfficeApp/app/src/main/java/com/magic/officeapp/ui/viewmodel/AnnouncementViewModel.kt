package com.magic.officeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.officeapp.data.model.response.AddAnnouncementResponse
import com.magic.officeapp.data.model.response.UserListResponseItem
import com.magic.officeapp.data.model.response.announcement.AnnouncementsResponse
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
    val loading get() = _loading

    private val _userAnnouncementState =
        MutableStateFlow<Result<AnnouncementsResponse>>(Result.Empty)
    val userAnnouncementState get() = _userAnnouncementState

    private val _addAnnouncementState =
        MutableStateFlow<Result<AddAnnouncementResponse>>(Result.Empty)
    val addAnnouncementState get() = _addAnnouncementState

    private val _announcementData = MutableStateFlow<Result<AnnouncementsResponse>>(Result.Empty)
    val announcementData get() = _announcementData

    fun getAllAnnouncement(token:String="") {
        viewModelScope.launch {
            _loading.value = true
            _announcementData.value = repository.getAllAnnouncements(token=token)
            _loading.value = false
        }
    }
    fun getAnnouncementUser(
        userId: String,
        jobRoleId: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            _userAnnouncementState.value = repository.getUserAnnouncement(
                userId = userId,
                jobRoleId = jobRoleId
            )
            _loading.value = false
        }
    }

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