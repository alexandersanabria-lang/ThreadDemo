package com.alexander.threaddemo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexander.threaddemo.network.RetrofitClient
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConCorrutinaScreen(navController: NavController) {
    var status by remember { mutableStateOf("Presiona el botón para ver qué pasa") }
    var resultado by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🚀 Caso 4 — Corrutina + Retrofit", color = Color.White) },
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
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
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
                        "Usa corrutinas de Kotlin para ejecutar múltiples\n" +
                                "pasos de forma asíncrona con código LINEAL.\n\n" +
                                "Además hace una llamada REAL a internet con Retrofit\n" +
                                "y muestra los datos recibidos.",
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
                    text = "scope.launch {\n" +
                            "    // paso 1\n" +
                            "    withContext(Dispatchers.IO) {\n" +
                            "        delay(1500)\n" +
                            "    }\n" +
                            "    // paso 2\n" +
                            "    withContext(Dispatchers.IO) {\n" +
                            "        delay(1500)\n" +
                            "    }\n" +
                            "    // paso 3 — Retrofit\n" +
                            "    val post = RetrofitClient.api.getPost(1)\n" +
                            "    // actualiza UI directo ✅\n" +
                            "}",
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFF61AFEF),
                    fontSize = 12.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                )
            }

            // Botón
            Button(
                onClick = {
                    status = "🚀 Paso 1: descargando datos..."
                    cargando = true
                    resultado = ""
                    scope.launch {
                        try {
                            // Paso 1
                            withContext(Dispatchers.IO) { delay(1500) }
                            status = "🚀 Paso 2: procesando datos..."

                            // Paso 2
                            withContext(Dispatchers.IO) { delay(1500) }
                            status = "🚀 Paso 3: llamando a la API con Retrofit..."

                            // Paso 3 — llamada real a internet
                            val post = withContext(Dispatchers.IO) {
                                RetrofitClient.api.getPost(1)
                            }

                            cargando = false
                            status = "✅ Corrutina + Retrofit completado"
                            resultado = "✅ Datos reales de la API:\n\n" +
                                    "🔹 ID: ${post.id}\n" +
                                    "🔹 UserID: ${post.userId}\n" +
                                    "🔹 Título: ${post.title}\n\n" +
                                    "🔹 Cuerpo:\n${post.body}\n\n" +
                                    "✅ Código limpio, lineal y fácil de leer.\n" +
                                    "¡Esta es la forma moderna en Kotlin!"
                        } catch (e: Exception) {
                            cargando = false
                            status = "❌ Error de conexión"
                            resultado = "Error: ${e.message}\n\nVerifica que el emulador\ntiene acceso a internet."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2980B9))
            ) {
                Text("🚀 Ejecutar Corrutina + Retrofit", color = Color.White, fontSize = 15.sp)
            }

            if (cargando) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF2980B9)
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