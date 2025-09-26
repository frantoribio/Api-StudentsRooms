package com.example.habitacionesapp

data class Habitacion(
    val id: String,
    val titulo: String,
    val ciudad: String,
    val direccion: String,
    val precioMensual: Double,
    val descripcion: String,
    val imagenesUrl: List<String>,
    val propietario: Any?,
    val reservas: List<Any>
)
