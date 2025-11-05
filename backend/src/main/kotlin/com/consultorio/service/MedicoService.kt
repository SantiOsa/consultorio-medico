package com.consultorio.service

import com.consultorio.dto.MedicoDTO
import com.consultorio.dto.PasswordUpdateRequest
import com.consultorio.dto.UserUpdateRequest
import com.consultorio.repository.MedicoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class MedicoService(
    private val medicoRepository: MedicoRepository,
    private val passwordEncoder: PasswordEncoder
) {    fun getAllMedicos(): List<MedicoDTO> {
        return medicoRepository.findAll().map { medico ->
            MedicoDTO(
                id = medico.id,
                nome = medico.nome,
                email = medico.email,
                especializacao = medico.especializacao
            )
        }
    }    fun getMedicoById(id: Long): MedicoDTO {
        val medico = medicoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Médico não encontrado com o ID: $id") }
        
        return MedicoDTO(
            id = medico.id,
            nome = medico.nome,
            email = medico.email,
            especializacao = medico.especializacao
        )
    }
    
    @Transactional
    fun updateMedico(id: Long, updateRequest: UserUpdateRequest): MedicoDTO {
        val medico = medicoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Médico não encontrado com o ID: $id") }
        
        // Verifica se o novo email já está em uso por outro usuário
        if (updateRequest.email != medico.email && 
            medicoRepository.existsByEmail(updateRequest.email)) {
            throw IllegalArgumentException("Email já está em uso")
        }
        
        // Atualizar os dados        medico.nome = updateRequest.nome
        medico.email = updateRequest.email        // Atualiza especialização se fornecida
        updateRequest.especializacao?.let {
            medico.especializacao = it
        }
          val updatedMedico = medicoRepository.save(medico)
        return MedicoDTO(
            id = updatedMedico.id,
            nome = updatedMedico.nome,
            email = updatedMedico.email,
            especializacao = updatedMedico.especializacao
        )
    }
    
    @Transactional
    fun updatePassword(id: Long, passwordRequest: PasswordUpdateRequest): Boolean {
        val medico = medicoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Médico não encontrado com o ID: $id") }
        
        // Verifica se a senha antiga está correta
        if (!passwordEncoder.matches(passwordRequest.senhaAntiga, medico.senha)) {
            throw IllegalArgumentException("Senha atual incorreta")
        }
        
        // Atualiza a senha
        medico.senha = passwordEncoder.encode(passwordRequest.senhaNova)
        medicoRepository.save(medico)
        
        return true
    }
}
