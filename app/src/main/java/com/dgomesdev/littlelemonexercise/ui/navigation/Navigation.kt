package com.dgomesdev.littlelemonexercise.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.dgomesdev.littlelemonexercise.ui.composables.Home
import com.dgomesdev.littlelemonexercise.ui.composables.Onboarding
import com.dgomesdev.littlelemonexercise.ui.composables.Profile
import com.dgomesdev.littlelemonexercise.ui.viewmodel.ProfileViewModel
import com.dgomesdev.littlelemonexercise.ui.viewmodel.UserState
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val userState by viewModel.user.collectAsState()
    if (userState is UserState.Loading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
        CircularProgressIndicator()
        }
    } else {
        val startDestination: NavKey = if (userState is UserState.Empty) {
            Destination.Onboarding
        } else {
            Destination.Home
        }
        val backStack = rememberNavBackStack(startDestination)
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Destination.Onboarding> {
                    Onboarding(
                        onLogIn = {
                            backStack.clear()
                            backStack.add(Destination.Home)
                        }
                    )
                }
                entry<Destination.Home> {
                    Home(onNavigation = {
                        backStack.add(Destination.Profile)
                    })
                }
                entry<Destination.Profile> {
                    Profile(
                        user = (userState as UserState.Success).user,
                        onLogOut = {
                            viewModel.logOut()
                            backStack.clear()
                            backStack.add(Destination.Onboarding)
                        }
                    )
                }
            },
            modifier = modifier.statusBarsPadding()
        )
    }
}
