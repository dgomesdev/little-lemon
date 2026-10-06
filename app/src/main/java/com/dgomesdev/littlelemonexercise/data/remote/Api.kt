package com.dgomesdev.littlelemonexercise.data.remote

import com.dgomesdev.littlelemonexercise.domain.model.MenuData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class Api(
    val httpClient: HttpClient,
) {
    suspend fun getMenu() = runCatching {
        httpClient.get(URL).body<MenuData>()
    }

    companion object {
        const val URL =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    }
}