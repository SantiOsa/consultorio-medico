package com.consultorio.dto

import java.time.LocalDate
import java.time.LocalTime

data class ConsultaRequest(
    val pacienteId: Long,
    val medicoId: Long,
    val data: LocalDate,
    val hora: LocalTime
)

data class ConsultaResponse(
    val id: Long,
    val pacienteId: Long,
    val pacienteNome: String,
    val medicoId: Long,
    val medicoNome: String,
    val data: LocalDate,
    val hora: LocalTime
)
