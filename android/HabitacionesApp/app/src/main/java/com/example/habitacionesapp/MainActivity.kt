package com.example.habitacionesapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var tvTitulo: TextView
    private lateinit var tvCiudad: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var api: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitulo = findViewById(R.id.tvTitulo)
        tvCiudad = findViewById(R.id.tvCiudad)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        tvPrecio = findViewById(R.id.tvPrecio)
        recyclerView = findViewById(R.id.recyclerViewImages)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080") // Para localhost desde emulador
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
        val habitacionId = "793cd0c5-b345-4e30-930b-a7b9ad603f42"

        lifecycleScope.launch {
            try {
                val habitacion = withContext(Dispatchers.IO) {
                    api.getHabitacion(habitacionId)
                }

                tvTitulo.text = habitacion.titulo
                tvCiudad.text = habitacion.ciudad
                tvDescripcion.text = habitacion.descripcion
                tvPrecio.text = "€${habitacion.precioMensual}"

                val adapter = ImagesAdapter(habitacion.imagenesAndroid)
                recyclerView.adapter = adapter

                val imagenUrl = habitacion.imagenesAndroid.firstOrNull()

                if (imagenUrl != null) {
                    Glide.with(this@MainActivity).load(imagenUrl).into(findViewById(R.id.imageItem))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

