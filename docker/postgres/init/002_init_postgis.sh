#!/bin/bash

# See https://github.com/MartinKaburu/docker-postgresql-multiple-databases for usage and follow up

set -e
set -u


echo "creating postgis extension"
psql -d geodata -U postgres <<-EOSQL
	    CREATE EXTENSION postgis;
	    SELECT PostGIS_full_version();
EOSQL

echo "creating mutations table"
psql -d geodata -U postgres <<-EOSQL
	    CREATE TABLE IF NOT EXISTS mutations (
    idmutation bigint NOT NULL,
    idmutinvar character varying,
    idopendata character varying,
    idnatmut character varying,
    codservch character varying,
    refdoc character varying,
    datemut character varying,
    anneemut integer,
    moismut integer,
    coddep character varying,
    libnatmut character varying,	
    nbartcgi character varying,	
    l_artcgi character varying,	
    vefa character varying,	
    valeurfonc float,	
    nbdispo	integer, 
    nblot	integer, 
    nbcomm	integer, 
    l_codinsee text ARRAY,	
    nbsection integer,	
    l_section text ARRAY,	
    nbpar character varying,	
    l_idpar	text ARRAY,	
    nbparmut integer,
    l_idparmut text ARRAY,	
    nbsuf integer,	
    sterr bigint,	
    l_dcnt text ARRAY,	
	nbvolmut integer,	
	nblocmut integer,	
	l_idlocmut text ARRAY,	
	nblocmai integer,	
	nblocapt integer,	
	nblocdep integer,	
	nblocact integer,	
	nbapt1pp integer,	
	nbapt2pp integer,	
	nbapt3pp integer,	
	nbapt4pp integer,	
	nbapt5pp integer,	
	nbmai1pp integer,	
	nbmai2pp integer,	
	nbmai3pp integer,	
	nbmai4pp integer, 
	nbmai5pp integer,	
	sbati bigint,	
	sbatmai bigint,	
	sbatapt bigint,	
	sbatact bigint,	
	sapt1pp bigint,	
	sapt2pp bigint,	
	sapt3pp bigint,	
	sapt4pp bigint,	
	sapt5pp bigint,	
	smai1pp	bigint, 
	smai2pp bigint,	
	smai3pp bigint,	
	smai4pp bigint,	
	smai5pp bigint,	
	codtypbien integer,	
	libtypbien character varying	
);
COPY mutations FROM '/data/r93_mutation.csv' DELIMITERS ',' CSV HEADER;
EOSQL

echo "insert shp dumped .sql files"

psql -d geodata -U postgres -f /data/import.sql
psql -d geodata -U postgres -f /data/import_codes.sql
psql -d geodata -U postgres -f /data/import-par.sql
psql -d geodata -U postgres -f /data/import-parmut.sql

echo "create views"

psql -d geodata -U postgres -f /data/create_views.sql
psql -d geodata -U postgres -f /data/create_views_by_codinsee.sql