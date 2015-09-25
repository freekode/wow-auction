CREATE TABLE users (
  id        SERIAL PRIMARY KEY,
  login     CHARACTER VARYING UNIQUE,
  password  CHARACTER VARYING,
  createdAt TIMESTAMP
);

INSERT INTO users (login, password, createdAt) VALUES ('admin', 'admin', now());
