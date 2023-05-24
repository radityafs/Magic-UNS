package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class AddPayrollResponse(

	@field:SerializedName("data")
	val data: AddPayrollResponseData? = null,

	@field:SerializedName("meta")
	val meta: AddPayrollResponseMeta? = null
)

data class AddPayrollResponseAttributes(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("deduction")
	val deduction: String? = null,

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

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class AddPayrollResponseData(

	@field:SerializedName("attributes")
	val attributes: AddPayrollResponseAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class AddPayrollResponseMeta(
	val any: Any? = null
)
