package com.dgomesdev.littlelemonexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.dgomesdev.littlelemonexercise.ui.navigation.NavGraph
import com.dgomesdev.littlelemonexercise.ui.theme.LittleLemonExerciseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonExerciseTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}