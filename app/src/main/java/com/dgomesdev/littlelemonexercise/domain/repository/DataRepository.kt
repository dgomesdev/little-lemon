package com.dgomesdev.littlelemonexercise.domain.repository

import com.dgomesdev.littlelemonexercise.data.local.DataPreferences
import com.dgomesdev.littlelemonexercise.domain.model.User

class DataRepository(
    private val dataPreferences: DataPreferences
) {
    suspend fun getUser(): User {
        return dataPreferences.getUser()
    }

    suspend fun saveUser(user: User) {
        dataPreferences.saveUser(user)
    }

    suspend fun logOut() {
        dataPreferences.logOut()
    }
}