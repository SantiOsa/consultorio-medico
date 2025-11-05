package com.consultorio.service

import com.consultorio.dto.LoginRequest
import com.consultorio.dto.LoginResponse
import com.consultorio.dto.RegisterRequest
import com.consultorio.dto.RegistroResponse
import com.consultorio.model.Medico
import com.consultorio.model.Paciente
import com.consultorio.repository.MedicoRepository
import com.consultorio.repository.PacienteRepository
import com.consultorio.security.CustomUserDetailsService
import com.consultorio.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class AuthService(
    private val pacienteRepository: PacienteRepository,
    private val medicoRepository: MedicoRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val primaryAuthManager: AuthenticationManager,
    private val customUserDetailsService: CustomUserDetailsService
) {

    @Transactional
    fun register(registerRequest: RegisterRequest): RegistroResponse {
        // Verifica se o email já está em uso
        if (pacienteRepository.existsByEmail(registerRequest.email) ||
            medicoRepository.existsByEmail(registerRequest.email)) {
            throw IllegalArgumentException("Email já cadastrado")
        }

        val senhaEncriptada = passwordEncoder.encode(registerRequest.senha)

        return when (registerRequest.tipo.lowercase()) {
            "paciente" -> {
                val paciente = Paciente(
                    nome = registerRequest.nome,
                    email = registerRequest.email,
                    senha = senhaEncriptada
                )
                val savedPaciente = pacienteRepository.save(paciente)
                RegistroResponse(
                    id = savedPaciente.id,
                    nome = savedPaciente.nome,
                    email = savedPaciente.email,
                    tipo = "paciente"
                )
            }            "medico" -> {
                val medico = Medico(
                    nome = registerRequest.nome,
                    email = registerRequest.email,
                    senha = senhaEncriptada,
                    especializacao = registerRequest.especializacao ?: ""
                )
                val savedMedico = medicoRepository.save(medico)
                RegistroResponse(
                    id = savedMedico.id,
                    nome = savedMedico.nome,
                    email = savedMedico.email,
                    tipo = "medico"
                )
            }
            else -> throw IllegalArgumentException("Tipo de usuário inválido. Use 'paciente' ou 'medico'.")
        }
    }

    fun login(loginRequest: LoginRequest): LoginResponse {
        try {
            // Autenticar usuário
            val authentication = primaryAuthManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.senha)
            )
            
            // Verificar tipo de usuário
            val email = authentication.name
            val pacienteOpt = pacienteRepository.findByEmail(email)
            val medicoOpt = medicoRepository.findByEmail(email)
            
            val userDetails = customUserDetailsService.loadUserByUsername(email)
            
            return when {
                pacienteOpt.isPresent -> {
                    val paciente = pacienteOpt.get()
                    val token = jwtService.generateToken(userDetails, paciente.id.toString(), "paciente")
                    LoginResponse(
                        token = token,
                        id = paciente.id,
                        nome = paciente.nome,
                        email = paciente.email,
                        tipo = "paciente"
                    )
                }
                medicoOpt.isPresent -> {
                    val medico = medicoOpt.get()
                    val token = jwtService.generateToken(userDetails, medico.id.toString(), "medico")
                    LoginResponse(
                        token = token,
                        id = medico.id,
                        nome = medico.nome,
                        email = medico.email,
                        tipo = "medico"
                    )
                }
                else -> throw IllegalArgumentException("Usuário não encontrado")
            }
        } catch (e: AuthenticationException) {
            throw IllegalArgumentException("Credenciais inválidas")
        }
    }
}
