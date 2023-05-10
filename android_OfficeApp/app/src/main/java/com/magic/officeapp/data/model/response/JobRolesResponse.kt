package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class JobRolesResponse(

	@field:SerializedName("data")
	val data: List<JobRolesResponseDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: JobRolesResponseMeta? = null
)

data class JobRolesResponseDataItem(

	@field:SerializedName("attributes")
	val attributes: JobRolesResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class JobRolesResponseMeta(

	@field:SerializedName("pagination")
	val pagination: JobRolesResponsePagination? = null
)

data class JobRolesResponsePagination(

	@field:SerializedName("pageCount")
	val pageCount: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("pageSize")
	val pageSize: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null
)

data class JobRolesResponseAttributes(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
