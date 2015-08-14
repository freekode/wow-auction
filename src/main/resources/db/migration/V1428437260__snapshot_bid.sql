CREATE TABLE snapshot_bid (
  bidId      INTEGER,
  snapshotId INTEGER,
  CONSTRAINT snapshot_bid_bid_fk FOREIGN KEY (bidId) REFERENCES bids (id),
  CONSTRAINT snapshot_bid_snapshot_fk FOREIGN KEY (snapshotId) REFERENCES snapshots (id)
);

CREATE UNIQUE INDEX snapshot_bid_uniq_index ON snapshot_bid (bidId, snapshotId);
