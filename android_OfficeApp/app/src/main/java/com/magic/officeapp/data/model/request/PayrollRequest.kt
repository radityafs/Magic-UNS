package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class PayrollRequest(
	@field:SerializedName("data")
	val data: PayrollRequestData? = null
)

data class PayrollRequestData(

	@field:SerializedName("deduction")
	val deduction: String? = null,

	@field:SerializedName("bonus")
	val bonus: String? = null,

	@field:SerializedName("total_salary")
	val totalSalary: String? = null,

	@field:SerializedName("month")
	val month: String? = null,

	@field:SerializedName("tax")
	val tax: String? = null,

	@field:SerializedName("user")
	val user: String? = null,

	@field:SerializedName("work_days")
	val workDays: String? = null,

	@field:SerializedName("work_salary")
	val workSalary: String? = null
)
