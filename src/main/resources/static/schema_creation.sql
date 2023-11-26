CREATE TABLE "user" (
    "id" SERIAL PRIMARY KEY,
    "login" varchar,
    "password" varchar
);

CREATE TABLE "country" (
                           "id" SERIAL PRIMARY KEY,
                           "name" varchar
);

CREATE TABLE "organization" (
                                "id" SERIAL PRIMARY KEY,
                                "type" varchar,
                                "name" varchar,
                                "address" varchar,
                                "logo_url" varchar,
                                "name_vector_eng" tsvector GENERATED ALWAYS AS (to_tsvector('english', name)) STORED,
                                "address_vector_eng" tsvector GENERATED ALWAYS AS (to_tsvector('english', address)) STORED,
                                "county_id" bigint
);
ALTER TABLE "organization" ADD FOREIGN KEY ("county_id") REFERENCES "country" ("id");

CREATE TABLE "contact" (
                           "id" SERIAL PRIMARY KEY,
                           "type" varchar,
                           "description" varchar,
                           "value" varchar,
                           "organization_id" bigint,
                           "value_vector_eng" tsvector GENERATED ALWAYS AS (to_tsvector('english', "value")) STORED
);
ALTER TABLE "contact" ADD FOREIGN KEY ("organization_id") REFERENCES "organization" ("id");

CREATE TABLE "organization_common_vector" (
                                            "organization_id" bigint,
                                            "search_vector" tsvector
);

ALTER TABLE "organization_common_vector" ADD FOREIGN KEY ("organization_id") REFERENCES "organization" ("id");
