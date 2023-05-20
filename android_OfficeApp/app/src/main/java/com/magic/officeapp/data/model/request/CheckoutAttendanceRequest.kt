package com.magic.officeapp.data.model.request

import com.google.gson.annotations.SerializedName

data class CheckoutAttendanceRequest(

	@field:SerializedName("data")
	val data: CheckoutAttendanceRequestData? = null
)

data class CheckoutAttendanceRequestData(

	@field:SerializedName("check_out")
	val checkOut: String? = null
)
