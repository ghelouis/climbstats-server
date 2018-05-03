-- Creation
CREATE TABLE "color" ("id" CHAR(36) PRIMARY KEY, "location_id" CHAR(36), "name" CHAR(36), "value" INT);

--;;

-- Insertion
INSERT INTO "color" VALUES ('c2a48c40-1f56-47d4-bd7c-f228b818751f', '0967076f-a913-482b-957b-84e236ab8efd', 'Jaune', 5);
INSERT INTO "color" VALUES ('ee92deba-ed88-46b9-b0b4-ed842b713a19', '0967076f-a913-482b-957b-84e236ab8efd', 'Vert', 15);
INSERT INTO "color" VALUES ('b0de78cd-f5cd-448a-b9de-3d234db0b8a1', '0967076f-a913-482b-957b-84e236ab8efd', 'Bleu', 30);
INSERT INTO "color" VALUES ('4971b9a8-5c14-4fe6-ae55-0d5882d1c04f', '0967076f-a913-482b-957b-84e236ab8efd', 'Rouge', 70);
INSERT INTO "color" VALUES ('5c804b07-090a-43a4-bb03-d8f414f32b3e', '0967076f-a913-482b-957b-84e236ab8efd', 'Noir', 150);
INSERT INTO "color" VALUES ('36fce666-c5ac-4ff4-9a88-f6cd313363fd', '0967076f-a913-482b-957b-84e236ab8efd', 'Violet', 300);

INSERT INTO "color" VALUES ('65b9db74-b1c4-4a88-85a2-f600a03def78', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Orange', 1);
INSERT INTO "color" VALUES ('7a63e22a-b8ac-4ce4-a0ee-a5b845c5cf0a', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Jaune', 2);
INSERT INTO "color" VALUES ('c605b60a-a088-457d-ae09-e0a4f0dc8bd4', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Vert', 5);
INSERT INTO "color" VALUES ('80d5ba51-a9c1-469a-94be-9b9e983929f8', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Bleu', 15);
INSERT INTO "color" VALUES ('c964f2b4-df15-493a-a3c3-faf8a0a30dfc', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Rouge', 30);
INSERT INTO "color" VALUES ('56ce2b44-f97d-49d5-89aa-703563b0994e', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Noir', 70);
INSERT INTO "color" VALUES ('c4865381-faf6-4bce-9de3-a33610f5b48b', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Blanc', 150);
INSERT INTO "color" VALUES ('6998611c-3c02-44c4-a51b-5702b8757bca', 'c34dbf49-75f3-473b-b4e5-1091f93c41b7', 'Violet', 300);
