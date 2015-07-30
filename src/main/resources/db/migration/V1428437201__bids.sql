CREATE TABLE bids (
  id         SERIAL PRIMARY KEY,
  identifier CHARACTER VARYING,
  itemId     INTEGER,
  playerId    INTEGER,
  bid        CHARACTER VARYING,
  buyout     CHARACTER VARYING,
  quantity   INTEGER,
  timeleft   CHARACTER VARYING,
  createdAt  TIMESTAMP,
  CONSTRAINT bids_items_fk FOREIGN KEY (itemId) REFERENCES items (id),
  CONSTRAINT bids_owners_fk FOREIGN KEY (playerId) REFERENCES players (id)
);
