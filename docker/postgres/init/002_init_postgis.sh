#!/bin/bash

# See https://github.com/MartinKaburu/docker-postgresql-multiple-databases for usage and follow up

set -e
set -u


echo "creating postgis extension"
psql -d geodata -U postgres <<-EOSQL
	    CREATE EXTENSION postgis;
	    SELECT PostGIS_full_version();
EOSQL
