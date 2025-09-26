package com.example.habitacionesapp

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/habitaciones/{id}")
    suspend fun getHabitacion(@Path("id") id: String): Habitacion
}
