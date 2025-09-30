package com.example.habitacionesapp.service

import com.example.habitacionesapp.model.Habitacion
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("api/habitaciones/{id}")
    suspend fun getHabitacion(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Habitacion
}