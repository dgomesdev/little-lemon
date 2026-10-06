package com.dgomesdev.littlelemonexercise.domain.repository

import com.dgomesdev.littlelemonexercise.data.local.DataPreferences
import com.dgomesdev.littlelemonexercise.data.remote.Api
import com.dgomesdev.littlelemonexercise.domain.model.User

class DataRepository(
    private val dataPreferences: DataPreferences,
    private val api: Api
) {
    fun getUser() = dataPreferences.getUser()

    suspend fun saveUser(user: User) {
        dataPreferences.saveUser(user)
    }

    suspend fun logOut() {
        dataPreferences.logOut()
    }

    suspend fun getMenu() = api.getMenu()

}