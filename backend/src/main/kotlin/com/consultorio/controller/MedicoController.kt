package com.consultorio.controller

import com.consultorio.dto.MedicoDTO
import com.consultorio.dto.PasswordUpdateRequest
import com.consultorio.dto.UserUpdateRequest
import com.consultorio.service.MedicoService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MedicoController(
    private val medicoService: MedicoService
) {

    @GetMapping("/medicos")
    fun getAllMedicos(): ResponseEntity<List<MedicoDTO>> {
        return ResponseEntity.ok(medicoService.getAllMedicos())
    }

    @GetMapping("/medicos/{id}")
    fun getMedicoById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val medico = medicoService.getMedicoById(id)
            ResponseEntity.ok(medico)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("message" to e.message))
        }
    }    @PutMapping("/medicos/update/{id}")
    @PreAuthorize("hasRole('MEDICO')")
    fun updateMedico(
        @PathVariable id: Long,
        @RequestBody updateRequest: UserUpdateRequest
    ): ResponseEntity<Any> {
        return try {
            val updatedMedico = medicoService.updateMedico(id, updateRequest)
            ResponseEntity.ok(updatedMedico)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("message" to e.message))
        } catch (e: Exception) {
            ResponseEntity.internalServerError()
                .body(mapOf("message" to "Erro ao atualizar dados do médico"))
        }
    }    @PostMapping("/medicos/update-password/{id}")
    @PreAuthorize("hasRole('MEDICO')")
    fun updateMedicoPassword(
        @PathVariable id: Long,
        @RequestBody passwordRequest: PasswordUpdateRequest
    ): ResponseEntity<Any> {
        return try {
            val updated = medicoService.updatePassword(id, passwordRequest)
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
