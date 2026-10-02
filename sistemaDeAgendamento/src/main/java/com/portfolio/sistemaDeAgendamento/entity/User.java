package com.portfolio.sistemaDeAgendamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @CPF
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean active = true;

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

    public User(String email, String phone, String cpf, String name) {
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.name = name;

        this.active = true;
    }
}