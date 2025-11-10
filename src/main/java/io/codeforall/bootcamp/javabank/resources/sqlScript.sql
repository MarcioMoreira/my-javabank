-- =============================
--  JAVABANK DATABASE (PostgreSQL)
-- =============================

-- CREATE DATABASE javabankdb;
-- \c javabankdb;

-- Create custom enum type for account kind
CREATE TYPE account_type AS ENUM ('CHECKING', 'SAVINGS');

-- Customer table
CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Account table
CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    balance NUMERIC(10,2) DEFAULT 0.00,
    type account_type NOT NULL,
    customer_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


-- =============================
-- DUMMY DATA FOR JAVABANK (PostgreSQL)
-- =============================

-- Insert customers
INSERT INTO customer (first_name, last_name)
VALUES
('Frodo', 'Baggins'),
('Samwise', 'Gamgee'),
('Aragorn', 'Elessar'),
('Legolas', 'Greenleaf'),
('Gimli', 'Son of Gloin'),
('Gandalf', 'The Grey'),
('Boromir', 'Of Gondor'),
('Eowyn', 'Of Rohan'),
('Merry', 'Brandybuck'),
('Pippin', 'Took');

-- Insert accounts (each linked to a customer)
INSERT INTO account (account_number, balance, type, customer_id)
VALUES
('ACC1001', 850.75, 'CHECKING', 1),
('ACC2001', 2500.00, 'SAVINGS', 1),
('ACC1002', 150.20, 'CHECKING', 2),
('ACC2002', 900.00, 'SAVINGS', 2),
('ACC1003', 1120.50, 'CHECKING', 3),
('ACC2003', 5000.00, 'SAVINGS', 3),
('ACC1004', 720.00, 'CHECKING', 4),
('ACC2004', 3100.75, 'SAVINGS', 4),
('ACC1005', 300.00, 'CHECKING', 5),
('ACC2005', 1200.00, 'SAVINGS', 5),
('ACC1006', 9999.99, 'CHECKING', 6),
('ACC1007', 450.50, 'CHECKING', 7),
('ACC1008', 750.25, 'CHECKING', 8),
('ACC1009', 80.00, 'CHECKING', 9),
('ACC1010', 95.40, 'CHECKING', 10);
