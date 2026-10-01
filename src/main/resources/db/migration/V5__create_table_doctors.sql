CREATE TABLE doctors (
    id                        BIGINT          PRIMARY KEY REFERENCES users(id),
    professional_registration VARCHAR(30)     UNIQUE
);
