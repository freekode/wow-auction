CREATE TABLE items_info (
  id            SERIAL PRIMARY KEY,
  name          CHARACTER VARYING,
  level         CHARACTER VARYING,
  url           CHARACTER VARYING,
  icon          CHARACTER VARYING,
  itemId        INTEGER,
  qualityId     INTEGER,
  classId       INTEGER,
  subclassId    INTEGER,
  inventorySlot INTEGER,
  CONSTRAINT items_info_id_pk FOREIGN KEY (itemId) REFERENCES items (id) ON UPDATE RESTRICT ON DELETE CASCADE
);

