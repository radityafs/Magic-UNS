package com.magic.officeapp.data.repository

import androidx.datastore.core.DataStore
import com.google.gson.Gson
import com.magic.officeapp.data.model.User
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val userData = stringPreferencesKey("UserData_Key")

    suspend fun getUserData(): User? {
        return dataStore.data.map {
            Gson().fromJson(it[userData], User::class.java)
        }.first()
    }

    suspend fun saveUserData(user: User) {
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