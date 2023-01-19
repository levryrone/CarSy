CREATE TABLE cars
(
    car_id          SERIAL      NOT NULL PRIMARY KEY,
    production_date DATE        NOT NULL,
    model           VARCHAR(50) NOT NULL,
    description     text,
    price           INT
);