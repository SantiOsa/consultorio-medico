package com.consultorio.dto

data class UserUpdateRequest(
    val nome: String,
    val email: String,
    val especializacao: String? = null
)

data class PasswordUpdateRequest(
    val senhaAntiga: String,
    val senhaNova: String
)

data class PacienteDTO(
    val id: Long,
    val nome: String,
    val email: String
)

data class MedicoDTO(
    val id: Long,
    val nome: String,
    val email: String,
    val especializacao: String
)
