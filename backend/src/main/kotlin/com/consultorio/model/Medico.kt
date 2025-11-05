package com.consultorio.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "medicos")
data class Medico(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    var nome: String,

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true)
    var email: String,

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Column(nullable = false)
    var senha: String,    @NotBlank(message = "A especialização é obrigatória")
    @Column(nullable = false)
    var especializacao: String,

    @OneToMany(mappedBy = "medico", cascade = [CascadeType.ALL], orphanRemoval = true)
    var consultas: MutableList<Consulta> = mutableListOf()
)
