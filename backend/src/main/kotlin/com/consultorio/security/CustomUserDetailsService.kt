package com.consultorio.security

import com.consultorio.repository.MedicoRepository
import com.consultorio.repository.PacienteRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val pacienteRepository: PacienteRepository,
    private val medicoRepository: MedicoRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        // Verifica primeiro no repositório de pacientes
        val pacienteOptional = pacienteRepository.findByEmail(username)
        if (pacienteOptional.isPresent) {
            val paciente = pacienteOptional.get()
            val authorities = listOf(SimpleGrantedAuthority("ROLE_PACIENTE"))
            return User(paciente.email, paciente.senha, authorities)
        }

        // Verifica no repositório de médicos
        val medicoOptional = medicoRepository.findByEmail(username)
        if (medicoOptional.isPresent) {
            val medico = medicoOptional.get()
            val authorities = listOf(SimpleGrantedAuthority("ROLE_MEDICO"))
            return User(medico.email, medico.senha, authorities)
        }

        throw UsernameNotFoundException("Usuário não encontrado: $username")
    }

    fun getUserType(username: String): String {
        val pacienteOptional = pacienteRepository.findByEmail(username)
        if (pacienteOptional.isPresent) {
            return "paciente"
        }

        val medicoOptional = medicoRepository.findByEmail(username)
        if (medicoOptional.isPresent) {
            return "medico"
        }

        throw UsernameNotFoundException("Usuário não encontrado: $username")
    }

    fun getUserId(username: String): Long {
        val pacienteOptional = pacienteRepository.findByEmail(username)
        if (pacienteOptional.isPresent) {
            return pacienteOptional.get().id
        }

        val medicoOptional = medicoRepository.findByEmail(username)
        if (medicoOptional.isPresent) {
            return medicoOptional.get().id
        }

        throw UsernameNotFoundException("Usuário não encontrado: $username")
    }
}
