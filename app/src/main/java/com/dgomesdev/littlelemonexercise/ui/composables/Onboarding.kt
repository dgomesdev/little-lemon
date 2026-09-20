package com.dgomesdev.littlelemonexercise.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dgomesdev.littlelemonexercise.R
import com.dgomesdev.littlelemonexercise.ui.viewmodel.OnboardingViewModel
import com.dgomesdev.littlelemonexercise.ui.theme.LittleLemonExerciseTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun Onboarding(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val email by viewModel.email.collectAsState()

    val isFirstNameValid by viewModel.isFirstNameValid.collectAsState()
    val isLastNameValid by viewModel.isLastNameValid.collectAsState()
    val isEmailValid by viewModel.isEmailValid.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentScale = ContentScale.FillWidth
        )
        Text("Let's get to know you")
        TextField(
            value = firstName,
            onValueChange = {
                viewModel.updateFirstName(it)
            },
            label = { Text("First Name") },
            isError = isFirstNameValid
        )
        TextField(
            value = lastName,
            onValueChange = {
                viewModel.updateLastName(it)
            },
            label = { Text("Last Name") },
            isError = isLastNameValid
        )
        TextField(
            value = email,
            onValueChange = {
                viewModel.updateEmail(it)
            },
            label = { Text("Email") },
            isError = isEmailValid
        )
        Button(
            onClick = { },
            modifier = modifier,
            enabled = isFormValid
        ) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingPrev() {
    LittleLemonExerciseTheme {
        Onboarding()
    }
}