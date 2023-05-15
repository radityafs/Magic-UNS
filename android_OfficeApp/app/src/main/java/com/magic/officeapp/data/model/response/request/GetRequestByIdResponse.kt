package com.magic.officeapp.data.model.response.request

import com.google.gson.annotations.SerializedName

data class GetRequestByIdResponse(

	@field:SerializedName("data")
	val data:  GetRequestByIdData? = null,

	@field:SerializedName("meta")
	val meta:  GetRequestByIdMeta? = null
)

data class  GetRequestByIdRole(

	@field:SerializedName("data")
	val data:  GetRequestByIdData? = null
)

data class GetRequestByIdCreatedBy(

	@field:SerializedName("data")
	val data: Any? = null
)

data class  GetRequestByIdUser(

	@field:SerializedName("data")
	val data:  GetRequestByIdData? = null
)

data class  GetRequestByIdUpdatedBy(

	@field:SerializedName("data")
	val data:  GetRequestByIdData? = null
)

data class  GetRequestByIdJobRole(

	@field:SerializedName("data")
	val data:  GetRequestByIdData? = null
)

data class GetRequestByIdData(

	@field:SerializedName("attributes")
	val attributes:  GetRequestByIdAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class  GetRequestByIdAttributes(

	@field:SerializedName("feedback")
	val feedback: Any? = null,

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("request_type")
	val requestType: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("request_date")
	val requestDate: String? = null,

	@field:SerializedName("is_approved")
	val isApproved: String? = null,

	@field:SerializedName("user")
	val user:  GetRequestByIdUser? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("role")
	val role:  GetRequestByIdRole? = null,

	@field:SerializedName("job_role")
	val jobRole:  GetRequestByIdJobRole? = null,

	@field:SerializedName("updatedBy")
	val updatedBy:  GetRequestByIdUpdatedBy? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("confirmed")
	val confirmed: Boolean? = null,

	@field:SerializedName("blocked")
	val blocked: Boolean? = null,

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("createdBy")
	val createdBy:  GetRequestByIdCreatedBy? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: Any? = null,

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("preferedLanguage")
	val preferedLanguage: Any? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class  GetRequestByIdMeta(
	val any: Any? = null
)
