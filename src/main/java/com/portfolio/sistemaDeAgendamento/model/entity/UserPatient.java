package com.portfolio.sistemaDeAgendamento.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
@DiscriminatorValue("PATIENT")
public class UserPatient extends User {

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street",       column = @Column(name = "address_street")),
        @AttributeOverride(name = "number",       column = @Column(name = "address_number")),
        @AttributeOverride(name = "complement",   column = @Column(name = "address_complement")),
        @AttributeOverride(name = "neighborhood", column = @Column(name = "address_neighborhood")),
        @AttributeOverride(name = "city",         column = @Column(name = "address_city")),
        @AttributeOverride(name = "state",        column = @Column(name = "address_state")),
        @AttributeOverride(name = "cep",          column = @Column(name = "address_cep"))
    })
    private Address address; // opcional iqual no RF01
}
