CREATE TABLE bids (
  id         BIGINT PRIMARY KEY,
  bid        INTEGER,
  buyout     INTEGER,
  quantity   INTEGER,
  itemId     INTEGER,
  playerId   INTEGER,
  timeleft   CHARACTER VARYING,
  createdAt  TIMESTAMP,
  CONSTRAINT bids_items_fk FOREIGN KEY (itemId) REFERENCES items (id),
  CONSTRAINT bids_owners_fk FOREIGN KEY (playerId) REFERENCES players (id)
);
