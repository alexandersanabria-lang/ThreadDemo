package com.alexander.threaddemo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0F0F1A)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("⚡ Thread Demo", fontSize = 32.sp,
                fontWeight = FontWeight.Bold, color = Color(0xFFE0E0FF))
            Text("Elige un caso para explorar",
                fontSize = 14.sp, color = Color(0xFF7B7B9E))

            Spacer(modifier = Modifier.height(48.dp))

            // Botón 1
            Button(
                onClick = { navController.navigate("sin_hilo") },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC0392B))
            ) {
                Text(" Caso 1 — Sin Hilo", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón 2
            Button(
                onClick = { navController.navigate("con_hilo") },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27AE60))
            ) {
                Text(" Caso 2 — Con Hilo Thread", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón 3
            Button(
                onClick = { navController.navigate("sin_corrutina") },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE67E22))
            ) {
                Text(" Caso 3 — Sin Corrutina", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón 4
            Button(
                onClick = { navController.navigate("con_corrutina") },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2980B9))
            ) {
                Text("Caso 4 — Corrutina + Retrofit", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}