package com.dgomesdev.littlelemonexercise.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.dgomesdev.littlelemonexercise.domain.model.MenuEntity

@Database(entities = [MenuEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}