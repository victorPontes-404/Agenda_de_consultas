package com.portfolio.sistemaDeAgendamento.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

@Builder
public record ErrorResponse(
        String message,
        @JsonFormat(pattern = "status_code")
        Integer statusCode
) {
}
