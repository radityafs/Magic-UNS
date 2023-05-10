package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(

	@field:SerializedName("data")
	val data: DataLocationResponse? = null,

	@field:SerializedName("meta")
	val meta: MetaLocationResponse? = null
)

data class LocationResponseAttributes(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class DataLocationResponse(

	@field:SerializedName("attributes")
	val attributes: LocationResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class MetaLocationResponse(
	val any: String? = null
)
