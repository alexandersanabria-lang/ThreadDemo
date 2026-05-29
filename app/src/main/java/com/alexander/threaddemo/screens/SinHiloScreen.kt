package com.alexander.threaddemo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SinHiloScreen(navController: NavController) {
    var status by remember { mutableStateOf("Presiona el botón para ver qué pasa") }
    var resultado by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(" Caso 1 — Sin Hilo", color = Color.White) },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("← Volver", color = Color(0xFF6C63FF))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A2E)
                )
            )
        },
        containerColor = Color(0xFF0F0F1A)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Explicación
            Surface(
                color = Color(0xFF1A1A2E),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("¿Qué hace?", fontWeight = FontWeight.Bold,
                        color = Color(0xFF6C63FF), fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Ejecuta Thread.sleep() directamente en el hilo principal (UI Thread).\n\n" +
                                "El hilo principal es el responsable de dibujar la interfaz, " +
                                "por lo que bloquearlo congela toda la app.",
                        color = Color(0xFFA0A0C0), fontSize = 13.sp, lineHeight = 20.sp
                    )
                }
            }

            // Código
            Surface(
                color = Color(0xFF161625),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "// Bloquea el hilo principal\nThread.sleep(3000)",
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFFE06C75),
                    fontSize = 13.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            }

            // Botón
            Button(
                onClick = {
                    status = "Ejecutando... intenta tocar la pantalla"
                    cargando = true
                    resultado = ""
                    Thread.sleep(3000) // Congela todo
                    cargando = false
                    status = "Terminó después de 3 segundos"
                    resultado = " ¿Pudiste tocar algo durante la espera?\n\nNO. La UI estuvo completamente congelada.\n\nEsto puede causar un ANR (App Not Responding)."
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC0392B))
            ) {
                Text(" Ejecutar Sin Hilo", color = Color.White, fontSize = 15.sp)
            }

            if (cargando) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFC0392B)
                )
            }

            // Status
            Surface(
                color = Color(0xFF1A1A2E),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = status,
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFFC0C0E0), fontSize = 14.sp
                )
            }

            // Resultado
            if (resultado.isNotEmpty()) {
                Surface(
                    color = Color(0xFF161625),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = resultado,
                        modifier = Modifier.padding(16.dp),
                        color = Color(0xFFA0A0C0), fontSize = 13.sp, lineHeight = 20.sp
                    )
                }
            }
        }
    }
}