DROP DATABASE IF EXISTS javabank;
CREATE DATABASE javabank;
\c javabank;

--DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS accounts;

-- Schema Creation
CREATE TABLE customers (
  id            SERIAL PRIMARY KEY,
  email         VARCHAR(255),
  first_name    VARCHAR(255),
  last_name     VARCHAR(255),
  phone         VARCHAR(255),
  version       INT NOT NULL DEFAULT 0,
  creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts(
  id            SERIAL PRIMARY KEY,
  account_type  VARCHAR(31) NOT NULL,
  balance       DOUBLE PRECISION NOT NULL,
  customer_id   INT NOT NULL,
  version       INT NOT NULL DEFAULT 0,
  creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customers (id)
);

-- Function && Trigger creation for timestamp update
CREATE OR REPLACE FUNCTION update_modified_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.update_time = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER set_timestamp_customers
    BEFORE UPDATE ON customers
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

CREATE TRIGGER set_timestamp_accounts
    BEFORE UPDATE ON accounts
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

-- DB Seeds
DELETE FROM customers;
INSERT INTO customers(creation_time, update_time, version, first_name, last_name, email, phone) VALUES
                                                                                                    (TIMESTAMP '2023-10-10 08:45:56.468', TIMESTAMP '2023-10-10 08:45:56.468', 0, 'Sérgio', 'Gouveia', 'sg@mail.pt', '919909812'),
                                                                                                    (TIMESTAMP '2023-10-10 08:45:56.481', TIMESTAMP '2023-10-10 08:45:56.481', 0, 'Sara', 'Talefe', 'talefe@academia.com', '929671231'),
                                                                                                    (TIMESTAMP '2023-10-10 08:45:56.482', TIMESTAMP '2023-10-10 08:45:56.482', 0, 'Rúben', 'Maya', 'danlo@tinder.com', '919231459'),
                                                                                                    (TIMESTAMP '2023-10-10 08:45:56.482', TIMESTAMP '2023-10-10 08:45:56.482', 0, 'Sara', 'Lopes', 'saralopes@noob.b', '967890989'),
                                                                                                    (TIMESTAMP '2023-10-10 08:45:56.482', TIMESTAMP '2023-10-10 08:45:56.482', 0, 'Diogo', 'Rolo', 'rolinho@soulindo.true', '911222333');


DELETE FROM accounts;
INSERT INTO accounts(account_type, creation_time, update_time, version, balance, customer_id) VALUES
                                                                                                  ('CHECKING', TIMESTAMP '2023-10-10 10:18:53.819', TIMESTAMP '2023-10-10 10:22:58.578', 0, 1500.0, 1),
                                                                                                  ('SAVINGS', TIMESTAMP '2023-10-10 10:23:02.194', TIMESTAMP '2023-10-10 10:23:19.801', 0, 650.0, 1),
                                                                                                  ('CHECKING', TIMESTAMP '2023-10-10 14:30:37.769', TIMESTAMP '2023-10-10 14:30:43.042', 0, 0, 1),
                                                                                                  ('CHECKING', TIMESTAMP '2023-10-10 14:30:38.426', TIMESTAMP '2023-10-10 14:30:46.471', 0, 120.0, 2),
                                                                                                  ('SAVINGS', TIMESTAMP '2023-10-10 14:30:37.769', TIMESTAMP '2023-10-10 14:30:43.042', 0, 12981.0, 2),
                                                                                                  ('CHECKING', TIMESTAMP '2023-10-10 14:30:38.426', TIMESTAMP '2023-10-10 14:30:46.471', 0, 671.52, 3),
                                                                                                  ('SAVINGS', TIMESTAMP '2023-10-10 14:30:37.769', TIMESTAMP '2023-10-10 14:30:43.042', 0, 12.0, 3);

