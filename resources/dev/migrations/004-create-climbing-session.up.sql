-- Creation
CREATE TABLE "climbing_session" (
    "id" CHAR(36) PRIMARY KEY,
    "user_id" CHAR(36),
    "location_id" CHAR(36),
    "comments" VARCHAR(500),
    "date" TIMESTAMP,
    FOREIGN KEY("user_id") REFERENCES "user"("id"),
    FOREIGN KEY("location_id") REFERENCES "location"("id"));
