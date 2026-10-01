CREATE TABLE specialties (
    id          BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL UNIQUE,
    description VARCHAR(255)
);
