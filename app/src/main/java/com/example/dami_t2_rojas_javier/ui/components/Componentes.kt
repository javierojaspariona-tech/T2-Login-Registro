package com.example.dami_t2_rojas_javier.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.dami_t2_rojas_javier.ui.theme.Azul
import com.example.dami_t2_rojas_javier.ui.theme.Blanco
import com.example.dami_t2_rojas_javier.ui.theme.Celeste
import com.example.dami_t2_rojas_javier.ui.theme.Negro
import com.example.dami_t2_rojas_javier.ui.theme.Plomo
import com.example.dami_t2_rojas_javier.ui.theme.PlomoClaro

@Composable
fun Encabezado(titulo: String, subtitulo: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Azul, RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text(
            text = titulo,
            color = Blanco,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(text = subtitulo, color = Celeste)
    }
}

@Composable
fun CampoTexto(
    valor: String,
    alCambiar: (String) -> Unit,
    etiqueta: String,
    teclado: KeyboardType = KeyboardType.Text,
    prefijo: String? = null,
    esClave: Boolean = false,
    error: String? = null,
) {
    OutlinedTextField(
        value = valor,
        onValueChange = alCambiar,
        label = { Text(etiqueta) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        visualTransformation = if (esClave) PasswordVisualTransformation() else VisualTransformation.None,
        prefix = if (prefijo != null) { { Text(prefijo) } } else null,
        isError = error != null,
        supportingText = if (error != null) { { Text(error) } } else null,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Azul,
            unfocusedBorderColor = Plomo,
            focusedLabelColor = Azul,
            cursorColor = Azul
        )
    )
}

@Composable
fun CampoSeleccion(
    valor: String,
    etiqueta: String,
    error: String? = null,
    alTocar: () -> Unit
) {
    OutlinedTextField(
        value = valor,
        onValueChange = {},
        label = { Text(etiqueta) },
        readOnly = true,
        enabled = false,
        isError = error != null,
        supportingText = if (error != null) { { Text(error) } } else null,
        trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { alTocar() },
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = Negro,
            disabledBorderColor = Plomo,
            disabledLabelColor = Plomo,
            disabledTrailingIconColor = Azul
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpcionesBottomSheet(
    titulo: String,
    opciones: List<String>,
    alElegir: (String) -> Unit,
    alCerrar: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = alCerrar, containerColor = Blanco) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
        ) {
            Text(text = titulo, color = Azul, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            opciones.forEach { opcion ->
                Text(
                    text = opcion,
                    color = Negro,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { alElegir(opcion) }
                        .padding(vertical = 14.dp)
                )
                HorizontalDivider(color = PlomoClaro)
            }
        }
    }
}

@Composable
fun FilaDato(etiqueta: String, valor: String) {
    Column {
        Text(text = etiqueta, color = Plomo, style = MaterialTheme.typography.labelMedium)
        Text(text = valor, color = Negro, style = MaterialTheme.typography.bodyLarge)
    }
}
