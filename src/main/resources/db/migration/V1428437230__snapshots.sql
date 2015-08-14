CREATE TABLE snapshots (
  id           SERIAL PRIMARY KEY,
  file         CHARACTER VARYING,
  lastModified TIMESTAMP UNIQUE,
  size         INTEGER,
  createdAt    TIMESTAMP,
  realmId      INTEGER,
  CONSTRAINT snapshots_realm_fk FOREIGN KEY (realmId) REFERENCES realms (id)
);
