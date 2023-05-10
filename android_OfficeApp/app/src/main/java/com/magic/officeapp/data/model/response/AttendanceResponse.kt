package com.magic.officeapp.data.model.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class AttendanceResponse(
	val data: List<DataItem?>? = null,
	val meta: Meta? = null
) : Parcelable

@Parcelize
data class Pagination(
	val pageCount: Int? = null,
	val total: Int? = null,
	val pageSize: Int? = null,
	val page: Int? = null
) : Parcelable

@Parcelize
data class AttendanceResponseUser(
	val data: Data? = null
) : Parcelable

@Parcelize
data class Data(
	val attributes: Attributes? = null,
	val id: Int? = null
) : Parcelable

@Parcelize
data class DataItem(
	val attributes: Attributes? = null,
	val id: Int? = null
) : Parcelable

@Parcelize
data class Meta(
	val pagination: Pagination? = null
) : Parcelable

@Parcelize
data class Attributes(
	val createdAt: String? = null,
	val publishedAt: String? = null,
	val latitude: String? = null,
	val user: AttendanceResponseUser? = null,
	val updatedAt: String? = null,
	val longitude: String? = null,
	val jobRole: String? = null,
	val blocked: Boolean? = null,
	val provider: String? = null,
	val phone: String? = null,
	val salary: String? = null,
	val confirmed: Boolean? = null,
	val email: String? = null,
	val username: String? = null
) : Parcelable
