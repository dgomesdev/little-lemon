package com.dgomesdev.littlelemonexercise.domain.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
) {
    constructor(menu: MenuItem) :
            this(
                menu.id,
                menu.title,
                menu.description,
                menu.price,
                menu.image,
                menu.category
            )
}
