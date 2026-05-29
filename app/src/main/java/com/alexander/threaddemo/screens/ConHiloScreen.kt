package com.alexander.threaddemo.screens

import android.os.Handler
import android.os.Looper
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
fun ConHiloScreen(navController: NavController) {
    var status by remember { mutableStateOf("Presiona el botón para ver qué pasa") }
    var resultado by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Caso 2 — Con Hilo Thread", color = Color.White) },
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
                        "Crea un Thread secundario para el trabajo pesado.\n\n" +
                                "La UI sigue respondiendo porque el hilo principal queda libre.\n\n" +
                                "Para actualizar la UI se usa Handler(Looper.getMainLooper()).",
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
                    text = "Thread {\n" +
                            "    Thread.sleep(3000) // segundo plano\n" +
                            "    Handler(Looper.getMainLooper()).post {\n" +
                            "        // actualiza la UI\n" +
                            "    }\n" +
                            "}.start()",
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFF98C379),
                    fontSize = 13.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            }

            // Botón
            Button(
                onClick = {
                    status = "Trabajando en segundo plano...\nintenta tocar la pantalla"
                    cargando = true
                    resultado = ""
                    Thread {
                        Thread.sleep(3000)
                        Handler(Looper.getMainLooper()).post {
                            cargando = false
                            status = "Thread terminó correctamente"
                            resultado = " ¿Pudiste tocar la pantalla durante la espera?\n\nSÍ. El trabajo se hizo en un hilo separado.\n\nSe usó Handler para volver al hilo principal y actualizar la UI."
                        }
                    }.start()
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27AE60))
            ) {
                Text(" Ejecutar Con Hilo Thread", color = Color.White, fontSize = 15.sp)
            }

            if (cargando) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF27AE60)
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