-- Database: contractor

-- DROP DATABASE contractor;

-- CREATE DATABASE contractor
--   WITH OWNER = postgres
--        ENCODING = 'UTF8'
--        TABLESPACE = pg_default
--        LC_COLLATE = 'Bosnian (Latin)_Bosnia and Herzegovina.1250'
--        LC_CTYPE = 'Bosnian (Latin)_Bosnia and Herzegovina.1250'
--        CONNECTION LIMIT = -1;
--
-- COMMENT ON DATABASE contractor
--   IS 'Contractor app test db';

DROP TABLE IF EXISTS session_token;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS pricing;
DROP TABLE IF EXISTS pricing_plan;
DROP TABLE IF EXISTS job_type;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user_right;

CREATE TABLE user_right (
  user_right_id SMALLSERIAL,
  user_right    CHARACTER VARYING(20) NOT NULL,

  PRIMARY KEY (user_right_id)
);

--ERROR:  foreign key constraint "user_role_user_right_fkey" cannot be implemented
--DETAIL:  Key columns "user_right" and "user_right_id" are of incompatible types: integer[] and integer.
--CREATE TABLE user_role (
--user_role_id SERIAL,
--user_role_name character varying(20),
--user_right integer [] references user_right (user_right_id),
--PRIMARY KEY(user_role_id)
--);
CREATE TABLE user_role (
  user_role_id   SMALLSERIAL,
  user_role_name CHARACTER VARYING(20) NOT NULL,
  user_right     SMALLINT []           NOT NULL,

  PRIMARY KEY (user_role_id)
);


CREATE TABLE users (
  user_id       BIGSERIAL,
  user_name     CHARACTER VARYING(255) NOT NULL,
  first_name    CHARACTER VARYING(255) NOT NULL,
  last_name     CHARACTER VARYING(255) NOT NULL,
  password      CHARACTER VARYING(255) NOT NULL,
  street_name   CHARACTER VARYING(255) NOT NULL,
  street_number SMALLINT,
  country       CHARACTER VARYING(255) NOT NULL,
  zip_code      SMALLINT,
  email         CHARACTER VARYING(255) NOT NULL,
  user_role_id  SMALLINT               NOT NULL REFERENCES user_role (user_role_id),
  phone_number  CHARACTER VARYING(30)  NOT NULL,
  created       TIMESTAMP,
  last_modified TIMESTAMP,

  PRIMARY KEY (user_id)
);

CREATE TABLE job_type (
  job_type_id          SMALLSERIAL,
  job_type_title       CHARACTER VARYING(30)  NOT NULL,
  job_type_description CHARACTER VARYING(255) NOT NULL,

  PRIMARY KEY (job_type_id)
);

CREATE TABLE pricing_plan (
  pricing_plan_id          SMALLSERIAL,
  pricing_plan_title       CHARACTER VARYING(30)  NOT NULL,
  pricing_plan_description CHARACTER VARYING(255) NOT NULL,

  PRIMARY KEY (pricing_plan_id)
);

CREATE TABLE pricing (
  pricing_id      SMALLSERIAL,
  price           DECIMAL     NOT NULL,
  price_currency  VARCHAR(15) NOT NULL,
  price_unit      VARCHAR(5)  NOT NULL,
  pricing_plan_id INTEGER     NOT NULL REFERENCES pricing_plan (pricing_plan_id),
  job_type_id     INTEGER     NOT NULL REFERENCES job_type (job_type_id),

  PRIMARY KEY (pricing_id)
);

CREATE TABLE jobs (
  job_id                  BIGSERIAL,
  job_type_id             SMALLINT REFERENCES job_type (job_type_id),
  job_location            CHARACTER VARYING(100) NOT NULL,
  job_created_by_user_id  BIGINT                 NOT NULL REFERENCES users (user_id),
  job_assigned_to_user_id BIGINT REFERENCES users (user_id),
  job_pricing_id          SMALLINT REFERENCES pricing (pricing_id),
  job_notes               VARCHAR(255),
  job_created             TIMESTAMP,
  job_assigned            TIMESTAMP,
  job_completed           TIMESTAMP,

  PRIMARY KEY (job_id, job_created_by_user_id)
);

CREATE TABLE session_token (
  session_token_id BIGSERIAL,
  user_id          BIGINT                NOT NULL REFERENCES users (user_id),
  session_token    CHARACTER VARYING(20) NOT NULL,
  created          TIMESTAMP             NOT NULL,
  validity         TIMESTAMP             NOT NULL,

  PRIMARY KEY (session_token_id)
);

INSERT INTO user_right (user_right)
VALUES
  ('deleteJob'),
  ('deleteJobType'),
  ('deleteUser'),
  ('getJob'),
  ('getJobType'),
  ('getUser'),
  ('login'),
  ('logout'),
  ('postJob'),
  ('postJobType'),
  ('postUser'),
  ('putJob'),
  ('putJobType'),
  ('putUser');

INSERT INTO user_role (user_role_name, user_right)
VALUES
  ('administrator', (SELECT array(SELECT user_right_id
                                  FROM user_right
                                  ORDER BY user_right_id ASC))),
  ('customer', (SELECT array(SELECT user_right_id
                             FROM user_right
                             WHERE user_right IN
                                   ('deleteUser', 'getJob', 'getUser', 'login', 'logout', 'postJob', 'putJob', 'putUser')
                             ORDER BY user_right_id ASC))),
  ('contractor', (SELECT array(SELECT user_right_id
                               FROM user_right
                               WHERE user_right IN
                                     ('deleteUser', 'getJob', 'getUser', 'login', 'logout', 'putJob', 'putUser')
                               ORDER BY user_right_id ASC)));

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

INSERT INTO pricing_plan (pricing_plan_title, pricing_plan_description)
VALUES
  ('work days', ''),
  ('work days - after hours', ''),
  ('weekend', '');

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