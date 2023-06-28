-- Create the database
CREATE DATABASE IF NOT EXISTS my_db;
USE my_db;

SET FOREIGN_KEY_CHECKS = 0;

-- Create the 'authorities' table
CREATE TABLE authorities (
                             id   BIGINT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL UNIQUE
);
-- Create the 'users' table
CREATE TABLE users (
                       id             BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username       VARCHAR(50)  NOT NULL,
                       password       VARCHAR(255) NOT NULL,
                       first_name     VARCHAR(255) NOT NULL,
                       last_name      VARCHAR(255) NOT NULL,
                       email          VARCHAR(255) NOT NULL,
                       phone_number   VARCHAR(255) NOT NULL,
                       date           VARCHAR(255) NOT NULL,
                       gender         VARCHAR(45)  NOT NULL,
                       enabled        BOOLEAN      NOT NULL,
                       UNIQUE INDEX username_UNIQUE (username ASC),
                       UNIQUE INDEX email_UNIQUE (email ASC),
                       UNIQUE INDEX phone_number_UNIQUE (phone_number ASC)
);

-- Create the 'user_authority' table
CREATE TABLE user_authority (
                                user_id      BIGINT NOT NULL,
                                authority_id BIGINT NOT NULL,
                                PRIMARY KEY (user_id, authority_id),
                                FOREIGN KEY (user_id) REFERENCES users (id),
                                FOREIGN KEY (authority_id) REFERENCES authorities (id)
);
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `my_db`.`authorities` (`name`) VALUES ('USER');
INSERT INTO `my_db`.`authorities` (`name`)VALUES ('ADMIN');