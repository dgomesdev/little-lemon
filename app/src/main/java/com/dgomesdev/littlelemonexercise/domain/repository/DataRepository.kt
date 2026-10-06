package com.dgomesdev.littlelemonexercise.domain.repository

import android.util.Log
import com.dgomesdev.littlelemonexercise.data.local.DataPreferences
import com.dgomesdev.littlelemonexercise.data.local.MenuDao
import com.dgomesdev.littlelemonexercise.data.remote.Api
import com.dgomesdev.littlelemonexercise.domain.model.MenuEntity
import com.dgomesdev.littlelemonexercise.domain.model.User

class DataRepository(
    private val dataPreferences: DataPreferences,
    private val api: Api,
    private val dao: MenuDao
) {
    fun getUser() = dataPreferences.getUser()

    suspend fun saveUser(user: User) {
        dataPreferences.saveUser(user)
    }

    suspend fun logOut() {
        dataPreferences.logOut()
    }

    suspend fun getMenu(): Result<List<MenuEntity>> {
        return api.getMenu().mapCatching { menuData ->
            val entities = menuData.items.map(::MenuEntity)
            dao.saveItems(entities)
            entities
        }.recoverCatching { exception ->
            Log.e("DataRepository", "getMenu", exception)
            val cachedItems = dao.getMenuItems()
            cachedItems
        }
    }
}