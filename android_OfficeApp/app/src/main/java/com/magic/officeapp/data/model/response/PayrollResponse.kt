package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class PayrollResponse(
	@field:SerializedName("data")
	val data: List<PayrollResponseDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: PayrollResponseMeta? = null
)

data class PayrollResponseAttributes(

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
	val tax: Int? = null,

	@field:SerializedName("work_days")
	val workDays: Int? = null,

	@field:SerializedName("work_salary")
	val workSalary: String? = null,

	@field:SerializedName("user")
	val user: PayrollResponseUser? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

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
	val username: String? = null
)

data class PayrollResponseData(

	@field:SerializedName("attributes")
	val attributes: PayrollResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class PayrollResponseMeta(

	@field:SerializedName("pagination")
	val pagination: PayrollResponsePagination? = null
)

data class PayrollResponseDataItem(

	@field:SerializedName("attributes")
	val attributes: PayrollResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class PayrollResponseUser(

	@field:SerializedName("data")
	val data: PayrollResponseData? = null
)

data class PayrollResponsePagination(

	@field:SerializedName("pageCount")
	val pageCount: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("pageSize")
	val pageSize: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null
)
