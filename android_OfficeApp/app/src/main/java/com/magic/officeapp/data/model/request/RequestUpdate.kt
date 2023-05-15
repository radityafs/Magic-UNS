package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class RequestUpdate(

	@field:SerializedName("data")
	val data: RequestUpdateData? = null
)

data class RequestUpdateData(

	@field:SerializedName("feedback")
	val feedback: String? = null,

	@field:SerializedName("is_approved")
	val isApproved: String? = null
)
