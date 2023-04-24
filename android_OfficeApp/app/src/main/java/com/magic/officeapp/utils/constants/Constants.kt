package com.magic.officeapp.utils.constants

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class Constants {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_preferences")
    }
}