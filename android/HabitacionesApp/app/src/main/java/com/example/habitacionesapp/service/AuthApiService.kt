package com.example.habitacionesapp.service

import com.example.habitacionesapp.model.LoginRequest
import com.example.habitacionesapp.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}