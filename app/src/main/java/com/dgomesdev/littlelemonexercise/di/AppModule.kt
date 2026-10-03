package com.dgomesdev.littlelemonexercise.di

import com.dgomesdev.littlelemonexercise.ui.viewmodel.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { OnboardingViewModel() }
}
