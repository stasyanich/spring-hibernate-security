CREATE TABLE IF NOT EXISTS user
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    login       VARCHAR(30) NOT NULL,
    password    CHAR(80)    NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    email       VARCHAR(64) NOT NULL,
    approved    BOOLEAN     DEFAULT FALSE
);