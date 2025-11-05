package com.consultorio.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "consultas")
data class Consulta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    var paciente: Paciente,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    var medico: Medico,

    @NotNull(message = "A data é obrigatória")
    @Column(nullable = false)
    var data: LocalDate,

    @NotNull(message = "A hora é obrigatória")
    @Column(nullable = false)
    var hora: LocalTime
)
