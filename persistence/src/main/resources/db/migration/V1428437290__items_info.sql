CREATE TABLE items_info (
  id              SERIAL PRIMARY KEY,
  name            CHARACTER VARYING,
  level           CHARACTER VARYING,
  url             CHARACTER VARYING,
  icon            CHARACTER VARYING,
  itemId          INTEGER,
  qualityId       INTEGER,
  itemClassId     INTEGER,
  itemSubclassId  INTEGER,
  inventorySlotId INTEGER,
  CONSTRAINT items_info_id_pk FOREIGN KEY (itemId) REFERENCES items (id) ON UPDATE RESTRICT ON DELETE CASCADE,
  CONSTRAINT items_info_quality_fk FOREIGN KEY (itemId) REFERENCES catalogs (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT items_info_class_fk FOREIGN KEY (itemId) REFERENCES catalogs (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT items_info_subclass_fk FOREIGN KEY (itemId) REFERENCES catalogs (id) ON UPDATE RESTRICT ON DELETE RESTRICT
);

