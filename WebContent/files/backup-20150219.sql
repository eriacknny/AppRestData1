--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.1
-- Started on 2015-03-05 11:50:31

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2054 (class 1262 OID 16394)
-- Name: rest; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE rest WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Bolivarian Republic of Venezuela.1252' LC_CTYPE = 'Spanish_Bolivarian Republic of Venezuela.1252';


ALTER DATABASE rest OWNER TO postgres;

\connect rest

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 186 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 186
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 16410)
-- Name: header; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE header (
    id integer NOT NULL,
    id_request integer NOT NULL,
    name text,
    value text,
    status "char"
);


ALTER TABLE header OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16408)
-- Name: header_id_request_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE header_id_request_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE header_id_request_seq OWNER TO postgres;

--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 175
-- Name: header_id_request_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE header_id_request_seq OWNED BY header.id_request;


--
-- TOC entry 174 (class 1259 OID 16406)
-- Name: header_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE header_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE header_id_seq OWNER TO postgres;

--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 174
-- Name: header_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE header_id_seq OWNED BY header.id;


--
-- TOC entry 185 (class 1259 OID 16479)
-- Name: parameter; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE parameter (
    id integer NOT NULL,
    id_request integer NOT NULL,
    field_value text,
    type text,
    length_min integer,
    length_max integer,
    required boolean,
    value text,
    status "char"
);


ALTER TABLE parameter OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16477)
-- Name: parameter_id_request_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE parameter_id_request_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE parameter_id_request_seq OWNER TO postgres;

--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 184
-- Name: parameter_id_request_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE parameter_id_request_seq OWNED BY parameter.id_request;


--
-- TOC entry 183 (class 1259 OID 16475)
-- Name: parameter_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE parameter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE parameter_id_seq OWNER TO postgres;

--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 183
-- Name: parameter_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE parameter_id_seq OWNED BY parameter.id;


--
-- TOC entry 173 (class 1259 OID 16397)
-- Name: request; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE request (
    id integer NOT NULL,
    url character varying(200),
    json json,
    name text,
    "time" timestamp without time zone,
    status "char"
);


ALTER TABLE request OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16395)
-- Name: request_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE request_id_seq OWNER TO postgres;

--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 172
-- Name: request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE request_id_seq OWNED BY request.id;


--
-- TOC entry 179 (class 1259 OID 16429)
-- Name: response_expected; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE response_expected (
    id integer NOT NULL,
    id_request integer NOT NULL,
    name character varying(100),
    status "char",
    json text,
    cod_status text
);


ALTER TABLE response_expected OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16460)
-- Name: response_receved; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE response_receved (
    id integer NOT NULL,
    id_request integer NOT NULL,
    status "char",
    status_response text,
    json text,
    duration integer,
    message text,
    json_send text,
    result text
);


ALTER TABLE response_receved OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16458)
-- Name: response_receved_id_request_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE response_receved_id_request_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE response_receved_id_request_seq OWNER TO postgres;

--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 181
-- Name: response_receved_id_request_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE response_receved_id_request_seq OWNED BY response_receved.id_request;


--
-- TOC entry 180 (class 1259 OID 16456)
-- Name: response_receved_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE response_receved_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE response_receved_id_seq OWNER TO postgres;

--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 180
-- Name: response_receved_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE response_receved_id_seq OWNED BY response_receved.id;


--
-- TOC entry 178 (class 1259 OID 16427)
-- Name: respose_expected_id_request_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE respose_expected_id_request_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE respose_expected_id_request_seq OWNER TO postgres;

--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 178
-- Name: respose_expected_id_request_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE respose_expected_id_request_seq OWNED BY response_expected.id_request;


--
-- TOC entry 177 (class 1259 OID 16425)
-- Name: respose_expected_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE respose_expected_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE respose_expected_id_seq OWNER TO postgres;

--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 177
-- Name: respose_expected_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE respose_expected_id_seq OWNED BY response_expected.id;


--
-- TOC entry 1919 (class 2604 OID 16413)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY header ALTER COLUMN id SET DEFAULT nextval('header_id_seq'::regclass);


--
-- TOC entry 1920 (class 2604 OID 16414)
-- Name: id_request; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY header ALTER COLUMN id_request SET DEFAULT nextval('header_id_request_seq'::regclass);


--
-- TOC entry 1925 (class 2604 OID 16482)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parameter ALTER COLUMN id SET DEFAULT nextval('parameter_id_seq'::regclass);


--
-- TOC entry 1926 (class 2604 OID 16483)
-- Name: id_request; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parameter ALTER COLUMN id_request SET DEFAULT nextval('parameter_id_request_seq'::regclass);


--
-- TOC entry 1918 (class 2604 OID 16400)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY request ALTER COLUMN id SET DEFAULT nextval('request_id_seq'::regclass);


--
-- TOC entry 1921 (class 2604 OID 16432)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY response_expected ALTER COLUMN id SET DEFAULT nextval('respose_expected_id_seq'::regclass);


--
-- TOC entry 1922 (class 2604 OID 16433)
-- Name: id_request; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY response_expected ALTER COLUMN id_request SET DEFAULT nextval('respose_expected_id_request_seq'::regclass);


--
-- TOC entry 1923 (class 2604 OID 16463)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY response_receved ALTER COLUMN id SET DEFAULT nextval('response_receved_id_seq'::regclass);


--
-- TOC entry 1924 (class 2604 OID 16464)
-- Name: id_request; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY response_receved ALTER COLUMN id_request SET DEFAULT nextval('response_receved_id_request_seq'::regclass);


--
-- TOC entry 1930 (class 2606 OID 16419)
-- Name: header_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY header
    ADD CONSTRAINT header_key PRIMARY KEY (id);


--
-- TOC entry 1932 (class 2606 OID 16438)
-- Name: id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY response_expected
    ADD CONSTRAINT id_key PRIMARY KEY (id);


--
-- TOC entry 1934 (class 2606 OID 16469)
-- Name: id_keyR; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY response_receved
    ADD CONSTRAINT "id_keyR" PRIMARY KEY (id);


--
-- TOC entry 1936 (class 2606 OID 16488)
-- Name: parameter_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parameter
    ADD CONSTRAINT parameter_key PRIMARY KEY (id);


--
-- TOC entry 1928 (class 2606 OID 16405)
-- Name: request_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY request
    ADD CONSTRAINT request_key PRIMARY KEY (id);


--
-- TOC entry 1938 (class 2606 OID 16439)
-- Name: id_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY response_expected
    ADD CONSTRAINT id_foreign FOREIGN KEY (id_request) REFERENCES request(id);


--
-- TOC entry 1939 (class 2606 OID 16470)
-- Name: id_foreignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY response_receved
    ADD CONSTRAINT "id_foreignKey" FOREIGN KEY (id_request) REFERENCES request(id);


--
-- TOC entry 1937 (class 2606 OID 16420)
-- Name: request_foreignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY header
    ADD CONSTRAINT "request_foreignKey" FOREIGN KEY (id_request) REFERENCES request(id);


--
-- TOC entry 1940 (class 2606 OID 16489)
-- Name: request_foreingKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parameter
    ADD CONSTRAINT "request_foreingKey" FOREIGN KEY (id_request) REFERENCES request(id);


--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-03-05 11:50:31

--
-- PostgreSQL database dump complete
--

