package com.magic.officeapp.data.model.response.request

import com.google.gson.annotations.SerializedName

data class RequestsResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null
)

data class Meta(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null
)

data class DataItem(

	@field:SerializedName("attributes")
	val attributes: Attributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Pagination(

	@field:SerializedName("pageCount")
	val pageCount: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("pageSize")
	val pageSize: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null
)

data class Attributes(

	@field:SerializedName("feedback")
	val feedback: Any? = null,

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("request_type")
	val requestType: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("request_date")
	val requestDate: String? = null,

	@field:SerializedName("is_approved")
	val isApproved: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
