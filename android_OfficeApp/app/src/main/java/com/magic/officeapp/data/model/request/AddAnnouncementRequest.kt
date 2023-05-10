package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class AddAnnouncement(
	@field:SerializedName("data")
	val data: AddAnnouncementData? = null
)

data class AddAnnouncementData(

	@field:SerializedName("job_role")
	val jobRole: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("users")
	val users: Int? = null
)
