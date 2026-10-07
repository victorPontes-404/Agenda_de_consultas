package com.portfolio.sistemaDeAgendamento.entity.Enum;

import lombok.Getter;

@Getter
public enum Roles {
    PATIENT("PATIENT"),
    DOCTOR("DOCTOR"),
    RECEPTIONIST("RECEPTIONIST"),
    ADMIN("ADMIN");


    private final String value;

    Roles(String value) {
        this.value = value;
    }

}
