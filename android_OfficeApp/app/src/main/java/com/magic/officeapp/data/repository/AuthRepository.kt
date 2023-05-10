package com.magic.officeapp.data.repository

import androidx.datastore.core.DataStore
import com.google.gson.Gson
import com.magic.officeapp.data.model.User
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.magic.officeapp.data.api.ApiAuthInterface
import com.magic.officeapp.data.model.LoginResponse
import com.magic.officeapp.data.model.request.LoginRequest
import com.magic.officeapp.data.model.response.DetailUserResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.magic.officeapp.utils.constants.Result


class AuthRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val apiService: ApiAuthInterface
) {
    private val userData = stringPreferencesKey("UserData_Key")
    suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            Result.Success(apiService.login(LoginRequest(email, password)))
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getDetails(
        token : String
    ): Result<DetailUserResponse> {
        return try {
            Result.Success(apiService.getDetails(
                "Bearer $token"
            ))
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getUserData(): DetailUserResponse? {
        return dataStore.data.map {
            Gson().fromJson(it[userData], DetailUserResponse::class.java)
        }.first()
    }

    suspend fun saveUserData(user: DetailUserResponse) {
        dataStore.edit {
            it[userData] = Gson().toJson(user)
        }
    }

    suspend fun clearUserData() {
        dataStore.edit {
            it[userData] = ""
        }
    }

}