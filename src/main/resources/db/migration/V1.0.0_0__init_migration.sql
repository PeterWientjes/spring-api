-- Table: public.store

-- DROP TABLE IF EXISTS public.store;

CREATE TABLE IF NOT EXISTS public.store
(
    id bigint NOT NULL,
    api_key character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT store_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.store
    OWNER to postgres;

-- Table: public.store_platform_connection

-- DROP TABLE IF EXISTS public.store_platform_connection;

CREATE TABLE IF NOT EXISTS public.store_platform_connection
(
    platform_type smallint,
    id bigint NOT NULL,
    store bigint,
    CONSTRAINT store_platform_connection_pkey PRIMARY KEY (id),
    CONSTRAINT fkoj5mprtv5gl51gino7tnd7ecq FOREIGN KEY (store)
        REFERENCES public.store (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT store_platform_connection_platform_type_check CHECK (platform_type >= 0 AND platform_type <= 2)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.store_platform_connection
    OWNER to postgres;

-- Table: public.connection_credentials

-- DROP TABLE IF EXISTS public.connection_credentials;

CREATE TABLE IF NOT EXISTS public.connection_credentials
(
    id bigint NOT NULL,
    store_platform_connection bigint NOT NULL,
    pixel_access_token character varying(255) COLLATE pg_catalog."default",
    pixel_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT connection_credentials_pkey PRIMARY KEY (id),
    CONSTRAINT fk3k10e4dehs0fpncyhilroyak5 FOREIGN KEY (store_platform_connection)
        REFERENCES public.store_platform_connection (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.connection_credentials
    OWNER to postgres;

-- SEQUENCE: public.store_seq

-- DROP SEQUENCE IF EXISTS public.store_seq;

CREATE SEQUENCE IF NOT EXISTS public.store_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.store_seq
    OWNER TO postgres;

-- SEQUENCE: public.connection_credentials_seq

-- DROP SEQUENCE IF EXISTS public.connection_credentials_seq;

CREATE SEQUENCE IF NOT EXISTS public.connection_credentials_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.connection_credentials_seq
    OWNER TO postgres;

-- SEQUENCE: public.store_platform_connection_seq

-- DROP SEQUENCE IF EXISTS public.store_platform_connection_seq;

CREATE SEQUENCE IF NOT EXISTS public.store_platform_connection_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.store_platform_connection_seq
    OWNER TO postgres;


-- Table: public.order

-- DROP TABLE IF EXISTS public."order";

CREATE TABLE IF NOT EXISTS public."order"
(
    price real,
    id character varying(255) NOT NULL,
    store bigint,
    "timestamp" bigint,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT "FKf20pq3q4v582wh3x0cxmrp7th" FOREIGN KEY (store)
        REFERENCES public.store (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."order"
    OWNER to postgres;