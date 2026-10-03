package com.dgomesdev.littlelemonexercise.ui.navigation

import androidx.navigation3.runtime.NavKey

sealed interface Destination : NavKey {
    data object Onboarding : Destination
    data object Home : Destination
    data object Profile : Destination
}
