CREATE TABLE realms (
  id        SERIAL PRIMARY KEY,
  region    CHARACTER VARYING,
  name      CHARACTER VARYING,
  slug      CHARACTER VARYING UNIQUE,
  updating  BOOLEAN,
  createdAt TIMESTAMP
);


INSERT INTO realms (region, name, slug, updating, createdAt)
VALUES ('EU', 'Bronze Dragonflight', 'bronze-dragonflight', TRUE, now())
