package com.dgomesdev.littlelemonexercise.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.dgomesdev.littlelemonexercise.domain.model.MenuEntity

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItems(items: List<MenuEntity>)

    @Query("SELECT * FROM menu_items")
    suspend fun getMenuItems(): List<MenuEntity>
}