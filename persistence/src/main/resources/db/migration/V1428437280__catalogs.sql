CREATE TABLE catalogs (
  id     SERIAL PRIMARY KEY,
  headId INTEGER,
  code   INTEGER,
  value  CHARACTER VARYING,
  CONSTRAINT references_head_fk FOREIGN KEY (headId) REFERENCES catalog_heads (id) ON UPDATE RESTRICT ON DELETE RESTRICT
);

