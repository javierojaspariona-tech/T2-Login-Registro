package com.example.dami_t2_rojas_javier.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EsquemaColores = lightColorScheme(
    primary = Azul,
    onPrimary = Blanco,
    secondary = Celeste,
    background = Blanco,
    surface = Blanco,
    onSurface = Negro
)

@Composable
fun DAMI_T2_ROJAS_JAVIERTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = EsquemaColores,
        typography = Typography,
        content = content
    )
}
