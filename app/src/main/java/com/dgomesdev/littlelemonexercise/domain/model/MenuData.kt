package com.dgomesdev.littlelemonexercise.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuData(
    val items: List<MenuItem>,
)
