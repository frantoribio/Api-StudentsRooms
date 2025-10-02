package com.example.habitacionesapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.habitacionesapp.adapters.ImagesAdapter
import com.example.habitacionesapp.repository.HabitacionesRepository
import com.example.habitacionesapp.service.ApiService
import com.example.habitacionesapp.service.AuthApiService
import com.example.habitacionesapp.viewmodel.HabitacionesViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HabitacionesViewModel
    private lateinit var tvTitulo: TextView
    private lateinit var tvCiudad: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        tvTitulo = findViewById(R.id.tvTitulo)
        tvCiudad = findViewById(R.id.tvCiudad)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        tvPrecio = findViewById(R.id.tvPrecio)
        recyclerView = findViewById(R.id.recyclerViewImages)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Configurar Retrofit y repositorio
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val repository = HabitacionesRepository(
            retrofit.create(AuthApiService::class.java),
            retrofit.create(ApiService::class.java),
            prefs
        )

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return HabitacionesViewModel(repository) as T
            }
        })[HabitacionesViewModel::class.java]

        // Observadores
        viewModel.habitacionLiveData.observe(this) { habitacion ->
            tvTitulo.text = habitacion.titulo
            tvCiudad.text = habitacion.ciudad
            tvDescripcion.text = habitacion.descripcion
            tvPrecio.text = "€${habitacion.precioMensual}"

            val adapter = ImagesAdapter(habitacion.imagenesAndroid)
            recyclerView.adapter = adapter

            habitacion.imagenesAndroid.firstOrNull()?.let { url ->
                Glide.with(this).load(url).into(findViewById(R.id.imageItem))
            }
        }

        viewModel.errorLiveData.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
        }

        // Cargar habitación
        viewModel.cargarHabitacion(
            id = "793cd0c5-b345-4e30-930b-a7b9ad603f42",
            email = "juan.perez@example.com",
            password = "login123"
        )
    }

}
