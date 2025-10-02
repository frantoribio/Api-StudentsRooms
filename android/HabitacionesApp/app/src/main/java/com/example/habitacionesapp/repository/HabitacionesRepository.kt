package com.example.habitacionesapp.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.habitacionesapp.model.Habitacion
import com.example.habitacionesapp.model.LoginRequest
import com.example.habitacionesapp.service.ApiService
import com.example.habitacionesapp.service.AuthApiService

class HabitacionesRepository(
    private val authApi: AuthApiService,
    private val api: ApiService,
    private val prefs: SharedPreferences
) {

    suspend fun login(email: String, password: String): Boolean {
        val response = authApi.login(LoginRequest(email, password))
        prefs.edit { putString("jwt_token", response.token) }
        return response.token.isNotBlank()
    }

    suspend fun getHabitacion(id: String): Habitacion {
        val token = prefs.getString("jwt_token", "") ?: ""
        return api.getHabitacion(id, "Bearer $token")
    }
}
