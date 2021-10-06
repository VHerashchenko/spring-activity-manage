DROP TABLE vh_user;

CREATE TABLE vh_user(
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20),
    status VARCHAR(20)
);