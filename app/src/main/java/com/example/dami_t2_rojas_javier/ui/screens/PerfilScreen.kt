package com.example.dami_t2_rojas_javier.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dami_t2_rojas_javier.ui.components.Encabezado
import com.example.dami_t2_rojas_javier.ui.components.FilaDato
import com.example.dami_t2_rojas_javier.ui.data.Prefs
import com.example.dami_t2_rojas_javier.ui.theme.Azul
import com.example.dami_t2_rojas_javier.ui.theme.Blanco
import com.example.dami_t2_rojas_javier.ui.theme.DAMI_T2_ROJAS_JAVIERTheme
import com.example.dami_t2_rojas_javier.ui.theme.PlomoClaro

@Composable
fun PerfilScreen(
    onCerrarSesion: () -> Unit = {}
) {
    val context = LocalContext.current
    val prefs = Prefs.abrir(context)

    val nombres = prefs.getString("nombres", "") ?: ""
    val apellidoPaterno = prefs.getString("apellidoPaterno", "") ?: ""
    val apellidoMaterno = prefs.getString("apellidoMaterno", "") ?: ""
    val dni = prefs.getString("dni", "") ?: ""
    val genero = prefs.getString("genero", "") ?: ""
    val celular = prefs.getString("celular", "") ?: ""
    val correo = prefs.getString("correo", "") ?: ""
    val peso = prefs.getString("peso", "") ?: ""
    val altura = prefs.getString("altura", "") ?: ""
    val estadoCivil = prefs.getString("estadoCivil", "") ?: ""

    Column(modifier = Modifier.fillMaxSize().background(Blanco)) {
        Encabezado(titulo = "Mi perfil", subtitulo = "$nombres $apellidoPaterno")

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = PlomoClaro)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FilaDato("Nombres", nombres)
                    FilaDato("Apellido paterno", apellidoPaterno)
                    FilaDato("Apellido materno", apellidoMaterno)
                    FilaDato("DNI", dni)
                    FilaDato("Género", genero)
                    FilaDato("Celular", "+51 $celular")
                    FilaDato("Correo", correo)
                    FilaDato("Peso", "$peso kg")
                    FilaDato("Altura", "$altura m")
                    FilaDato("Estado civil", estadoCivil)
                }
            }

            OutlinedButton(
                onClick = onCerrarSesion,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión", color = Azul)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    DAMI_T2_ROJAS_JAVIERTheme {
        PerfilScreen()
    }
}
