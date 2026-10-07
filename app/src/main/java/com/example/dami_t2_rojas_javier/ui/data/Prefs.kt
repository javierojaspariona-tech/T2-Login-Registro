package com.example.dami_t2_rojas_javier.ui.data

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    fun abrir(context: Context): SharedPreferences {
        return context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
    }
}
