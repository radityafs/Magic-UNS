package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class AddAnnouncementRequest(

	@field:SerializedName("data")
	val data: AddAnnouncementData? = null
)

data class AddAnnouncementData(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("job_roles")
	val jobRoles: List<Int?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("user")
	val user: Int? = null
)
