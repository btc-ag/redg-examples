DROP TABLE IF EXISTS redg.appointment;
DROP TABLE IF EXISTS redg.patient;
DROP DATABASE IF EXISTS redG;

CREATE DATABASE redg;

CREATE TABLE redg.patient (
  patient_id    INTEGER     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name    VARCHAR(50) NOT NULL,
  last_name     VARCHAR(50) NOT NULL,
  date_of_birth TIMESTAMP
);

CREATE TABLE redg.appointment (
  time    TIMESTAMP NOT NULL,
  patient INTEGER   NOT NULL,
  note    TEXT,

  CONSTRAINT pk_appointment PRIMARY KEY (time, patient),
  CONSTRAINT fk_appointment_patient FOREIGN KEY (patient) REFERENCES redg.patient (patient_id)
);