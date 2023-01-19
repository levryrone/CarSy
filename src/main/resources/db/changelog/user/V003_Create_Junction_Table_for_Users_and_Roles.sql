CREATE TABLE users_roles
(
    user_id INT NOT NULL REFERENCES users (user_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    role_id INT NOT NULL REFERENCES roles (role_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);