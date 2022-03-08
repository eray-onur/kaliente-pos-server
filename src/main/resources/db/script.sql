--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1 (Debian 14.1-1.pgdg110+1)
-- Dumped by pg_dump version 14.1

-- Started on 2022-03-08 22:04:59

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3357 (class 1262 OID 25139)
-- Name: kalientedb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE kalientedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE kalientedb OWNER TO postgres;


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 25140)
-- Name: application_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.application_user (
    id uuid NOT NULL,
    is_active boolean NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    user_name character varying(255)
);


ALTER TABLE public.application_user OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 25147)
-- Name: privilege; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.privilege (
    id uuid NOT NULL,
    is_active boolean NOT NULL,
    title character varying(255)
);


ALTER TABLE public.privilege OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 25152)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id uuid NOT NULL,
    is_active boolean NOT NULL,
    description character varying(255),
    price double precision NOT NULL,
    title character varying(255),
    catalogue_id uuid
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 25159)
-- Name: product_catalogue; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_catalogue (
    id uuid NOT NULL,
    is_active boolean NOT NULL,
    description character varying(255),
    title character varying(255),
    parent_catalogue_id uuid
);


ALTER TABLE public.product_catalogue OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 25166)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id uuid NOT NULL,
    is_active boolean NOT NULL,
    title character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 25171)
-- Name: role_privileges; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_privileges (
    role_id uuid NOT NULL,
    privilege_id uuid NOT NULL
);


ALTER TABLE public.role_privileges OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 25174)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    user_id uuid NOT NULL,
    role_id uuid NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 3345 (class 0 OID 25140)
-- Dependencies: 209
-- Data for Name: application_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.application_user (id, is_active, email, first_name, last_name, password, user_name) VALUES ('fade0622-d157-45ba-bcbe-6845b4592bf5', true, 'Superadmin@Admin.com', 'Superadmin', 'Superadmin', '$2a$10$Ml3goLI9RY8cgiENy8oZleoY8oK89ghkF8neOExGcx30FLKAL0.EW', NULL);
INSERT INTO public.application_user (id, is_active, email, first_name, last_name, password, user_name) VALUES ('308a9732-ecda-4e11-b330-4001adaf762f', true, 'Admin@Admin.com', 'Admin', 'Admin', '$2a$10$GBACrhQ2lYAry0I2Us0SA.RO0OudXPUh1tnUS.5nxSomoqeq13nd2', NULL);
--
-- TOC entry 3346 (class 0 OID 25147)
-- Dependencies: 210
-- Data for Name: privilege; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.privilege (id, is_active, title) VALUES ('28780d80-b4c8-41f3-bb51-39935baa37ca', true, 'READ_PRIVILEGE');
INSERT INTO public.privilege (id, is_active, title) VALUES ('aa710dbc-307b-4f6c-b44e-727ff825d70c', true, 'WRITE_PRIVILEGE');


--
-- TOC entry 3347 (class 0 OID 25152)
-- Dependencies: 211
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3348 (class 0 OID 25159)
-- Dependencies: 212
-- Data for Name: product_catalogue; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3349 (class 0 OID 25166)
-- Dependencies: 213
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, is_active, title) VALUES ('dbd14113-0e17-4522-8daa-93e6f9fb13c9', true, 'ROLE_SUPERADMIN');
INSERT INTO public.role (id, is_active, title) VALUES ('f52b378c-8fb0-439c-a574-5031ee3e767c', true, 'ROLE_ADMIN');
INSERT INTO public.role (id, is_active, title) VALUES ('4868f6ce-4f25-40b0-9e3f-f10833bd3f9c', true, 'ROLE_PERSONNEL');


--
-- TOC entry 3350 (class 0 OID 25171)
-- Dependencies: 214
-- Data for Name: role_privileges; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('dbd14113-0e17-4522-8daa-93e6f9fb13c9', '28780d80-b4c8-41f3-bb51-39935baa37ca');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('dbd14113-0e17-4522-8daa-93e6f9fb13c9', 'aa710dbc-307b-4f6c-b44e-727ff825d70c');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('f52b378c-8fb0-439c-a574-5031ee3e767c', '28780d80-b4c8-41f3-bb51-39935baa37ca');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('f52b378c-8fb0-439c-a574-5031ee3e767c', 'aa710dbc-307b-4f6c-b44e-727ff825d70c');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('4868f6ce-4f25-40b0-9e3f-f10833bd3f9c', '28780d80-b4c8-41f3-bb51-39935baa37ca');


--
-- TOC entry 3351 (class 0 OID 25174)
-- Dependencies: 215
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_roles (user_id, role_id) VALUES ('fade0622-d157-45ba-bcbe-6845b4592bf5', 'dbd14113-0e17-4522-8daa-93e6f9fb13c9');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('308a9732-ecda-4e11-b330-4001adaf762f', 'f52b378c-8fb0-439c-a574-5031ee3e767c');


--
-- TOC entry 3191 (class 2606 OID 25146)
-- Name: application_user application_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.application_user
    ADD CONSTRAINT application_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 25151)
-- Name: privilege privilege_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.privilege
    ADD CONSTRAINT privilege_pkey PRIMARY KEY (id);


--
-- TOC entry 3197 (class 2606 OID 25165)
-- Name: product_catalogue product_catalogue_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_catalogue
    ADD CONSTRAINT product_catalogue_pkey PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 25158)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 25170)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 25187)
-- Name: role_privileges fk9bh6h5cm4bq0u3q9pcotkydq8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_privileges
    ADD CONSTRAINT fk9bh6h5cm4bq0u3q9pcotkydq8 FOREIGN KEY (privilege_id) REFERENCES public.privilege(id);


--
-- TOC entry 3201 (class 2606 OID 25182)
-- Name: product_catalogue fk9uwhogmfrgmbcvriufi4vcy3f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_catalogue
    ADD CONSTRAINT fk9uwhogmfrgmbcvriufi4vcy3f FOREIGN KEY (parent_catalogue_id) REFERENCES public.product_catalogue(id);


--
-- TOC entry 3203 (class 2606 OID 25192)
-- Name: role_privileges fkgelpp2j5e63axp7bcguwaqec5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_privileges
    ADD CONSTRAINT fkgelpp2j5e63axp7bcguwaqec5 FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 3200 (class 2606 OID 25177)
-- Name: product fkidv301qbxl7t4ps9fco1lmb7y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkidv301qbxl7t4ps9fco1lmb7y FOREIGN KEY (catalogue_id) REFERENCES public.product_catalogue(id);


--
-- TOC entry 3205 (class 2606 OID 25202)
-- Name: user_roles fkq0h6vpf3crn504yyep1hdv0vc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkq0h6vpf3crn504yyep1hdv0vc FOREIGN KEY (user_id) REFERENCES public.application_user(id);


--
-- TOC entry 3204 (class 2606 OID 25197)
-- Name: user_roles fkrhfovtciq1l558cw6udg0h0d3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3 FOREIGN KEY (role_id) REFERENCES public.role(id);


-- Completed on 2022-03-08 22:04:59

--
-- PostgreSQL database dump complete
--

