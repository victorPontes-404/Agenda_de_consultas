CREATE TABLE patients (
    id                   BIGINT          PRIMARY KEY REFERENCES users(id),
    birth_date           DATE            NOT NULL,
    address_street       VARCHAR(255),
    address_number       VARCHAR(20),
    address_complement   VARCHAR(100),
    address_neighborhood VARCHAR(100),
    address_city         VARCHAR(100),
    address_state        VARCHAR(2),
    address_cep          VARCHAR(9)
);
