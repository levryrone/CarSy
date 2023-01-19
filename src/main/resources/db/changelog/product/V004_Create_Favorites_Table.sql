CREATE TABLE favorites_list
(
    car_id  INT NOT NULL REFERENCES cars (car_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
        PRIMARY KEY,
    user_id INT NOT NULL
);