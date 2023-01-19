CREATE TABLE IF NOT EXISTS users
(
    user_id  SERIAL       NOT NULL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    name     VARCHAR(64)  NOT NULL,
    password VARCHAR(64)  NOT NULL,
    enabled  INT DEFAULT NULL
);