package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class RequestsRequest(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("request_type")
	val requestType: String? = null,

	@field:SerializedName("request_date")
	val requestDate: String? = null,

	@field:SerializedName("user")
	val user: Int? = null
)
