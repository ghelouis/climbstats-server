-- Creation
CREATE TABLE "route" (
    "id" CHAR(36) PRIMARY KEY,
    "climbing_session_id" CHAR(36),
    "color_id" CHAR(36),
    "nb_climbed" INT,
    FOREIGN KEY("climbing_session_id") REFERENCES "climbing_session"("id"),
    FOREIGN KEY("color_id") REFERENCES "color"("id"));

--;;

-- Insertion
INSERT INTO "route" VALUES ('0d3dda49-be9b-4731-98ba-7b5ef09a2ec9', '75de375a-b109-4a10-a3de-c6e6d5d6f424', 'c2a48c40-1f56-47d4-bd7c-f228b818751f', 8);
INSERT INTO "route" VALUES ('ebad21d9-c693-454b-b079-7de22b79f963', '75de375a-b109-4a10-a3de-c6e6d5d6f424', 'ee92deba-ed88-46b9-b0b4-ed842b713a19', 3);
