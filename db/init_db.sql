DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_right;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS job_types;
DROP TABLE IF EXISTS pricing;
DROP TABLE IF EXISTS token_session;

CREATE TABLE users (
user_id  SERIAL,
user_name varchar(255) NOT NULL,
first_name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
password  varchar(255) NOT NULL,
street_name varchar(255) NOT NULL,
street_number smallint(5),
country  varchar(255) NOT NULL,
zip smallint(5),
email varchar(255) NOT NULL,
phone_number varchar(30) NOT NULL,
created timestamp,

PRIMARY KEY(user_id)
);

CREATE TABLE users_right (
user_right_id SERIAL,
user_right varchar(20) NOT NULL,
PRIMARY KEY(user_right_id)
);


CREATE TABLE users_role (
users_role_id SERIAL,

PRIMARY KEY(user_id)
);

CREATE TABLE users (
users_role_id SERIAL,

PRIMARY KEY(user_id)
);

CREATE TABLE jobs (
job_id SERIAL,
job_type integer,
job_location varchar(100) NOT NULL,
created_by_user_id integer,
assigned_to_user_id integer,
created timestamp,
assigned timestamp,
completed timestamp,

PRIMARY KEY(job_id)
);

CREATE TABLE job_types (
job_type_id SERIAL,

PRIMARY KEY(job_type_id)
);

CREATE TABLE pricing (
pricing_id  SERIAL,
price integer(10) NOT NULL,
pricing_plan_id integer NOT NULL,

PRIMARY KEY(pricing_id)
);

CREATE TABLE pricing_plan (
pricing_plan_id SERIAL,
pricing_plan_name varchar(20) NOT NULL,
PRIMARY KEY(pricing_plan_id)
);

CREATE TABLE token_session (
token_session_id SERIAL,
user_id integer NOT NULL,
token varchar(20) NOT NULL,
created timestamp NOT NULL,
validity timestamp NOT NULL,

PRIMARY KEY(token_session_id)
);
