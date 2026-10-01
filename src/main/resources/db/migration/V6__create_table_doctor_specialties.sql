CREATE TABLE doctor_specialties (
    doctor_id    BIGINT  NOT NULL REFERENCES doctors(id),
    specialty_id BIGINT  NOT NULL REFERENCES specialties(id),
    PRIMARY KEY (doctor_id, specialty_id)
);
