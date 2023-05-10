package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class AttendanceRequest(
	@field:SerializedName("data")
	val data: DataAttendanceRequest? = null
)

data class DataAttendanceRequest(
	@field:SerializedName("user")
	val user: Int? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("permit")
	val permit: Boolean? = false
)
