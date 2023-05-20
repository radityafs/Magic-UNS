package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class AttendanceResponse(

	@field:SerializedName("data")
	val data: List<AttendanceResponseDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: AttendanceResponseMeta? = null
)

data class AttendanceResponseAttributes(

	@field:SerializedName("check_out")
	val checkOut: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

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

	@field:SerializedName("role")
	val role: AttendanceResponseRole? = null,

	@field:SerializedName("job_role")
	val jobRole: AttendanceResponseJobRole? = null,

	@field:SerializedName("updatedBy")
	val updatedBy: AttendanceResponseUpdatedBy? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("confirmed")
	val confirmed: Boolean? = null,

	@field:SerializedName("picture")
	val picture: String? = null,

	@field:SerializedName("blocked")
	val blocked: Boolean? = null,

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("createdBy")
	val createdBy: AttendanceResponseCreatedBy? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class AttendanceResponseUpdatedBy(

	@field:SerializedName("data")
	val data: Any? = null
)

data class AttendanceResponseRole(

	@field:SerializedName("data")
	val data: AttendanceResponseData? = null
)

data class AttendanceResponseJobRole(

	@field:SerializedName("data")
	val data: AttendanceResponseData? = null
)

data class AttendanceResponseUser(

	@field:SerializedName("data")
	val data: AttendanceResponseData? = null
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

data class AttendanceResponseMeta(

	@field:SerializedName("pagination")
	val pagination: AttendanceResponsePagination? = null
)

data class AttendanceResponseCreatedBy(

	@field:SerializedName("data")
	val data: Any? = null
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
