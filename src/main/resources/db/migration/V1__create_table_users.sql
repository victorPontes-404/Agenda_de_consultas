CREATE TABLE users (
    id          BIGSERIAL       PRIMARY KEY,
    user_type   VARCHAR(10)     NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    cpf         VARCHAR(14)     NOT NULL UNIQUE,
    phone       VARCHAR(20)     NOT NULL,
    email       VARCHAR(255)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE
);
