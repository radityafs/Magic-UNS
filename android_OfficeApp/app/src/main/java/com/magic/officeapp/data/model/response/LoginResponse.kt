package com.magic.officeapp.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class LoginResponse(
    val jwt: String,
    val user: User,
) : Parcelable

@Parcelize
data class User(
    val address: String? = null,
    val salary: String? = null,
    val confirmed: Boolean? = null,
    val createdAt: String? = null,
    val job_role : String? = null,
    val blocked: Boolean? = null,
    val provider: String? = null,
    val phone: String? = null,
    val id: Int? = null,
    val email: String? = null,
    val username: String? = null,
    val updatedAt: String? = null
) : Parcelable
