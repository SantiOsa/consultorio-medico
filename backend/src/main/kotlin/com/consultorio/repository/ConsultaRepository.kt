package com.consultorio.repository

import com.consultorio.model.Consulta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface ConsultaRepository : JpaRepository<Consulta, Long> {
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId")
    fun findByPacienteId(@Param("pacienteId") pacienteId: Long): List<Consulta>
    
    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId")
    fun findByMedicoId(@Param("medicoId") medicoId: Long): List<Consulta>
    
    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId AND c.data = :data")
    fun findByMedicoIdAndData(
        @Param("medicoId") medicoId: Long, 
        @Param("data") data: LocalDate
    ): List<Consulta>
}
