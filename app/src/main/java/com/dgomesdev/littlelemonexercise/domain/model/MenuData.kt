package com.dgomesdev.littlelemonexercise.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuData(
    @SerialName("menu")
    val items: List<MenuItem>,
)
