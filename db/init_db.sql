-- Database: contractor

-- DROP DATABASE contractor;

CREATE DATABASE contractor
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Bosnian (Latin)_Bosnia and Herzegovina.1250'
       LC_CTYPE = 'Bosnian (Latin)_Bosnia and Herzegovina.1250'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE contractor
  IS 'Contractor app test db';

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_right;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS job_types;
DROP TABLE IF EXISTS pricing;
DROP TABLE IF EXISTS session_token;

CREATE TABLE user_right (
user_right_id SERIAL,
user_right varchar(20) NOT NULL,
PRIMARY KEY(user_right_id)
);

--ERROR:  foreign key constraint "user_role_user_right_fkey" cannot be implemented
--DETAIL:  Key columns "user_right" and "user_right_id" are of incompatible types: integer[] and integer.
--CREATE TABLE user_role (
--user_role_id SERIAL,
--user_role_name varchar(20),
--user_right integer [] references user_right (user_right_id),
--PRIMARY KEY(user_role_id)
--);
CREATE TABLE user_role (
user_role_id SERIAL,
user_role_name varchar(20),
user_right integer [],
PRIMARY KEY(user_role_id)
);


CREATE TABLE users (
user_id SERIAL,
user_name varchar(255) NOT NULL,
first_name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
password varchar(255) NOT NULL,
street_name varchar(255) NOT NULL,
street_number smallint,
country varchar(255) NOT NULL,
zip_code smallint,
email varchar(255) NOT NULL,
user_role_id INT NOT NULL references user_role(user_role_id),
phone_number varchar(30) NOT NULL,
created timestamp,

PRIMARY KEY(user_id)
);

CREATE TABLE job_type (
job_type_id SERIAL,
job varchar (30) NOT NULL,

PRIMARY KEY(job_type_id)
);

CREATE TABLE jobs (
job_id SERIAL,
job_type integer references job_type (job_type_id),
job_location varchar(100) NOT NULL,
created_by_user_id integer NOT NULL references users(user_id),
assigned_to_user_id integer references users(user_id),
created timestamp,
assigned timestamp,
completed timestamp,

PRIMARY KEY(job_id, created_by_user_id)
);

CREATE TABLE pricing_plan (
pricing_plan_id SERIAL,
pricing_plan_name varchar(20) NOT NULL,

PRIMARY KEY(pricing_plan_id)
);

CREATE TABLE pricing (
pricing_id SERIAL,
price integer NOT NULL,
pricing_plan_id integer NOT NULL references pricing_plan (pricing_plan_id),

PRIMARY KEY(pricing_id)
);

CREATE TABLE session_token (
session_token_id SERIAL,
user_id integer NOT NULL references users(user_id),
session_token varchar(20) NOT NULL,
created timestamp NOT NULL,
validity timestamp NOT NULL,

PRIMARY KEY(session_token_id)
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
    ('putUser')
;

INSERT INTO user_role (user_role_name, user_right)
VALUES
('administrator', (SELECT array(SELECT user_right_id FROM user_right ORDER BY user_right_id ASC))),
('customer',(SELECT array(SELECT user_right_id FROM user_right WHERE user_right IN ('deleteUser', 'getJob', 'getUser', 'login', 'logout', 'postJob', 'putJob', 'putUser') ORDER BY user_right_id ASC))),
('contractor', (SELECT array(SELECT user_right_id FROM user_right WHERE user_right IN ('deleteUser', 'getJob', 'getUser', 'login', 'logout', 'putJob', 'putUser') ORDER BY user_right_id ASC)))
;
