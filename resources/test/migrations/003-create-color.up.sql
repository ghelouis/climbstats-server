-- Creation
CREATE TABLE "color" ("id" CHAR(36) PRIMARY KEY, "location_id" CHAR(36), "name" CHAR(36), "value" INT);

--;;

-- Insertion
INSERT INTO "color" VALUES ('c2a48c40-1f56-47d4-bd7c-f228b818751f', '71906266-e7eb-428d-a99a-b27898f44ee8', 'jaune', 1);
INSERT INTO "color" VALUES ('ee92deba-ed88-46b9-b0b4-ed842b713a19', '71906266-e7eb-428d-a99a-b27898f44ee8', 'vert', 3);
INSERT INTO "color" VALUES ('b0de78cd-f5cd-448a-b9de-3d234db0b8a1', '71906266-e7eb-428d-a99a-b27898f44ee8', 'bleu', 7);
INSERT INTO "color" VALUES ('4971b9a8-5c14-4fe6-ae55-0d5882d1c04f', '71906266-e7eb-428d-a99a-b27898f44ee8', 'rouge', 21);
INSERT INTO "color" VALUES ('5c804b07-090a-43a4-bb03-d8f414f32b3e', '71906266-e7eb-428d-a99a-b27898f44ee8', 'noir', 50);
INSERT INTO "color" VALUES ('36fce666-c5ac-4ff4-9a88-f6cd313363fd', '71906266-e7eb-428d-a99a-b27898f44ee8', 'violet', 100);

INSERT INTO "color" VALUES ('0061b75d-985c-4e17-b9e0-d559fa341ccc', '71906266-e7eb-428d-a99a-b27898f44ee9', 'jaune', 3);
INSERT INTO "color" VALUES ('a46f077b-ae16-4bb2-8cbd-0f4ff958c18b', '71906266-e7eb-428d-a99a-b27898f44ee9', 'vert', 10);
INSERT INTO "color" VALUES ('522a955a-1cc8-4b04-b0b2-b54c77f45198', '71906266-e7eb-428d-a99a-b27898f44ee9', 'bleu', 30);
