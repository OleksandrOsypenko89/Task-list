CREATE TABLE public.task (
                             id integer NOT NULL,
                             description character varying(255),
                             status smallint
);

CREATE SEQUENCE public.task_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;