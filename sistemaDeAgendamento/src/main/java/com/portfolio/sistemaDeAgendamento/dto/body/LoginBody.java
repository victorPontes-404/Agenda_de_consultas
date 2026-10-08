package com.portfolio.sistemaDeAgendamento.dto.body;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginBody(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
