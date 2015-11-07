CREATE TABLE items (
  id         SERIAL PRIMARY KEY,
  identifier CHARACTER VARYING,
  rand       CHARACTER VARYING,
  seed       CHARACTER VARYING,
  context    CHARACTER VARYING,
  createdAt  TIMESTAMP
);

CREATE UNIQUE INDEX items_identifier_index ON items (identifier, seed);
