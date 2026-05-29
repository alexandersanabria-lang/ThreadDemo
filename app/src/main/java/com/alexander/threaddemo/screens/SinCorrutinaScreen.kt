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
fun SinCorrutinaScreen(navController: NavController) {
    var status by remember { mutableStateOf("Presiona el botón para ver qué pasa") }
    var resultado by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⚙️ Caso 3 — Sin Corrutina", color = Color.White) },
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
                        "Usa Threads anidados con Handler para simular\n" +
                                "múltiples operaciones encadenadas.\n\n" +
                                "Funciona, pero el código se vuelve difícil de leer,\n" +
                                "mantener y depurar. Esto se llama 'Callback Hell'.",
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
                            "    // paso 1\n" +
                            "    handler.post {\n" +
                            "        Thread {\n" +
                            "            // paso 2\n" +
                            "            handler.post {\n" +
                            "                // paso 3...\n" +
                            "            }\n" +
                            "        }.start()\n" +
                            "    }\n" +
                            "}.start() // Callback Hell",
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFFE5C07B),
                    fontSize = 12.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            }

            // Botón
            Button(
                onClick = {
                    status = "⚙️ Paso 1: descargando datos..."
                    cargando = true
                    resultado = ""
                    val handler = Handler(Looper.getMainLooper())
                    Thread {
                        Thread.sleep(1500)
                        handler.post {
                            status = "Paso 2: procesando datos..."
                            Thread {
                                Thread.sleep(1500)
                                handler.post {
                                    status = "Paso 3: guardando resultados..."
                                    Thread {
                                        Thread.sleep(1000)
                                        handler.post {
                                            cargando = false
                                            status = "Completado con Handlers"
                                            resultado = "Funcionó pero mira el código.\n\n" +
                                                    "3 pasos = 3 niveles de callbacks anidados.\n\n" +
                                                    "Imagina 10 pasos... ¡imposible de mantener!\n\n" +
                                                    "A esto se le llama 'Callback Hell'. "
                                        }
                                    }.start()
                                }
                            }.start()
                        }
                    }.start()
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE67E22))
            ) {
                Text("⚙️ Ejecutar Sin Corrutina", color = Color.White, fontSize = 15.sp)
            }

            if (cargando) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFE67E22)
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