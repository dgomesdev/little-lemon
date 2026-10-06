package com.dgomesdev.littlelemonexercise.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dgomesdev.littlelemonexercise.R
import com.dgomesdev.littlelemonexercise.domain.model.User

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    user: User,
    onLogOut: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo"
        )
        Text(
            text = "Profile information"
        )
        Text(
            text = user.firstName
        )
        Text(
            text = user.lastName
        )
        Text(
            text = user.email
        )
        Button(
            onClick = { onLogOut() }
        ) {
            Text("Log out")
        }
    }
}