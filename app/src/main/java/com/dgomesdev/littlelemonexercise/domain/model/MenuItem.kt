package com.dgomesdev.littlelemonexercise.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)