CREATE TABLE catalog_heads (
  id    SERIAL PRIMARY KEY,
  value CHARACTER VARYING
);


INSERT INTO catalog_heads (id, value) VALUES (1, 'Quality');
INSERT INTO catalog_heads (id, value) VALUES (2, 'Item Class');
INSERT INTO catalog_heads (id, value) VALUES (3, 'Item Subclass');
INSERT INTO catalog_heads (id, value) VALUES (4, 'Inventory slot');
