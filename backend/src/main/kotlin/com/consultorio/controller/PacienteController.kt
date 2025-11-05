package com.consultorio.controller

import com.consultorio.dto.PacienteDTO
import com.consultorio.dto.PasswordUpdateRequest
import com.consultorio.dto.UserUpdateRequest
import com.consultorio.service.PacienteService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PacienteController(
    private val pacienteService: PacienteService
) {

    @GetMapping("/pacientes/{id}")
    fun getPacienteById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val paciente = pacienteService.getPacienteById(id)
            ResponseEntity.ok(paciente)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("message" to e.message))
        }
    }    @PutMapping("/pacientes/update/{id}")
    @PreAuthorize("hasRole('PACIENTE')")
    fun updatePaciente(
        @PathVariable id: Long,
        @RequestBody updateRequest: UserUpdateRequest
    ): ResponseEntity<Any> {
        return try {
            val updatedPaciente = pacienteService.updatePaciente(id, updateRequest)
            ResponseEntity.ok(updatedPaciente)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            ResponseEntity.internalServerError()
                .body(mapOf("message" to "Erro ao atualizar dados do paciente"))
        }
    }    @PostMapping("/pacientes/update-password/{id}")
    @PreAuthorize("hasRole('PACIENTE')")
    fun updatePacientePassword(
        @PathVariable id: Long,
        @RequestBody passwordRequest: PasswordUpdateRequest
    ): ResponseEntity<Any> {
        return try {
            val updated = pacienteService.updatePassword(id, passwordRequest)
            if (updated) {
                ResponseEntity.ok(mapOf("message" to "Senha atualizada com sucesso"))
            } else {
                ResponseEntity.internalServerError()
                    .body(mapOf("message" to "Erro ao atualizar senha"))
            }
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            ResponseEntity.internalServerError()
                .body(mapOf("message" to "Erro ao atualizar senha"))
        }
    }
}
