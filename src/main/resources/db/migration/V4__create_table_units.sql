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
