package com.magic.officeapp.data.model.response.request

import com.google.gson.annotations.SerializedName

data class AddRequestResponse(

	@field:SerializedName("data")
	val data: AddRequestResponseData? = null,

	@field:SerializedName("meta")
	val meta: AddRequestResponseMeta? = null
)

data class AddRequestResponseAttributes(

	@field:SerializedName("feedback")
	val feedback: String? = null,

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

data class AddRequestResponseData(

	@field:SerializedName("attributes")
	val attributes: AddRequestResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class AddRequestResponseMeta(
	val any: Any? = null
)
