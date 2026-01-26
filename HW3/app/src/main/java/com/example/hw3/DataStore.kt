package com.example.hw3

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object UserPrefs {
    val NAME = stringPreferencesKey("user_name")
    val AVATAR_URI = stringPreferencesKey("user_avatar_uri")
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "preferences"
)

class PreferenceDataStore(context: Context) {
    private val appContext = context.applicationContext

    suspend fun userSetName(name: String) {
        appContext.dataStore.edit {
            it[UserPrefs.NAME] = name
        }
    }

    fun userGetName(): Flow<String?> {
        return appContext.dataStore.data.map {it[UserPrefs.NAME]}
    }

    suspend fun userSetAvatar(uri: String) {
        appContext.dataStore.edit {
            it[UserPrefs.AVATAR_URI] = uri
        }
    }

    fun userGetAvatar(): Flow<String?> {
        return appContext.dataStore.data.map {it[UserPrefs.AVATAR_URI]}
    }
}




