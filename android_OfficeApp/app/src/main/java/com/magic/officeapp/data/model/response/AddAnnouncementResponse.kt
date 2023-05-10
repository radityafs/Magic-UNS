package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class AddAnnouncementResponse(

	@field:SerializedName("data")
	val data: AddAnnouncementData? = null,

	@field:SerializedName("meta")
	val meta: AddAnnouncementMeta? = null
)

data class AddAnnouncementAttributes(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class AddAnnouncementData(

	@field:SerializedName("attributes")
	val attributes: AddAnnouncementAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class AddAnnouncementMeta(
	val any: Any? = null
)
