CREATE TABLE snapshots (
  id           SERIAL PRIMARY KEY,
  realmId      INTEGER,
  file         CHARACTER VARYING,
  lastModified TIMESTAMP UNIQUE,
  createdAt    TIMESTAMP,
  CONSTRAINT snapshots_realm_fk FOREIGN KEY (realmId) REFERENCES realms (id)
);
