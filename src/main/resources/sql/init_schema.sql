DROP TABLE vh_activity;
DROP TABLE vh_user;
DROP TABLE vh_category;

CREATE TABLE vh_user(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20),
    status VARCHAR(20)
);

CREATE TABLE vh_activity(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    status VARCHAR(20),
    time BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

CREATE TABLE vh_category(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20)
);

ALTER TABLE ONLY vh_activity
    ADD CONSTRAINT fk_vh_user_activity FOREIGN KEY (user_id) REFERENCES vh_user (id);

ALTER TABLE ONLY vh_activity
    ADD CONSTRAINT fk_vh_category_activity FOREIGN KEY (category_id) REFERENCES vh_category (id);
