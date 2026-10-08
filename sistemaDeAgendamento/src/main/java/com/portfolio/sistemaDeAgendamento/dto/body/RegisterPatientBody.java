package com.portfolio.sistemaDeAgendamento.dto.body;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record RegisterPatientBody(
        @NotBlank String name,
        @NotBlank @CPF String cpf,
        @NotBlank String phone,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank LocalDate birthDate,
        AddressBody address
) {
}
