CREATE TABLE cars (
                      id serial PRIMARY KEY,
                      vin VARCHAR(25) NOT NULL,
                      maker VARCHAR(25) NOT NULL,
                      model VARCHAR(25) NOT NULL,
                      model_year INT NOT NULL,
                      price DECIMAL(10,2) NOT NULL,
                      car_showroom_id BIGINT NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      updated_at TIMESTAMP NOT NULL,
                      deleted BOOLEAN NOT NULL DEFAULT FALSE,
                      FOREIGN KEY (car_showroom_id) REFERENCES car_showrooms(id)
);