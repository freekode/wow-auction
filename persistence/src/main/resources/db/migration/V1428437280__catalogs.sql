CREATE TABLE catalogs (
  id          SERIAL PRIMARY KEY,
  headId      INTEGER,
  codeInteger INTEGER,
  codeString  CHARACTER VARYING,
  value       CHARACTER VARYING,
  CONSTRAINT references_head_fk FOREIGN KEY (headId) REFERENCES catalog_heads (id) ON UPDATE RESTRICT ON DELETE RESTRICT
);


-- quality
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 0, 'Poor');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 1, 'Common');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 2, 'Uncommon');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 3, 'Rare');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 4, 'Epic');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 5, 'Legendary');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 6, 'Artifact');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 7, 'Heirloom');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (1, 8, 'WoW Token');


-- item class
INSERT INTO catalogs (headId, codeInteger, value) VALUES (2, 0, 'Consumables');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (2, 1, 'Containers');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (2, 2, 'Weapons');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (2, 3, 'Gems');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (2, 4, 'Armor');


-- item subclass
INSERT INTO catalogs (headId, codeInteger, value) VALUES (3, 0, 'Miscellaneous');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (3, 1, 'Cloth');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (3, 2, 'Leather');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (3, 3, 'Mail');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (3, 4, 'Plate');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (3, 5, 'Cosmetic');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (3, 6, 'Shields');


-- inventory slot
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 1, 'Head');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 3, 'Shoulder');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 5, 'Chest');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 6, 'Waist');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 7, 'Legs');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 8, 'Feet');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 9, 'Wrist');
INSERT INTO catalogs (headId, codeInteger, value) VALUES (4, 10, 'Hands');
