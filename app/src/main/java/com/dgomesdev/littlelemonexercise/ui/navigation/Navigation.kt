package com.dgomesdev.littlelemonexercise.ui.navigation

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.dgomesdev.littlelemonexercise.ui.composables.Home
import com.dgomesdev.littlelemonexercise.ui.composables.Onboarding
import com.dgomesdev.littlelemonexercise.ui.composables.Profile

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: NavKey = Destination.Onboarding,
    backStack: NavBackStack<NavKey> = rememberNavBackStack(startDestination)
) {
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Destination.Onboarding> {
                Onboarding()
            }
            entry<Destination.Home> {
                Home()
            }
            entry<Destination.Profile> {
                Profile()
            }
        },
        modifier = modifier.statusBarsPadding()
    )
}
