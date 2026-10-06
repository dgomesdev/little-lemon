package com.dgomesdev.littlelemonexercise.di

import com.dgomesdev.littlelemonexercise.data.local.DataPreferences
import com.dgomesdev.littlelemonexercise.data.remote.Api
import com.dgomesdev.littlelemonexercise.domain.repository.DataRepository
import com.dgomesdev.littlelemonexercise.ui.viewmodel.OnboardingViewModel
import com.dgomesdev.littlelemonexercise.ui.viewmodel.ProfileViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
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
    single { Api(get()) }
    single { DataPreferences(androidContext()) }
    single { DataRepository(get(), get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}
