package com.portfolio.sistemaDeAgendamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

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
    private Address address;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String phone;


    @Column(name = "business_hours")
    private String businessHours;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToMany(mappedBy = "units")
    private Set<UserDoctor> doctors = new HashSet<>();

    public Unit(String name, Address address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.active = true;
    }
}
