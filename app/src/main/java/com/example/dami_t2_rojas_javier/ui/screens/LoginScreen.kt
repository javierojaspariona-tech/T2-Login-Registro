package com.example.dami_t2_rojas_javier.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dami_t2_rojas_javier.ui.components.CampoTexto
import com.example.dami_t2_rojas_javier.ui.components.Encabezado
import com.example.dami_t2_rojas_javier.ui.data.Prefs
import com.example.dami_t2_rojas_javier.ui.theme.Azul
import com.example.dami_t2_rojas_javier.ui.theme.Blanco
import com.example.dami_t2_rojas_javier.ui.theme.DAMI_T2_ROJAS_JAVIERTheme

@Composable
fun LoginScreen(
    onLoginExitoso: () -> Unit = {},
    onIrARegistro: () -> Unit = {}
) {
    val context = LocalContext.current
    var telefono by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var intentado by remember { mutableStateOf(false) }
    var credencialesMal by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(Blanco)) {
        Encabezado(titulo = "Bienvenido", subtitulo = "Inicia sesión para continuar")

        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CampoTexto(
                valor = telefono,
                alCambiar = { if (it.length <= 9 && it.all { c -> c.isDigit() }) telefono = it },
                etiqueta = "Teléfono",
                teclado = KeyboardType.Phone,
                prefijo = "+51 ",
                error = if (intentado && telefono.length != 9) "El teléfono debe tener 9 dígitos" else null
            )
            CampoTexto(
                valor = otp,
                alCambiar = { if (it.length <= 6 && it.all { c -> c.isDigit() }) otp = it },
                etiqueta = "Código OTP",
                teclado = KeyboardType.NumberPassword,
                esClave = true,
                error = if (intentado && otp.length != 6) "El OTP debe tener 6 dígitos" else null
            )

            if (credencialesMal) {
                Text(
                    text = "Teléfono o código incorrecto",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = {
                    intentado = true
                    if (telefono.length == 9 && otp.length == 6) {
                        val prefs = Prefs.abrir(context)
                        val celularGuardado = prefs.getString("celular", "")
                        val otpGuardado = prefs.getString("otp", "")
                        if (telefono == celularGuardado && otp == otpGuardado) {
                            credencialesMal = false
                            onLoginExitoso()
                        } else {
                            credencialesMal = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Ingresar")
            }

            TextButton(onClick = onIrARegistro, modifier = Modifier.fillMaxWidth()) {
                Text("¿No tienes cuenta? Regístrate", color = Azul)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DAMI_T2_ROJAS_JAVIERTheme {
        LoginScreen()
    }
}
