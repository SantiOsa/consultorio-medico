package com.consultorio.service

import com.consultorio.dto.PacienteDTO
import com.consultorio.dto.PasswordUpdateRequest
import com.consultorio.dto.UserUpdateRequest
import com.consultorio.repository.PacienteRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class PacienteService(
    private val pacienteRepository: PacienteRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun getPacienteById(id: Long): PacienteDTO {
        val paciente = pacienteRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Paciente não encontrado com o ID: $id") }
        
        return PacienteDTO(
            id = paciente.id,
            nome = paciente.nome,
            email = paciente.email
        )
    }
    
    @Transactional
    fun updatePaciente(id: Long, updateRequest: UserUpdateRequest): PacienteDTO {
        val paciente = pacienteRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Paciente não encontrado com o ID: $id") }
        
        // Verifica se o novo email já está em uso por outro usuário
        if (updateRequest.email != paciente.email && 
            pacienteRepository.existsByEmail(updateRequest.email)) {
            throw IllegalArgumentException("Email já está em uso")
        }
        
        // Atualizar os dados
        paciente.nome = updateRequest.nome
        paciente.email = updateRequest.email
        
        val updatedPaciente = pacienteRepository.save(paciente)
        
        return PacienteDTO(
            id = updatedPaciente.id,
            nome = updatedPaciente.nome,
            email = updatedPaciente.email
        )
    }
    
    @Transactional
    fun updatePassword(id: Long, passwordRequest: PasswordUpdateRequest): Boolean {
        val paciente = pacienteRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Paciente não encontrado com o ID: $id") }
        
        // Verifica se a senha antiga está correta
        if (!passwordEncoder.matches(passwordRequest.senhaAntiga, paciente.senha)) {
            throw IllegalArgumentException("Senha atual incorreta")
        }
        
        // Atualiza a senha
        paciente.senha = passwordEncoder.encode(passwordRequest.senhaNova)
        pacienteRepository.save(paciente)
        
        return true
    }
}
