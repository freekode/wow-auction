CREATE TABLE items (
  id         SERIAL PRIMARY KEY,
  identifier INTEGER,
  suffixId   INTEGER,
  uniqueId   BIGINT,
  context    INTEGER,
  snapshotId INTEGER,
  createdAt  TIMESTAMP,
  CONSTRAINT items_snapshots_fk FOREIGN KEY (snapshotId) REFERENCES snapshots (id)
);

CREATE INDEX items_identifier_index on items(identifier);
