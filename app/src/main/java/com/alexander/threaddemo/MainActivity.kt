package com.alexander.threaddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexander.threaddemo.screens.HomeScreen
import com.alexander.threaddemo.screens.SinHiloScreen
import com.alexander.threaddemo.screens.ConHiloScreen
import com.alexander.threaddemo.screens.SinCorrutinaScreen
import com.alexander.threaddemo.screens.ConCorrutinaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable("sin_hilo") {
                        SinHiloScreen(navController)
                    }
                    composable("con_hilo") {
                        ConHiloScreen(navController)
                    }
                    composable("sin_corrutina") {
                        SinCorrutinaScreen(navController)
                    }
                    composable("con_corrutina") {
                        ConCorrutinaScreen(navController)
                    }
                }
            }
        }
    }
}