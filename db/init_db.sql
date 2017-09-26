-- Database: contractor

-- DROP DATABASE contractor;
--
-- CREATE DATABASE contractor
--   WITH OWNER = postgres
--        ENCODING = 'UTF8'
--        TABLESPACE = pg_default
--        LC_COLLATE = 'Bosnian (Latin)_Bosnia and Herzegovina.1250'
--        LC_CTYPE = 'Bosnian (Latin)_Bosnia and Herzegovina.1250'
--        CONNECTION LIMIT = -1;
--
-- COMMENT ON DATABASE contractor
--   IS 'Contractor app db';

DROP TABLE IF EXISTS notification_token;
DROP TABLE IF EXISTS session_token;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS pricing;
DROP TABLE IF EXISTS pricing_plan;
DROP TABLE IF EXISTS job_type;
DROP TABLE IF EXISTS job_status;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS user_role CASCADE;

CREATE TABLE user_role (
  user_role_id   SERIAL,
  user_role_name CHARACTER VARYING(20) NOT NULL,

  PRIMARY KEY (user_role_id)
);

CREATE TABLE company (
  company_id                    SERIAL,
  name                          CHARACTER VARYING(255) NOT NULL,
  company_identification_number CHARACTER(20)          NOT NULL,
  company_registration_code     CHARACTER(30)          NOT NULL,
  street_name                   CHARACTER VARYING(255) NOT NULL,
  street_number                 CHARACTER VARYING(10)  NOT NULL,
  country                       CHARACTER VARYING(255) NOT NULL,
  region                        CHARACTER VARYING(255) NOT NULL,
  zip_code                      INTEGER,
  notes                         CHARACTER VARYING(255) NOT NULL,
  phone_number                  CHARACTER VARYING(30)  NOT NULL,
  bank_account_number           CHARACTER VARYING(30)  NOT NULL,
  active                        BOOLEAN                NOT NULL DEFAULT FALSE,
  created                       TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified                 TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (company_id)
);

CREATE TABLE users (
  user_id            SERIAL,
  user_name          CHARACTER VARYING(255) NOT NULL,
  first_name         CHARACTER VARYING(255) NOT NULL,
  last_name          CHARACTER VARYING(255) NOT NULL,
  password           CHARACTER VARYING(255) NOT NULL,
  street_name        CHARACTER VARYING(255) NOT NULL,
  street_number      CHARACTER VARYING(10)  NOT NULL,
  country            CHARACTER VARYING(255) NOT NULL,
  zip_code           INTEGER                NOT NULL,
  email              CHARACTER VARYING(255) NOT NULL  UNIQUE,
  email_subscription BOOLEAN                NOT NULL DEFAULT FALSE,
  user_role_id       INTEGER                NOT NULL,
  company_id         INTEGER,
  phone_number       CHARACTER VARYING(30)  NOT NULL,
  active             BOOLEAN                NOT NULL DEFAULT TRUE,
  created            TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified      TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (user_id),
  FOREIGN KEY (user_role_id) REFERENCES user_role (user_role_id) ON UPDATE CASCADE,
  FOREIGN KEY (company_id) REFERENCES company (company_id) ON UPDATE CASCADE,
  UNIQUE (email)
);

CREATE TABLE job_status (
  job_status_id   SERIAL,
  job_status_name VARCHAR(20),

  PRIMARY KEY (job_status_id)
);

CREATE TABLE job_type (
  job_type_id          SERIAL,
  job_type_title       CHARACTER VARYING(30)  NOT NULL,
  job_type_description CHARACTER VARYING(255) NOT NULL,

  PRIMARY KEY (job_type_id)
);

CREATE TABLE pricing_plan (
  pricing_plan_id          SERIAL,
  pricing_plan_title       CHARACTER VARYING(30)  NOT NULL,
  pricing_plan_description CHARACTER VARYING(255) NOT NULL,
  pricing_plan_coefficient REAL                   NOT NULL,

  PRIMARY KEY (pricing_plan_id)
);

CREATE TABLE pricing (
  pricing_id      SERIAL,
  price           DECIMAL     NOT NULL,
  price_currency  VARCHAR(15) NOT NULL,
  price_unit      VARCHAR(20) NOT NULL,
  pricing_plan_id INTEGER     NOT NULL,
  job_type_id     INTEGER     NOT NULL,

  PRIMARY KEY (pricing_id),
  FOREIGN KEY (pricing_plan_id) REFERENCES pricing_plan (pricing_plan_id) ON UPDATE CASCADE,
  FOREIGN KEY (job_type_id) REFERENCES job_type (job_type_id) ON UPDATE CASCADE
);

CREATE TABLE job (
  job_id                  BIGSERIAL,
  job_type_id             INTEGER                NOT NULL,
  job_location            CHARACTER VARYING(100) NOT NULL,
  job_created_by_user_id  INTEGER                NOT NULL,
  job_assigned_to_user_id INTEGER,
  job_pricing_id          INTEGER                NOT NULL,
  job_notes               VARCHAR(255),
  job_created             TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  job_assigned            TIMESTAMP,
  job_completed           TIMESTAMP,

  PRIMARY KEY (job_id),
  FOREIGN KEY (job_type_id) REFERENCES job_type (job_type_id) ON UPDATE CASCADE,
  FOREIGN KEY (job_created_by_user_id) REFERENCES users (user_id) ON UPDATE CASCADE,
  FOREIGN KEY (job_assigned_to_user_id) REFERENCES users (user_id) ON UPDATE CASCADE,
  FOREIGN KEY (job_pricing_id) REFERENCES pricing (pricing_id) ON UPDATE CASCADE
);

CREATE TABLE session_token (
  session_token_id SERIAL,
  user_id          INTEGER               NOT NULL,
  session_token    CHARACTER VARYING(32) NOT NULL,
  created          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  validity         TIMESTAMP DEFAULT CURRENT_TIMESTAMP + (15 * INTERVAL '1 minute'),

  PRIMARY KEY (session_token_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE
);

CREATE TABLE notification_token (
  notification_token_id SERIAL,
  user_id               INTEGER     NOT NULL,
  token_type            VARCHAR(10) NOT NULL,
  notification_token    CHARACTER VARYING(255),
  created               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (notification_token_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT INTO user_role (user_role_name)
VALUES
  ('administrator'),
  ('customer'),
  ('contractor');

INSERT INTO job_type (job_type_title, job_type_description)
VALUES
  ('Appliance Install', ''),
  ('Appliance Repair', ''),
  ('BBQ Cleaning & Repair', ''),
  ('Camera & Alarm Install', ''),
  ('Carpet & Upholstery Cleaning', ''),
  ('Decks & Fences', ''),
  ('Duct Cleaning', ''),
  ('Eavestrough Repair', ''),
  ('Electrical', ''),
  ('Flooring', ''),
  ('Furniture Assembly', ''),
  ('Garage Door Repair', ''),
  ('Gas Services', ''),
  ('Handyman Services', ''),
  ('Heating & Cooling', ''),
  ('Home Inspection', ''),
  ('Junk Removal', ''),
  ('Lawn Maintenance', ''),
  ('Locksmith', ''),
  ('Mobile Auto Detail', ''),
  ('Mobile Tire Change', ''),
  ('Mould Remediation', ''),
  ('Moving & Storage', ''),
  ('Painting', ''),
  ('Pest Control', ''),
  ('Plumbing', ''),
  ('Power Wash', ''),
  ('Roofing', ''),
  ('Snow Removal', ''),
  ('Stone & Interlock', ''),
  ('Tile & Grout Cleaning', ''),
  ('Window & Eaves Cleaning', '');

INSERT INTO job_status (job_status_name)
VALUES
  ('Requested'),
  ('Accepted'),
  ('In Progress'),
  ('Review'),
  ('Completed');

INSERT INTO pricing_plan (pricing_plan_title, pricing_plan_description, pricing_plan_coefficient)
VALUES
  ('work days', '', 1.0),
  ('work days - after hours', '', 1.5),
  ('weekend', '', 1.5);

INSERT INTO pricing (price, price_currency, price_unit, pricing_plan_id, job_type_id)
  SELECT
    100.0,
    'CAD',
    'hour',
    c.pricing_plan_id,
    c.job_type_id
  FROM
    (SELECT
       a.pricing_plan_id,
       b.job_type_id
     FROM pricing_plan AS a, job_type AS b
     WHERE a.pricing_plan_title = 'work days') AS c;

INSERT INTO pricing (price, price_currency, price_unit, pricing_plan_id, job_type_id)
  SELECT
    200.0,
    'CAD',
    'hour',
    c.pricing_plan_id,
    c.job_type_id
  FROM
    (SELECT
       a.pricing_plan_id,
       b.job_type_id
     FROM pricing_plan AS a, job_type AS b
     WHERE a.pricing_plan_title = 'work days - after hours') AS c;

INSERT INTO pricing (price, price_currency, price_unit, pricing_plan_id, job_type_id)
  SELECT
    300.0,
    'CAD',
    'hour',
    c.pricing_plan_id,
    c.job_type_id
  FROM
    (SELECT
       a.pricing_plan_id,
       b.job_type_id
     FROM pricing_plan AS a, job_type AS b
     WHERE a.pricing_plan_title = 'weekend') AS c;

INSERT INTO users (user_name, first_name, last_name, password, street_name, street_number, country, zip_code, email, user_role_id, phone_number, created, last_modified)
  SELECT
    'everest',
    'Everest',
    'Consulting',
    '$2a$04$At.7B49IFotG0mzWtwSb6.ZXLF7l9fyDiDyO4USrTQy9tdaV55esy',
    'Behdzeta Mutevelica',
    '2A',
    'Bosnia & Herzegovina',
    71000,
    'damir@everestconsulting.ba',
    a.user_role_id,
    '+38761222242',
    now(),
    now()
  FROM (SELECT user_role_id
        FROM user_role
        WHERE user_role_name = 'administrator') AS a;