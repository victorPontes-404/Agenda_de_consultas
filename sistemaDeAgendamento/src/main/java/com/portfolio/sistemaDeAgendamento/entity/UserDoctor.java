package com.portfolio.sistemaDeAgendamento.entity;

import com.portfolio.sistemaDeAgendamento.entity.Enum.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
@DiscriminatorValue("DOCTOR")
public class UserDoctor extends User {

    @Column(name = "professional_registration", unique = true)
    private String professionalRegistration;

    @ManyToMany
    @JoinTable(
            name = "doctor_specialties",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "doctor_units",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id")
    )
    private Set<Unit> units = new HashSet<>();

    public UserDoctor(String email, String phone, String cpf, String name, String professionalRegistration) {
        super(email, phone, cpf, name);
        this.professionalRegistration = professionalRegistration;
        this.setRole(Roles.DOCTOR);
    }
}
