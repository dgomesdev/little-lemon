package com.dgomesdev.littlelemonexercise.ui.navigation

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dgomesdev.littlelemonexercise.ui.composables.Home
import com.dgomesdev.littlelemonexercise.ui.composables.Onboarding
import com.dgomesdev.littlelemonexercise.ui.composables.Profile

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Destination.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            composable(
                route = Destination.Onboarding.route,
                arguments = Destination.Onboarding.arguments
            ) {
                Onboarding()
            }

            composable(
                route = Destination.Home.route,
                arguments = Destination.Home.arguments
            ) {
                Home()
            }

            composable(
                route = Destination.Profile.route,
                arguments = Destination.Profile.arguments
            ) {
                Profile()
            }
        },
        modifier = modifier.statusBarsPadding()
    )
}
