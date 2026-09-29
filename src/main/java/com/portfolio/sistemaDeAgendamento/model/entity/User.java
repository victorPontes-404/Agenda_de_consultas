package com.portfolio.sistemaDeAgendamento.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
public class User {

    @Serial
    private Integer id;

    private String cpf;
    private String name;
}
