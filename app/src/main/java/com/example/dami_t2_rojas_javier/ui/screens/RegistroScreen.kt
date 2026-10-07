package com.example.dami_t2_rojas_javier.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.example.dami_t2_rojas_javier.ui.components.CampoSeleccion
import com.example.dami_t2_rojas_javier.ui.components.CampoTexto
import com.example.dami_t2_rojas_javier.ui.components.Encabezado
import com.example.dami_t2_rojas_javier.ui.components.OpcionesBottomSheet
import com.example.dami_t2_rojas_javier.ui.data.Prefs
import com.example.dami_t2_rojas_javier.ui.theme.Azul
import com.example.dami_t2_rojas_javier.ui.theme.Blanco
import com.example.dami_t2_rojas_javier.ui.theme.DAMI_T2_ROJAS_JAVIERTheme

@Composable
fun RegistroScreen(
    onRegistroExitoso: () -> Unit = {},
    onIrALogin: () -> Unit = {}
) {
    val context = LocalContext.current

    var nombres by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var estadoCivil by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }

    var mostrarGenero by remember { mutableStateOf(false) }
    var mostrarEstadoCivil by remember { mutableStateOf(false) }
    var intentado by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(Blanco)) {
        Encabezado(titulo = "Crear cuenta", subtitulo = "Completa tus datos para registrarte")

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CampoTexto(
                valor = nombres,
                alCambiar = { nombres = it },
                etiqueta = "Nombres",
                error = if (intentado && nombres.isBlank()) "Campo obligatorio" else null
            )
            CampoTexto(
                valor = apellidoPaterno,
                alCambiar = { apellidoPaterno = it },
                etiqueta = "Apellido paterno",
                error = if (intentado && apellidoPaterno.isBlank()) "Campo obligatorio" else null
            )
            CampoTexto(
                valor = apellidoMaterno,
                alCambiar = { apellidoMaterno = it },
                etiqueta = "Apellido materno",
                error = if (intentado && apellidoMaterno.isBlank()) "Campo obligatorio" else null
            )
            CampoTexto(
                valor = dni,
                alCambiar = { if (it.length <= 8 && it.all { c -> c.isDigit() }) dni = it },
                etiqueta = "DNI",
                teclado = KeyboardType.Number,
                error = if (intentado && dni.length != 8) "El DNI debe tener 8 dígitos" else null
            )
            CampoSeleccion(
                valor = genero,
                etiqueta = "Género",
                error = if (intentado && genero.isBlank()) "Seleccione un género" else null,
                alTocar = { mostrarGenero = true }
            )
            CampoTexto(
                valor = celular,
                alCambiar = { if (it.length <= 9 && it.all { c -> c.isDigit() }) celular = it },
                etiqueta = "N° de celular",
                teclado = KeyboardType.Phone,
                prefijo = "+51 ",
                error = if (intentado && celular.length != 9) "El celular debe tener 9 dígitos" else null
            )
            CampoTexto(
                valor = correo,
                alCambiar = { correo = it },
                etiqueta = "Correo electrónico",
                teclado = KeyboardType.Email,
                error = if (intentado && !correo.contains("@")) "Ingrese un correo válido" else null
            )
            CampoTexto(
                valor = peso,
                alCambiar = { peso = it },
                etiqueta = "Peso (kg)",
                teclado = KeyboardType.Decimal,
                error = if (intentado && peso.isBlank()) "Campo obligatorio" else null
            )
            CampoTexto(
                valor = altura,
                alCambiar = { altura = it },
                etiqueta = "Altura (m)",
                teclado = KeyboardType.Decimal,
                error = if (intentado && altura.isBlank()) "Campo obligatorio" else null
            )
            CampoSeleccion(
                valor = estadoCivil,
                etiqueta = "Estado civil",
                error = if (intentado && estadoCivil.isBlank()) "Seleccione un estado civil" else null,
                alTocar = { mostrarEstadoCivil = true }
            )
            CampoTexto(
                valor = otp,
                alCambiar = { if (it.length <= 6 && it.all { c -> c.isDigit() }) otp = it },
                etiqueta = "Código OTP (6 dígitos)",
                teclado = KeyboardType.NumberPassword,
                esClave = true,
                error = if (intentado && otp.length != 6) "El OTP debe tener 6 dígitos" else null
            )

            Button(
                onClick = {
                    intentado = true
                    val todoValido = nombres.isNotBlank() &&
                        apellidoPaterno.isNotBlank() &&
                        apellidoMaterno.isNotBlank() &&
                        dni.length == 8 &&
                        genero.isNotBlank() &&
                        celular.length == 9 &&
                        correo.contains("@") &&
                        peso.isNotBlank() &&
                        altura.isNotBlank() &&
                        estadoCivil.isNotBlank() &&
                        otp.length == 6
                    if (todoValido) {
                        Prefs.abrir(context).edit()
                            .putString("nombres", nombres)
                            .putString("apellidoPaterno", apellidoPaterno)
                            .putString("apellidoMaterno", apellidoMaterno)
                            .putString("dni", dni)
                            .putString("genero", genero)
                            .putString("celular", celular)
                            .putString("correo", correo)
                            .putString("peso", peso)
                            .putString("altura", altura)
                            .putString("estadoCivil", estadoCivil)
                            .putString("otp", otp)
                            .apply()
                        onRegistroExitoso()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Registrarse")
            }

            TextButton(onClick = onIrALogin, modifier = Modifier.fillMaxWidth()) {
                Text("¿Ya tienes cuenta? Inicia sesión", color = Azul)
            }
        }
    }

    if (mostrarGenero) {
        OpcionesBottomSheet(
            titulo = "Seleccionar género",
            opciones = listOf("Masculino", "Femenino", "No especificado"),
            alElegir = {
                genero = it
                mostrarGenero = false
            },
            alCerrar = { mostrarGenero = false }
        )
    }

    if (mostrarEstadoCivil) {
        OpcionesBottomSheet(
            titulo = "Seleccionar estado civil",
            opciones = listOf("Soltero", "Casado", "Viudo", "Divorciado"),
            alElegir = {
                estadoCivil = it
                mostrarEstadoCivil = false
            },
            alCerrar = { mostrarEstadoCivil = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    DAMI_T2_ROJAS_JAVIERTheme {
        RegistroScreen()
    }
}
