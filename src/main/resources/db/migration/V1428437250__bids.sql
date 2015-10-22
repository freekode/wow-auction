CREATE TABLE bids (
  id         SERIAL PRIMARY KEY,
  identifier CHARACTER VARYING,
  rate       BIGINT,
  buyout     BIGINT,
  quantity   INTEGER,
  timeleft   CHARACTER VARYING,
  closed     BOOLEAN,
  createdAt  TIMESTAMP,
  itemId     INTEGER,
  playerId   INTEGER,
  CONSTRAINT bids_items_fk FOREIGN KEY (itemId) REFERENCES items (id) ON UPDATE RESTRICT ON DELETE CASCADE,
  CONSTRAINT bids_owners_fk FOREIGN KEY (playerId) REFERENCES players (id)
);
