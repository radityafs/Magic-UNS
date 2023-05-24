package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class PayrollDetailResponse(

	@field:SerializedName("data")
	val data: PayrollDetailResponseData? = null,

	@field:SerializedName("meta")
	val meta: PayrollDetailResponseMeta? = null
)

data class PayrollDetailResponseData(

	@field:SerializedName("attributes")
	val attributes: PayrollDetailResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class PayrollDetailResponseJobRole(

	@field:SerializedName("data")
	val data: PayrollDetailResponseData? = null
)

data class PayrollDetailResponseUser(

	@field:SerializedName("data")
	val data: PayrollDetailResponseData? = null
)

data class PayrollDetailResponseAttributes(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("deduction")
	val deduction: String? = null,

	@field:SerializedName("month")
	val month: String? = null,

	@field:SerializedName("bonus")
	val bonus: String? = null,

	@field:SerializedName("total_salary")
	val totalSalary: String? = null,

	@field:SerializedName("tax")
	val tax: String? = null,

	@field:SerializedName("work_days")
	val workDays: Int? = null,

	@field:SerializedName("work_salary")
	val workSalary: String? = null,

	@field:SerializedName("user")
	val user: PayrollDetailResponseUser? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("job_role")
	val jobRole: PayrollDetailResponseJobRole? = null,

	@field:SerializedName("blocked")
	val blocked: Boolean? = null,

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("confirmed")
	val confirmed: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("picture")
	val picture: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class PayrollDetailResponseMeta(
	val any: Any? = null
)
