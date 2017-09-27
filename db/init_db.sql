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
DROP TABLE IF EXISTS job_status;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS pricing;
DROP TABLE IF EXISTS pricing_plan;
DROP TABLE IF EXISTS job_category;
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

CREATE TABLE job_category (
  job_category_id          SERIAL,
  job_category_title       CHARACTER VARYING(30) NOT NULL,
  job_category_description TEXT                  NOT NULL,

  PRIMARY KEY (job_category_id)
);

CREATE TABLE pricing_plan (
  pricing_plan_id          SERIAL,
  pricing_plan_title       CHARACTER VARYING(30) NOT NULL,
  pricing_plan_description TEXT                  NOT NULL,
  pricing_plan_coefficient REAL                  NOT NULL,

  PRIMARY KEY (pricing_plan_id)
);

CREATE TABLE pricing (
  pricing_id                   SERIAL,
  starting_price               DECIMAL     NOT NULL,
  staring_price_description    TEXT        NOT NULL,
  additional_price             DECIMAL     NOT NULL,
  additional_price_description TEXT        NOT NULL,
  price_currency               VARCHAR(15) NOT NULL,
  price_unit                   VARCHAR(20) NOT NULL,
  job_category_id              INTEGER     NOT NULL,

  PRIMARY KEY (pricing_id),
  FOREIGN KEY (job_category_id) REFERENCES job_category (job_category_id) ON UPDATE CASCADE
);

CREATE TABLE job (
  job_id                  BIGSERIAL,
  job_category_id         INTEGER                             NOT NULL,
  job_location            CHARACTER VARYING(100)              NOT NULL,
  job_created_by_user_id  INTEGER                             NOT NULL,
  job_assigned_to_user_id INTEGER,
  job_pricing_id          INTEGER                             NOT NULL,
  job_pricing_plan_id     INTEGER                             NOT NULL,
  job_notes               VARCHAR(255),
  job_created             TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  job_assigned            TIMESTAMP,
  job_completed           TIMESTAMP,

  PRIMARY KEY (job_id),
  FOREIGN KEY (job_category_id) REFERENCES job_category (job_category_id) ON UPDATE CASCADE,
  FOREIGN KEY (job_created_by_user_id) REFERENCES users (user_id) ON UPDATE CASCADE,
  FOREIGN KEY (job_assigned_to_user_id) REFERENCES users (user_id) ON UPDATE CASCADE,
  FOREIGN KEY (job_pricing_id) REFERENCES pricing (pricing_id),
  FOREIGN KEY (job_pricing_plan_id) REFERENCES pricing (pricing_id)

);

CREATE TABLE job_status (
  job_status_id    SERIAL,
  job_id           BIGINT    NOT NULL,
  user_id          INTEGER   NOT NULL,
  job_status_title CHARACTER VARYING(15),
  created          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (job_status_id),
  FOREIGN KEY (job_id) REFERENCES job (job_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
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

INSERT INTO job_category (job_category_title, job_category_description)
VALUES
  ('Appliance Install',
   'Get a technician same-day to install any new appliance. Technicians can also uninstall your old appliances if necessary.'),
  ('Appliance Repair', 'Technicians diagnose and fix any issue with your dishwasher, fridge, freezer, washer, dryer, stove, oven or range. Parts, if needed, will be quoted. Miele appliances are 1.5X regular rates.'),
  ('BBQ Cleaning & Repair', 'A thorough cleaning brings your entire BBQ back to look almost new by taking it completely apart and power washing each component. The pros can also repair almost anything not working properly with the unit. Based on a 3 burner BBQ; +$30 per additional burner.'),
  ('Camera & Alarm Install', 'Professionally installing your home protection system will help keep your family safe.'),
  ('Carpet & Upholstery Cleaning', 'Minimum is $80 per job. Price is based on 200 sqft/room of carpet cleaning; area rug is $1.10 per sq ft and upholstery is $50/seat'),
  ('Decks & Fences', 'We will instantly match you with an amazing company to provide a quote. It is that easy.'),
  ('Duct Cleaning', 'Based on a single furnace house less than 2,000 square feet and 15 vents; additional charges may apply for more furnaces, larger square footage homes and 15+ vents'),
  ('Eavestrough Repair', 'We will instantly match you with an amazing company to provide a quote. It is that easy.'),
  ('Electrical', 'Electricians are ESA certified and should be used exclusively for everything electrical in a home.'),
  ('Flooring', 'We will instantly match you with an amazing company to provide a quote. It is that easy.'),
  ('Furniture Assembly', 'Assemblers are experienced in all types of furniture and brands. Hourly rate is based on man hours.'),
  ('Garage Door Repair', 'Technicians can fix any door or remote issues, along with ensuring your track is aligned and running smoothly.'),
  ('Gas Services', 'Gas technicians are TSSA certified and should be used exclusively for everything related to gas in a home.'),
  ('Handyman Services', 'Handymen are the do-it-all type and take care of almost all general repair and maintenance inside and outside of a home.TV Mounting, Caulking & other Bathroom Repairs, Hanging Mirrors, Art & Pictures, Installing Doorbells & Locks, Repairing Drywall & Much More!'),
  ('Heating & Cooling', 'Heating, Ventilation and Air Conditioning (HVAC) technicians are TSSA certified and should be used exclusively for heating, cooling and hot water in a home.'),
  ('Home Inspection', ''),
  ('Junk Removal', 'Based on a 5''x8''x12'' junk truck; 480 cubic feet. Prices for loads larger than 1/2 truck as follows: 2/3: $410, 3/4: $440, Full: $470. Construction materials and appliances may be extra and will be quoted by your pro.'),
  ('Lawn Maintenance', 'Price is per man hour. Fall and spring cleanup, one time maintenance or anything else you need to show your lawn the love it deserves. Have the following services plus many more taken care of today: grass cutting, leaf and debris removal, pull dead plants, turnover garden beds and edging & trenching for gardens. Additional charges may apply for specific equipment'),
  ('Locksmith', 'Certified locksmiths install and fix all issues with every type of locks, handles and fixtures, both indoor and outdoor.'),
  ('Mobile Auto Detail', 'Based on an interior/exterior wash of a sedan; additional charges apply for larger vehicles and extra services such as shampooing and waxing, excessive pet hair.'),
  ('Mobile Tire Change', 'Mobile mechanics come to your home or office to swap your ties on the spot. Add $50 if your tires are off-rims.'),
  ('Mould Remediation', 'We will instantly match you with an amazing company to provide a quote. It is that easy.'),
  ('Moving & Storage', 'Experienced movers without a large minimum! Rate includes 2 movers and a truck. Additional charges may apply to appliances and fitness equipment.'),
  ('Painting', 'We will instantly match you with an amazing company to provide a quote. It is that easy.'),
  ('Pest Control', 'Based on wasps / hornets / bees. Additional charge may apply if nest is 20+ feet high. Cockroaches, mice, ants start at $299, wild life removal start at $349; prices may vary based on technicians inspection. All pest control jobs are guaranteed for 6 months.'),
  ('Plumbing', 'Licensed plumbers should be used exclusively for all pipes, drains and bathroom/kitchen fixtures in a home. Use of certain equipment, such as a snake or camera, are extra and will be quoted by your pro.'),
  ('Power Wash', 'Make your deck, driveway, garage door and stone look like new with a professional powerwash.'),
  ('Roofing', 'We will instantly match you with an amazing company to provide a quote. It is that easy.'),
  ('Snow Removal', 'Based on a driveway that fits 2 cars (+$10 per additional car space). Includes clearing and applying salt to driveway, walkways, porch & sidewalk. Additional costs for unmaintained driveways and walkways.'),
  ('Stone & Interlock', 'We will instantly match you with an amazing company to provide a quote. It is that easy.'),
  ('Tile & Grout Cleaning', 'A cleaning completely restores your floors and walls to its original sparkle by cutting through built-up bacteria, grime and other micro-organisms. Marble cleaning is $3.50/sqf with a $450 job minimum'),
  ('Window & Eaves Cleaning',
   'Rate is for exterior Windows or Eaves cleaning - save 15% savings if you do both! Price is based on a 2,000 square foot home without divided windows. Additional charges apply for larger homes, french windows and 3+ story homes. Your professional will advise of any additional costs if outside of these parameters and prior to proceeding.');

INSERT INTO pricing_plan (pricing_plan_title, pricing_plan_description, pricing_plan_coefficient)
VALUES
  ('work days', '', 1.0),
  ('work days - after hours', '', 1.5),
  ('weekend', '', 1.5);

INSERT INTO pricing (starting_price, staring_price_description, additional_price, additional_price_description, price_currency, price_unit, job_category_id)
  SELECT
    100.0,
    '',
    50.0,
    '',
    'CAD',
    'hour',
    b.job_category_id
  FROM (SELECT a.job_category_id
        FROM job_category AS a) AS b;

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