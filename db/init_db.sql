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
  user_right_id SERIAL,
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
  user_role_id   SERIAL,
  user_role_name CHARACTER VARYING(20) NOT NULL,
  user_right     INTEGER []            NOT NULL,

  PRIMARY KEY (user_role_id)
);


CREATE TABLE users (
  user_id       SERIAL,
  user_name     CHARACTER VARYING(255) NOT NULL,
  first_name    CHARACTER VARYING(255) NOT NULL,
  last_name     CHARACTER VARYING(255) NOT NULL,
  password      CHARACTER VARYING(255) NOT NULL,
  street_name   CHARACTER VARYING(255) NOT NULL,
  street_number SMALLINT,
  country       CHARACTER VARYING(255) NOT NULL,
  zip_code      SMALLINT,
  email         CHARACTER VARYING(255) NOT NULL,
  user_role_id  INT                    NOT NULL REFERENCES user_role (user_role_id),
  phone_number  CHARACTER VARYING(30)  NOT NULL,
  created       TIMESTAMP,

  PRIMARY KEY (user_id)
);

CREATE TABLE job_type (
  job_type_id SERIAL,
  job         CHARACTER VARYING(30) NOT NULL,

  PRIMARY KEY (job_type_id)
);

CREATE TABLE pricing_plan (
  pricing_plan_id   SERIAL,
  pricing_plan_name CHARACTER VARYING(20) NOT NULL,

  PRIMARY KEY (pricing_plan_id)
);

CREATE TABLE pricing (
  pricing_id      SERIAL,
  price           INTEGER NOT NULL,
  pricing_plan_id INTEGER NOT NULL REFERENCES pricing_plan (pricing_plan_id),
  job_type_id     INTEGER NOT NULL REFERENCES job_type (job_type_id),

  PRIMARY KEY (pricing_id)
);

CREATE TABLE jobs (
  job_id              SERIAL,
  job_type            INTEGER REFERENCES job_type (job_type_id),
  job_location        CHARACTER VARYING(100) NOT NULL,
  created_by_user_id  INTEGER                NOT NULL REFERENCES users (user_id),
  assigned_to_user_id INTEGER REFERENCES users (user_id),
  pricing_id          INTEGER REFERENCES pricing (pricing_id),
  created             TIMESTAMP,
  assigned            TIMESTAMP,
  completed           TIMESTAMP,

  PRIMARY KEY (job_id, created_by_user_id)
);

CREATE TABLE session_token (
  session_token_id SERIAL,
  user_id          INTEGER               NOT NULL REFERENCES users (user_id),
  session_token    CHARACTER VARYING(20) NOT NULL,
  created          TIMESTAMP             NOT NULL,
  validity         TIMESTAMP             NOT NULL,

  PRIMARY KEY (session_token_id)
);

INSERT INTO user_right (user_right)
VALUES
  ('deleteJob'),
  ('deleteUser'),
  ('getJob'),
  ('getUser'),
  ('login'),
  ('logout'),
  ('postJob'),
  ('postUser'),
  ('putJob'),
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
