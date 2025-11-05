package com.consultorio.dto

data class LoginRequest(
    val email: String,
    val senha: String
)

data class LoginResponse(
    val id: Long,
    val nome: String,
    val email: String,
    val tipo: String,
    val token: String
)

data class RegisterRequest(
    val nome: String,
    val email: String,
    val senha: String,
    val tipo: String,
    val especializacao: String? = null
)

data class RegistroResponse(
    val id: Long,
    val nome: String,
    val email: String,
    val tipo: String
)
