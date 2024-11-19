CREATE TABLE car_showrooms (
                               id serial PRIMARY KEY,
                               name VARCHAR(100) NOT NULL,
                               commercial_registration_number VARCHAR(10) NOT NULL UNIQUE,
                               manager_name VARCHAR(100),
                               contact_number VARCHAR(15) NOT NULL,
                               address VARCHAR(255),
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               deleted BOOLEAN NOT NULL DEFAULT FALSE
);