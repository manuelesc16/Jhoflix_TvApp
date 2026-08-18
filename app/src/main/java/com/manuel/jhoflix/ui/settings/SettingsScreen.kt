package com.manuel.jhoflix.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manuel.jhoflix.ui.theme.JhoflixAccent
import com.manuel.jhoflix.ui.theme.JhoflixBackground
import com.manuel.jhoflix.ui.theme.JhoflixTextPrimary
import com.manuel.jhoflix.ui.theme.JhoflixTextSecondary
import com.manuel.jhoflix.viewmodel.ConnectionTestState
import com.manuel.jhoflix.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = viewModel()
) {
    val serverUrl by viewModel.serverUrl.collectAsState()
    val testState by viewModel.connectionTestState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(JhoflixBackground)
            .padding(48.dp)
    ) {
        Column {
            Text(
                text = "Configuración del servidor",
                color = JhoflixTextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Dirección del servidor",
                color = JhoflixTextSecondary,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
            )

            OutlinedTextField(
                value = serverUrl,
                onValueChange = { viewModel.onUrlChanged(it) },
                placeholder = { Text("http://192.168.1.100:5000") },
                modifier = Modifier.width(420.dp),
                singleLine = true
            )

            Row(
                modifier = Modifier.padding(top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { viewModel.saveUrl() },
                    colors = ButtonDefaults.buttonColors(containerColor = JhoflixAccent)
                ) {
                    Text("Guardar")
                }

                OutlinedButton(onClick = { viewModel.testConnection() }) {
                    Text("Probar conexión")
                }
            }

            Box(modifier = Modifier.padding(top = 20.dp)) {
                when (testState) {
                    ConnectionTestState.TESTING -> Row {
                        CircularProgressIndicator(
                            color = JhoflixAccent,
                            modifier = Modifier.width(20.dp)
                        )
                        Text(
                            "  Probando conexión...",
                            color = JhoflixTextSecondary,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    ConnectionTestState.SUCCESS -> Text(
                        "✔ Conexión exitosa",
                        color = Color(0xFF4CAF50)
                    )
                    ConnectionTestState.FAILED -> Text(
                        "✘ No se pudo conectar con el servidor",
                        color = Color(0xFFFF6B6B)
                    )
                    ConnectionTestState.IDLE -> {}
                }
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            ) {
                Text("Volver")
            }
        }
    }
}
