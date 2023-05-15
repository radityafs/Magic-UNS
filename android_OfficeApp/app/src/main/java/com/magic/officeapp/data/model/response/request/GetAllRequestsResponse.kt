package com.magic.officeapp.data.model.response.request

import com.google.gson.annotations.SerializedName

data class GetAllRequestsResponse(

	@field:SerializedName("data")
	val data: List<GetAllRequestsDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: GetAllRequestsMeta? = null
)

data class GetAllRequestsPagination(

	@field:SerializedName("pageCount")
	val pageCount: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("pageSize")
	val pageSize: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null
)

data class GetAllRequestsData(

	@field:SerializedName("attributes")
	val attributes: GetAllRequestsAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class GetAllRequestsJobRole(

	@field:SerializedName("data")
	val data: GetAllRequestsData? = null
)

data class UpdatedBy(

	@field:SerializedName("data")
	val data: GetAllRequestsData? = null
)

data class User(

	@field:SerializedName("data")
	val data: GetAllRequestsData? = null
)

data class Role(

	@field:SerializedName("data")
	val data: GetAllRequestsData? = null
)

data class GetAllRequestsDataItem(

	@field:SerializedName("attributes")
	val attributes: GetAllRequestsAttributes? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class GetAllRequestsAttributes(

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
	val user: User? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("role")
	val role: Role? = null,

	@field:SerializedName("job_role")
	val jobRole: GetAllRequestsJobRole? = null,

	@field:SerializedName("updatedBy")
	val updatedBy: UpdatedBy? = null,

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
	val createdBy: GetAllRequestsCreatedBy? = null,

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

data class GetAllRequestsCreatedBy(

	@field:SerializedName("data")
	val data: Any? = null
)

data class GetAllRequestsMeta(

	@field:SerializedName("pagination")
	val pagination: GetAllRequestsPagination? = null
)
