#!/bin/bash

set -e
set -u


echo "creating mutations table"
psql -d $INSTANT_DB_DATABASE -U postgres <<-EOSQL
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
COPY mutations FROM '/geo_data/shp/R93_Provence_Alpes_Cote_d_Azur/r93_mutation.csv' DELIMITERS ',' CSV HEADER;
ALTER TABLE mutations OWNER TO instant
EOSQL

echo "insert shp dumped .sql files"


psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import_communes.sql

psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-mutation_geomlocmut.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-geomloc.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-par.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-parmut.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-mutation_geompar.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-parcelle_geomloc.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-locmut.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-parcelle_geompar.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/pginsert/import-mutation_geomparmut.sql

psql -d $INSTANT_DB_DATABASE -U postgres <<-EOSQL
  ALTER TABLE communes OWNER TO instant;
  ALTER TABLE mutation_geomlocmut OWNER TO instant;
  ALTER TABLE geomloc OWNER TO instant;
  ALTER TABLE par OWNER TO instant;
  ALTER TABLE parmut OWNER TO instant;
  ALTER TABLE mutation_geompar OWNER TO instant;
  ALTER TABLE parcelle_geomloc OWNER TO instant;
  ALTER TABLE locmut OWNER TO instant;
  ALTER TABLE parcelle_geompar OWNER TO instant;
  ALTER TABLE mutation_geomparmut OWNER TO instant;

EOSQL