package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class AttendanceResponse(

	@field:SerializedName("data")
	val data: List<AttendanceResponseDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: AttendanceResponseMeta? = null
)

data class AttendanceResponseUser(

	@field:SerializedName("data")
	val data: AttendanceResponseData? = null
)

data class AttendanceResponseDataItem(

	@field:SerializedName("attributes")
	val attributes: AttendanceResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class AttendanceResponseData(

	@field:SerializedName("attributes")
	val attributes: AttendanceResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class AttendanceResponseAttributes(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("check_out")
	val checkOut: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("user")
	val user: AttendanceResponseUser? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("blocked")
	val blocked: Boolean? = null,

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("confirmed")
	val confirmed: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class AttendanceResponseMeta(

	@field:SerializedName("pagination")
	val pagination: AttendanceResponsePagination? = null
)

data class AttendanceResponsePagination(

	@field:SerializedName("pageCount")
	val pageCount: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("pageSize")
	val pageSize: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null
)
