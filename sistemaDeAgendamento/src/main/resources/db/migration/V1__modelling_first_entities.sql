CREATE TABLE users (
    id          BIGSERIAL       PRIMARY KEY,
    user_type   VARCHAR(10)     NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    cpf         VARCHAR(14)     NOT NULL UNIQUE,
    phone       VARCHAR(20)     NOT NULL,
    email       VARCHAR(255)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,

    address_street       VARCHAR(255),
    address_number       VARCHAR(20),
    address_complement   VARCHAR(100),
    address_neighborhood VARCHAR(100),
    address_city         VARCHAR(100),
    address_state        VARCHAR(2),
    address_cep          VARCHAR(9)
);

CREATE TABLE patients (
    id                   BIGINT          PRIMARY KEY REFERENCES users(id),
    birth_date           DATE            NOT NULL
);

CREATE TABLE specialties (
    id          BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE units (
    id                   BIGSERIAL       PRIMARY KEY,
    name                 VARCHAR(255)    NOT NULL,
    phone                VARCHAR(20)     NOT NULL,
    business_hours       VARCHAR(100),
    active               BOOLEAN         NOT NULL DEFAULT TRUE,
    address_street       VARCHAR(255),
    address_number       VARCHAR(20),
    address_complement   VARCHAR(100),
    address_neighborhood VARCHAR(100),
    address_city         VARCHAR(100),
    address_state        VARCHAR(2),
    address_cep          VARCHAR(9)
);

CREATE TABLE doctors (
    id                        BIGINT          PRIMARY KEY REFERENCES users(id),
    professional_registration VARCHAR(30)     UNIQUE
);

CREATE TABLE doctor_specialties (
    doctor_id    BIGINT  NOT NULL REFERENCES doctors(id),
    specialty_id BIGINT  NOT NULL REFERENCES specialties(id),
    PRIMARY KEY (doctor_id, specialty_id)
);

CREATE TABLE doctor_units (
    doctor_id BIGINT  NOT NULL REFERENCES doctors(id),
    unit_id   BIGINT  NOT NULL REFERENCES units(id),
    PRIMARY KEY (doctor_id, unit_id)
);