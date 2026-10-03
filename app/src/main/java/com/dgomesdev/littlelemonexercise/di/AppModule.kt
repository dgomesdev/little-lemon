package com.dgomesdev.littlelemonexercise.di

import com.dgomesdev.littlelemonexercise.data.local.DataPreferences
import com.dgomesdev.littlelemonexercise.domain.repository.DataRepository
import com.dgomesdev.littlelemonexercise.ui.viewmodel.OnboardingViewModel
import com.dgomesdev.littlelemonexercise.ui.viewmodel.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { DataPreferences(androidContext()) }
    single { DataRepository(get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}
