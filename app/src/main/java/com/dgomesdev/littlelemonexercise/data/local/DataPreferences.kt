package com.dgomesdev.littlelemonexercise.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dgomesdev.littlelemonexercise.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_data")

class DataPreferences(private val context: Context) {
    companion object {
        val FIRST_NAME = stringPreferencesKey("firstName")
        val LAST_NAME = stringPreferencesKey("lastName")
        val EMAIL = stringPreferencesKey("email")
    }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_NAME] = user.firstName
            preferences[LAST_NAME] = user.lastName
            preferences[EMAIL] = user.email
        }
    }

    val isUserLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[FIRST_NAME]?.isNotBlank() == true &&
                    preferences[LAST_NAME]?.isNotBlank() == true &&
                    preferences[EMAIL]?.isNotBlank() == true
        }

    suspend fun logOut() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}