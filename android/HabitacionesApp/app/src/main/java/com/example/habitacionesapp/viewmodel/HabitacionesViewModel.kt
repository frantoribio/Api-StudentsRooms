package com.example.habitacionesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitacionesapp.model.Habitacion
import com.example.habitacionesapp.repository.HabitacionesRepository
import kotlinx.coroutines.launch

class HabitacionesViewModel(
    private val repository: HabitacionesRepository
) : ViewModel() {

    val habitacionLiveData = MutableLiveData<Habitacion>()
    val errorLiveData = MutableLiveData<String>()

    fun cargarHabitacion(id: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val loginExitoso = repository.login(email, password)
                if (loginExitoso) {
                    val habitacion = repository.getHabitacion(id)
                    habitacionLiveData.postValue(habitacion)
                } else {
                    errorLiveData.postValue("Login fallido")
                }
            } catch (e: Exception) {
                errorLiveData.postValue(e.message ?: "Error desconocido")
            }
        }
    }
}
