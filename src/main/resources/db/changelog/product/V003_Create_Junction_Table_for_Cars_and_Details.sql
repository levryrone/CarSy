CREATE TABLE cars_details
(
    car_id INT NOT NULL REFERENCES cars (car_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    detail_id INT NOT NULL REFERENCES details (detail_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (car_id, detail_id)
);