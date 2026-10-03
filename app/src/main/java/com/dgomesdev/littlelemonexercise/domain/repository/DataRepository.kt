package com.dgomesdev.littlelemonexercise.domain.repository

import com.dgomesdev.littlelemonexercise.data.local.DataPreferences
import com.dgomesdev.littlelemonexercise.domain.model.User
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val dataPreferences: DataPreferences
) {
    fun isUserLoggedIn(): Flow<Boolean> {
        return dataPreferences.isUserLoggedIn
    }

    suspend fun saveUser(user: User) {
        dataPreferences.saveUser(user)
    }

    suspend fun logOut() {
        dataPreferences.logOut()
    }
}