package com.consultorio.controller

import com.consultorio.dto.ConsultaRequest
import com.consultorio.dto.ConsultaResponse
import com.consultorio.service.ConsultaService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/consultas")
class ConsultaController(
    private val consultaService: ConsultaService
) {

    @GetMapping("/paciente/{pacienteId}")
    @PreAuthorize("hasRole('PACIENTE')")
    fun getConsultasByPacienteId(@PathVariable pacienteId: Long): ResponseEntity<List<ConsultaResponse>> {
        return ResponseEntity.ok(consultaService.getConsultasByPacienteId(pacienteId))
    }

    @GetMapping("/medico/{medicoId}")
    @PreAuthorize("hasRole('MEDICO')")
    fun getConsultasByMedicoId(@PathVariable medicoId: Long): ResponseEntity<List<ConsultaResponse>> {
        return ResponseEntity.ok(consultaService.getConsultasByMedicoId(medicoId))
    }

    @PostMapping
    @PreAuthorize("hasRole('PACIENTE')")
    fun createConsulta(@Valid @RequestBody consultaRequest: ConsultaRequest): ResponseEntity<Any> {
        return try {
            val createdConsulta = consultaService.createConsulta(consultaRequest)
            ResponseEntity.ok(createdConsulta)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            ResponseEntity.internalServerError()
                .body(mapOf("message" to "Erro ao criar consulta"))
        }
    }

    @DeleteMapping("/{consultaId}")
    fun deleteConsulta(@PathVariable consultaId: Long): ResponseEntity<Any> {
        return try {
            consultaService.deleteConsulta(consultaId)
            ResponseEntity.ok(mapOf("message" to "Consulta cancelada com sucesso"))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            ResponseEntity.internalServerError()
                .body(mapOf("message" to "Erro ao cancelar consulta"))
        }
    }
}
