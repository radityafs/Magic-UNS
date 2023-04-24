package com.magic.officeapp.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class LoginResponse(
    val jwt: String? = null,
    val user: User? = null
) : Parcelable

@Parcelize
data class User(
    val address: String? = null,
    val salary: String? = null,
    val confirmed: Boolean? = null,
    val lastEducation: String? = null,
    val createdAt: String? = null,
    val nIK: String? = null,
    val permit: Int? = null,
    val blocked: Boolean? = null,
    val provider: String? = null,
    val phone: String? = null,
    val dob: String? = null,
    val id: Int? = null,
    val email: String? = null,
    val username: String? = null,
    val updatedAt: String? = null
) : Parcelable
