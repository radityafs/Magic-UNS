package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterEmployeeRequest(
	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("job_role")
	val jobRole: Int? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
