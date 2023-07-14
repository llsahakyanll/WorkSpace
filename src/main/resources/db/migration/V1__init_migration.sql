-- Create the database
CREATE DATABASE IF NOT EXISTS my_db;
USE my_db;

SET FOREIGN_KEY_CHECKS = 0;

-- Create the 'authorities' table
CREATE TABLE authorities
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);
-- Create the 'users' table
CREATE TABLE users
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_details_id    BIGINT,
    company_details_id BIGINT,
    email              VARCHAR(255) NOT NULL UNIQUE ,
    password           VARCHAR(255) NOT NULL,
    enabled            BOOLEAN      NOT NULL,
    FOREIGN KEY (user_details_id) REFERENCES user_details (id),
    FOREIGN KEY (company_details_id) REFERENCES company_details (id)

);
CREATE TABLE user_details
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name        VARCHAR(255) NOT NULL,
    last_name         VARCHAR(255) NOT NULL,
    registration_date VARCHAR(255) NOT NULL,
    birth_date        VARCHAR(255) NOT NULL,
    cv_pdf            VARCHAR(255) NOT NULL,
    phone_number      VARCHAR(255) NOT NULL UNIQUE ,
    image             BLOB         NOT NULL,
    gender            VARCHAR(255) NOT NULL
);
-- Create company_details table
CREATE TABLE company_details
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    address      VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    country      VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL UNIQUE ,
    website      VARCHAR(255),
    logo         BLOB         NOT NULL,
    description  TEXT,
    founded_year VARCHAR(255) NOT NULL
);
-- Create the 'user_authority' table
CREATE TABLE user_authority
(
    user_id      BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, authority_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (authority_id) REFERENCES authorities (id)
);
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `my_db`.`authorities` (`name`)
VALUES ('USER');
INSERT INTO `my_db`.`authorities` (`name`)
VALUES ('ADMIN');