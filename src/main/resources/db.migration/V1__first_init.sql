DROP TABLE IF EXISTS "car_park_information";
DROP SEQUENCE IF EXISTS car_park_information_id_seq;
CREATE SEQUENCE car_park_information_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."car_park_information" (
    "id" bigint DEFAULT nextval('car_park_information_id_seq') NOT NULL,
    "car_park_number" character varying(12) NOT NULL,
    "address" character varying(90),
    "x_coordinate" double precision,
    "y_coordinate" double precision,
    "latitude" double precision,
    "longitude" double precision,
    "car_park_type" character varying(36),
    "parking_system_type" character varying(30),
    "short_term_parking" character varying(15),
    "is_free_parking" boolean,
    "is_night_parking" boolean,
    "car_park_decks" integer,
    "gantry_height" double precision,
    "is_car_park_basement" boolean,
    "status" character varying(15) NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "car_park_information_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

DROP TABLE IF EXISTS "car_park_availability";
DROP SEQUENCE IF EXISTS car_park_availability_id_seq;
CREATE SEQUENCE car_park_availability_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."car_park_availability" (
    "id" bigint DEFAULT nextval('car_park_availability_id_seq') NOT NULL,
    "car_park_number" character varying(12) NOT NULL,
    "lot_type" character varying(6),
    "total_lots" integer,
    "available_lots" integer,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "car_park_availability_pkey" PRIMARY KEY ("id")
) WITH (oids = false);