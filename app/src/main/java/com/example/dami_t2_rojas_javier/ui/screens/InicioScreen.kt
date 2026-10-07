package com.example.dami_t2_rojas_javier.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dami_t2_rojas_javier.ui.components.Encabezado
import com.example.dami_t2_rojas_javier.ui.data.Prefs
import com.example.dami_t2_rojas_javier.ui.theme.Blanco
import com.example.dami_t2_rojas_javier.ui.theme.DAMI_T2_ROJAS_JAVIERTheme
import com.example.dami_t2_rojas_javier.ui.theme.Negro

@Composable
fun InicioScreen() {
    val context = LocalContext.current
    val nombres = Prefs.abrir(context).getString("nombres", "") ?: ""

    Column(modifier = Modifier.fillMaxSize().background(Blanco)) {
        Encabezado(titulo = "Inicio", subtitulo = "Todo listo para empezar")

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Bienvenido a la aplicación, $nombres!",
                color = Negro,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {
    DAMI_T2_ROJAS_JAVIERTheme {
        InicioScreen()
    }
}
