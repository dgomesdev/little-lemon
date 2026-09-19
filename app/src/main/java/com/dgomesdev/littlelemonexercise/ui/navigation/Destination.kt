package com.dgomesdev.littlelemonexercise.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class Destination(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    data object OnBoarding: Destination(Route.ONBOARDING)
    data object Home: Destination(Route.HOME)
    data object Profile: Destination(Route.PROFILE)
}

private object Route {
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val PROFILE = "profile"
}