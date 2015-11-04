CREATE TABLE items_calc (
  id          SERIAL PRIMARY KEY,
  marketPrice BIGINT,
  itemId      INTEGER,
  updatedAt   TIMESTAMP,
  createdAt   TIMESTAMP,
  CONSTRAINT items_info_id_pk FOREIGN KEY (itemId) REFERENCES items (id) ON UPDATE RESTRICT ON DELETE CASCADE
);

