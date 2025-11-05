package com.consultorio.dto

data class ErrorResponse(
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)
