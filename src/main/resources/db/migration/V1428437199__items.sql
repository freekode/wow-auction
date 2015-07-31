CREATE TABLE items (
  id         SERIAL PRIMARY KEY,
  identifier INTEGER,
  suffixId   INTEGER,
  uniqueId   BIGINT,
  context    INTEGER,
  createdAt  TIMESTAMP
);

CREATE INDEX items_identifier_index ON items (identifier);
