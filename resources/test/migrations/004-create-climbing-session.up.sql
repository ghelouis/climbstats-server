-- Creation
CREATE TABLE "climbing_session" (
    "id" CHAR(36) PRIMARY KEY,
    "user_id" CHAR(36),
    "location_id" CHAR(36),
    "comments" VARCHAR(500),
    "date" TIMESTAMP,
    FOREIGN KEY("user_id") REFERENCES "user"("id"),
    FOREIGN KEY("location_id") REFERENCES "location"("id"));

--;;

-- Insertion
INSERT INTO "climbing_session" VALUES ('75de375a-b109-4a10-a3de-c6e6d5d6f424', '9133b12e-93af-4d06-9536-898ae0ef6747', '71906266-e7eb-428d-a99a-b27898f44ee8', 'top!', {ts '2012-09-17 18:47:52.69'});
INSERT INTO "climbing_session" VALUES ('75de375a-b109-4a10-a3de-c6e6d5d6f425', '8e409705-fb4e-4b46-b976-7292fdfd7a96', '71906266-e7eb-428d-a99a-b27898f44ee8', 'génial!', {ts '2017-02-13 10:45:43.69'});
