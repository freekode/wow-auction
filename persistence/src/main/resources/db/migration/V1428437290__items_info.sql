CREATE TABLE items_info (
  id    INTEGER PRIMARY KEY,
  title CHARACTER VARYING,
  CONSTRAINT items_info_id_pk FOREIGN KEY (id) REFERENCES items_info (id) ON DELETE CASCADE
);

