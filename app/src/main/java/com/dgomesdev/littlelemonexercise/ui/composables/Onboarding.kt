package com.dgomesdev.littlelemonexercise.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dgomesdev.littlelemonexercise.R
import com.dgomesdev.littlelemonexercise.ui.theme.LittleLemonExerciseTheme

@Composable
fun Onboarding(modifier: Modifier = Modifier) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = modifier
        )
        Text("Let's get to know you")
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
        )
        Button(
            onClick = { },
            modifier = modifier
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