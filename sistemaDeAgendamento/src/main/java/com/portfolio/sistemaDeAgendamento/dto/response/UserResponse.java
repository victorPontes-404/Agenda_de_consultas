package com.portfolio.sistemaDeAgendamento.dto.response;

import com.portfolio.sistemaDeAgendamento.entity.User;

public record UserResponse(
        String email,
        String name
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getName()
        );
    }

}
