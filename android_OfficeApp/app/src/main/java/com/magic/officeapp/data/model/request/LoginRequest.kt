package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

	@field:SerializedName("identifier")
	val identifier: String,

	@field:SerializedName("password")
	val password: String,

)
