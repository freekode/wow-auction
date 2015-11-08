CREATE TABLE items_info (
  id              SERIAL PRIMARY KEY,
  name            CHARACTER VARYING,
  itemLevel       INTEGER,
  url             CHARACTER VARYING,
  icon            CHARACTER VARYING,
  sellPrice       BIGINT,
  itemId          INTEGER,
  qualityId       INTEGER,
  itemClassId     INTEGER,
  itemSubclassId  INTEGER,
  inventorySlotId INTEGER,
  CONSTRAINT items_info_id_pk FOREIGN KEY (itemId) REFERENCES items (id) ON UPDATE RESTRICT ON DELETE CASCADE,
  CONSTRAINT items_info_quality_fk FOREIGN KEY (qualityId) REFERENCES catalogs (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT items_info_class_fk FOREIGN KEY (itemClassId) REFERENCES catalogs (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT items_info_subclass_fk FOREIGN KEY (itemSubclassId) REFERENCES catalogs (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT items_info_inventory_slot_fk FOREIGN KEY (inventorySlotId) REFERENCES catalogs (id) ON UPDATE RESTRICT ON DELETE RESTRICT
);

