package com.consultorio.repository

import com.consultorio.model.Medico
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MedicoRepository : JpaRepository<Medico, Long> {
    fun findByEmail(email: String): Optional<Medico>
    fun existsByEmail(email: String): Boolean
}
