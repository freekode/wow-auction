CREATE TABLE snapshots (
  id           SERIAL PRIMARY KEY,
  file         CHARACTER VARYING,
  lastModified TIMESTAMP UNIQUE,
  closed       INTEGER,
  existing     INTEGER,
  newAmount    INTEGER,
  createdAt    TIMESTAMP,
  realmId      INTEGER,
  CONSTRAINT snapshots_realm_fk FOREIGN KEY (realmId) REFERENCES realms (id) ON UPDATE RESTRICT ON DELETE RESTRICT
);
