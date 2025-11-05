package com.consultorio.service

import com.consultorio.dto.ConsultaRequest
import com.consultorio.dto.ConsultaResponse
import com.consultorio.model.Consulta
import com.consultorio.repository.ConsultaRepository
import com.consultorio.repository.MedicoRepository
import com.consultorio.repository.PacienteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ConsultaService(
    private val consultaRepository: ConsultaRepository,
    private val pacienteRepository: PacienteRepository,
    private val medicoRepository: MedicoRepository
) {

    fun getConsultasByPacienteId(pacienteId: Long): List<ConsultaResponse> {
        return consultaRepository.findByPacienteId(pacienteId).map { consulta ->
            ConsultaResponse(
                id = consulta.id,
                pacienteId = consulta.paciente.id,
                pacienteNome = consulta.paciente.nome,
                medicoId = consulta.medico.id,
                medicoNome = consulta.medico.nome,
                data = consulta.data,
                hora = consulta.hora
            )
        }
    }

    fun getConsultasByMedicoId(medicoId: Long): List<ConsultaResponse> {
        return consultaRepository.findByMedicoId(medicoId).map { consulta ->
            ConsultaResponse(
                id = consulta.id,
                pacienteId = consulta.paciente.id,
                pacienteNome = consulta.paciente.nome,
                medicoId = consulta.medico.id,
                medicoNome = consulta.medico.nome,
                data = consulta.data,
                hora = consulta.hora
            )
        }
    }

    @Transactional
    fun createConsulta(consultaRequest: ConsultaRequest): ConsultaResponse {
        // Validar se a data da consulta não é no passado
        if (consultaRequest.data.isBefore(LocalDate.now())) {
            throw IllegalArgumentException("A data da consulta não pode ser no passado")
        }
        
        // Buscar paciente e médico
        val paciente = pacienteRepository.findById(consultaRequest.pacienteId)
            .orElseThrow { IllegalArgumentException("Paciente não encontrado com o ID: ${consultaRequest.pacienteId}") }
        
        val medico = medicoRepository.findById(consultaRequest.medicoId)
            .orElseThrow { IllegalArgumentException("Médico não encontrado com o ID: ${consultaRequest.medicoId}") }
        
        // Verificar se já existe consulta para este médico nesta data e hora
        val consultasDoMedicoNaData = consultaRepository.findByMedicoIdAndData(consultaRequest.medicoId, consultaRequest.data)
        val horarioOcupado = consultasDoMedicoNaData.any { it.hora == consultaRequest.hora }
        
        if (horarioOcupado) {
            throw IllegalArgumentException("O médico já possui consulta agendada nesta data e horário")
        }
        
        // Criar e salvar a nova consulta
        val consulta = Consulta(
            paciente = paciente,
            medico = medico,
            data = consultaRequest.data,
            hora = consultaRequest.hora
        )
        
        val savedConsulta = consultaRepository.save(consulta)
        
        return ConsultaResponse(
            id = savedConsulta.id,
            pacienteId = savedConsulta.paciente.id,
            pacienteNome = savedConsulta.paciente.nome,
            medicoId = savedConsulta.medico.id,
            medicoNome = savedConsulta.medico.nome,
            data = savedConsulta.data,
            hora = savedConsulta.hora
        )
    }

    @Transactional
    fun deleteConsulta(consultaId: Long) {
        if (!consultaRepository.existsById(consultaId)) {
            throw IllegalArgumentException("Consulta não encontrada com o ID: $consultaId")
        }
        consultaRepository.deleteById(consultaId)
    }
}
