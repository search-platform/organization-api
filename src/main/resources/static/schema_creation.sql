CREATE TABLE "organization" (
                                "id" SERIAL PRIMARY KEY,
                                "type" varchar,
                                "name" varchar,
                                "address" varchar,
                                "logoUrl" varchar,
                                "name_vector_eng" tsvector GENERATED ALWAYS AS (to_tsvector('english', name)) STORED,
                                "address_vector_eng" tsvector GENERATED ALWAYS AS (to_tsvector('english', address)) STORED
);

CREATE TABLE "contact" (
                           "id" SERIAL PRIMARY KEY,
                           "type" varchar,
                           "description" varchar,
                           "value" varchar,
                           "organization_id" bigint,
                           "value_vector_eng" tsvector GENERATED ALWAYS AS (to_tsvector('english', "value")) STORED
);

CREATE TABLE "organization_common_vector" (
                                              "organization_id" bigint,
                                              "search_vector" tsvector
);

ALTER TABLE "contact" ADD FOREIGN KEY ("organization_id") REFERENCES "organization" ("id");

ALTER TABLE "organization_common_vector" ADD FOREIGN KEY ("organization_id") REFERENCES "organization" ("id");
