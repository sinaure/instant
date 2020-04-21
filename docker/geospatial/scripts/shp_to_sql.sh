#!/bin/bash
cd /geo_data

rm -rf shp pginsert
mkdir shp
mkdir pginsert

if [ -f /geo_data/archives/R93_Provence_Alpes_Cote_d_Azur.tar.xz ]; then
  tar -xJf /geo_data/archives/R93_Provence_Alpes_Cote_d_Azur.tar.xz -C /geo_data/shp
else
  echo "file R93_Provence_Alpes_Cote_d_Azur not found"
  exit 1
fi
if [ -f /geo_data/archives/communes-20200101-shp.tar.xz ]; then
  tar -xJf /geo_data/archives/communes-20200101-shp.tar.xz -C /geo_data/shp
else
  echo "file communes not found"
  exit 1
fi

chown -R root:root /geo_data/shp/*
chmod a+x /geo_data/shp/*
echo "extract Done"
shp2pgsql -s 2154  /geo_data/shp/R93_Provence_Alpes_Cote_d_Azur/r93_disposition_parcelle_geomloc.shp public.parcelle_geomloc > /geo_data/pginsert/import-parcelle_geomloc.sql
echo "import-parcelle_geomloc.sql file generation Done"

shp2pgsql -s 2154  /geo_data/shp/R93_Provence_Alpes_Cote_d_Azur/r93_disposition_parcelle_geompar.shp public.parcelle_geompar > ./geo_data/pginsert/import-parcelle_geompar.sql
echo "import-parcelle_geompar.sql file generation Done"

shp2pgsql -s 2154  /geo_data/shp/R93_Provence_Alpes_Cote_d_Azur/r93_local_geomloc.shp public.geomloc > ./geo_data/pginsert/import-geomloc.sql
echo "import-geomloc.sql file generation Done"

shp2pgsql -s 2154  /geo_data/shp/R93_Provence_Alpes_Cote_d_Azur/r93_mutation_geomlocmut.shp public.mutation_geomlocmut > ./geo_data/pginsert/import-mutation_geomlocmut.sql
echo "import-mutation_geomlocmut.sql file generation Done"

shp2pgsql -s 2154  /geo_data/shp/R93_Provence_Alpes_Cote_d_Azur/r93_mutation_geompar.shp public.mutation_geompar > ./geo_data/pginsert/import-mutation_geompar.sql
echo "import-mutation_geompar.sql file generation Done"

shp2pgsql -s 2154  /geo_data/shp/R93_Provence_Alpes_Cote_d_Azur/r93_mutation_geomparmut.shp public.mutation_geomparmut > ./geo_data/pginsert/import-mutation_geomparmut.sql
echo "import-mutation_geomparmut.sql file generation Done"
shp2pgsql -s 2154  /geo_data/shp/communes-20200101-shp/communes-20200101.shp public.communes > ./geo_data/pginsert/import_communes.sql
echo "All sql file generation Done"
