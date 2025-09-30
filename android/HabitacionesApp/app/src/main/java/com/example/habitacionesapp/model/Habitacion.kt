package com.example.habitacionesapp.model

data class Habitacion(
    val id: String? = null,
    val titulo: String? = null,
    val ciudad: String? = null,
    val direccion: String? = null,
    val precioMensual: Double? = null,
    val descripcion: String? = null,
    val imagenesUrl: List<String> = emptyList(),
    val propietario: Any? = null,
    val reservas: List<Any> = emptyList()
) {
    val imagenesAndroid: List<String>
        get() = imagenesUrl.map { it.replace("localhost", "10.0.2.2") }
}