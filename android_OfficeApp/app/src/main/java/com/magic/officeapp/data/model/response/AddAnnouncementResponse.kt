package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class AddAnnouncementResponse(

	@field:SerializedName("data")
	val data: ResponseData? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null
)

data class Attributes(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("title")
	val title: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class ResponseData(

	@field:SerializedName("attributes")
	val attributes: Attributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Meta(
	val any: Any? = null
)
