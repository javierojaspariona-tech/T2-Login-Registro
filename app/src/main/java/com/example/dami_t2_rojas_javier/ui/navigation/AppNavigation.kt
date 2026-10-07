package com.example.dami_t2_rojas_javier.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dami_t2_rojas_javier.ui.screens.InicioScreen
import com.example.dami_t2_rojas_javier.ui.screens.LoginScreen
import com.example.dami_t2_rojas_javier.ui.screens.PerfilScreen
import com.example.dami_t2_rojas_javier.ui.screens.RegistroScreen
import com.example.dami_t2_rojas_javier.ui.theme.Azul
import com.example.dami_t2_rojas_javier.ui.theme.Blanco
import com.example.dami_t2_rojas_javier.ui.theme.Celeste

object Rutas {
    const val REGISTRO = "registro"
    const val LOGIN = "login"
    const val INICIO = "inicio"
    const val PERFIL = "perfil"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val entradaActual by navController.currentBackStackEntryAsState()
    val rutaActual = entradaActual?.destination?.route

    val mostrarBarra =
        rutaActual == Rutas.INICIO ||
                rutaActual == Rutas.PERFIL

    Scaffold(
        bottomBar = {
            if (mostrarBarra) {
                NavigationBar(
                    containerColor = Azul
                ) {
                    NavigationBarItem(
                        selected = rutaActual == Rutas.INICIO,
                        onClick = {
                            navController.navigate(Rutas.INICIO) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Inicio"
                            )
                        },
                        label = {
                            Text("Inicio")
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Azul,
                            selectedTextColor = Blanco,
                            indicatorColor = Celeste,
                            unselectedIconColor = Blanco,
                            unselectedTextColor = Blanco
                        )
                    )

                    NavigationBarItem(
                        selected = rutaActual == Rutas.PERFIL,
                        onClick = {
                            navController.navigate(Rutas.PERFIL) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Perfil"
                            )
                        },
                        label = {
                            Text("Perfil")
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Azul,
                            selectedTextColor = Blanco,
                            indicatorColor = Celeste,
                            unselectedIconColor = Blanco,
                            unselectedTextColor = Blanco
                        )
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Rutas.LOGIN,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Rutas.LOGIN) {
                LoginScreen(
                    onLoginExitoso = {
                        navController.navigate(Rutas.INICIO) {
                            popUpTo(Rutas.LOGIN) {
                                inclusive = true
                            }
                        }
                    },
                    onIrARegistro = {
                        navController.navigate(Rutas.REGISTRO)
                    }
                )
            }

            composable(Rutas.REGISTRO) {
                RegistroScreen(
                    onRegistroExitoso = {
                        navController.navigate(Rutas.LOGIN) {
                            popUpTo(Rutas.REGISTRO) {
                                inclusive = true
                            }
                        }
                    },
                    onIrALogin = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Rutas.INICIO) {
                InicioScreen()
            }

            composable(Rutas.PERFIL) {
                PerfilScreen(
                    onCerrarSesion = {
                        navController.navigate(Rutas.LOGIN) {
                            popUpTo(Rutas.INICIO) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}