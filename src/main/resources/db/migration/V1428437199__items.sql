CREATE TABLE items (
  id          SERIAL PRIMARY KEY,
  identifier  CHARACTER VARYING,
  snapshotId  INTEGER,
  createdAt   TIMESTAMP,
  CONSTRAINT items_snapshots_fk FOREIGN KEY (snapshotId) REFERENCES snapshots (id)
);
