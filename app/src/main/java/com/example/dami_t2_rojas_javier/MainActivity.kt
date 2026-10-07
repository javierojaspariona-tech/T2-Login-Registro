package com.example.dami_t2_rojas_javier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dami_t2_rojas_javier.ui.navigation.AppNavigation
import com.example.dami_t2_rojas_javier.ui.theme.DAMI_T2_ROJAS_JAVIERTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DAMI_T2_ROJAS_JAVIERTheme {
                AppNavigation()
            }
        }
    }
}
