CREATE TABLE doctor_units (
    doctor_id BIGINT  NOT NULL REFERENCES doctors(id),
    unit_id   BIGINT  NOT NULL REFERENCES units(id),
    PRIMARY KEY (doctor_id, unit_id)
);
