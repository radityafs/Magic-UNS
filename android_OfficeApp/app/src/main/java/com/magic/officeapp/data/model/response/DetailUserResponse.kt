package com.magic.officeapp.data.model.response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

	@field:SerializedName("role")
	val role: DetailUserResponseRole? = null,

	val jwt: String? = null,

	@field:SerializedName("job_role")
	val jobRole: DetailUserResponseJobRole? = null,

	@field:SerializedName("updatedBy")
	val updatedBy: UpdatedBy? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("confirmed")
	val confirmed: Boolean? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("blocked")
	val blocked: Boolean? = null,

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("createdBy")
	val createdBy: CreatedBy? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class DetailUserResponseJobRole(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class CreatedBy(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("preferedLanguage")
	val preferedLanguage: Any? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null,

	@field:SerializedName("username")
	val username: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class UpdatedBy(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("preferedLanguage")
	val preferedLanguage: Any? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null,

	@field:SerializedName("username")
	val username: Any? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class DetailUserResponseRole(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
