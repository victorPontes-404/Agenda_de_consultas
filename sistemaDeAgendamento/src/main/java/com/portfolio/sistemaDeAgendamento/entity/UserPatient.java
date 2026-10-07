package com.portfolio.sistemaDeAgendamento.entity;

import com.portfolio.sistemaDeAgendamento.entity.Enum.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patients")
@DiscriminatorValue("PATIENT")
public class UserPatient extends User {

    @Column(nullable = false)
    private LocalDate birthDate;

    public UserPatient(String email, String phone, String cpf, String name, LocalDate birthDate) {
        super(email, phone, cpf, name);
        this.birthDate = birthDate;
        this.setRole(Roles.PATIENT);
    }
}
