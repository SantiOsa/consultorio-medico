package com.consultorio.repository

import com.consultorio.model.Paciente
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PacienteRepository : JpaRepository<Paciente, Long> {
    fun findByEmail(email: String): Optional<Paciente>
    fun existsByEmail(email: String): Boolean
}
