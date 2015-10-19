CREATE TABLE items_info (
  id            INTEGER PRIMARY KEY,
  name          CHARACTER VARYING,
  level         CHARACTER VARYING,
  url           CHARACTER VARYING,
  icon          CHARACTER VARYING,
  qualityId     INTEGER,
  classId       INTEGER,
  subclassId    INTEGER,
  inventorySlot INTEGER,
  CONSTRAINT items_info_id_pk FOREIGN KEY (id) REFERENCES items_info (id) ON DELETE CASCADE
);

