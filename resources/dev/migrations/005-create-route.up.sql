-- Creation
CREATE TABLE "route" (
    "id" CHAR(36) PRIMARY KEY,
    "climbing_session_id" CHAR(36),
    "color_id" CHAR(36),
    "nb_climbed" INT,
    FOREIGN KEY("climbing_session_id") REFERENCES "climbing_session"("id"),
    FOREIGN KEY("color_id") REFERENCES "color"("id"));