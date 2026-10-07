package com.dgomesdev.littlelemonexercise.di

import androidx.room3.Room
import com.dgomesdev.littlelemonexercise.data.local.DataPreferences
import com.dgomesdev.littlelemonexercise.data.local.MenuDatabase
import com.dgomesdev.littlelemonexercise.data.remote.Api
import com.dgomesdev.littlelemonexercise.domain.repository.DataRepository
import com.dgomesdev.littlelemonexercise.ui.viewmodel.HomeViewModel
import com.dgomesdev.littlelemonexercise.ui.viewmodel.OnboardingViewModel
import com.dgomesdev.littlelemonexercise.ui.viewmodel.ProfileViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Text.Plain,
                )
            }
        }
    }
    single {
        Room.databaseBuilder<MenuDatabase>(
            context = androidContext(),
            name = "little_lemon.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<MenuDatabase>().menuDao() }
    single { DataPreferences(androidContext()) }
    singleOf(::Api)
    singleOf(::DataRepository)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::HomeViewModel)
}
