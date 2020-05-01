#!/bin/bash
cd /geo_data

rm -rf shp
mkdir shp

if [ -f /geo_data/archives/civ_admbndp_admALL_cntig_ocha_itos_20180706.tar.xz ]; then
  tar -xJf /geo_data/archives/civ_admbndp_admALL_cntig_ocha_itos_20180706.tar.xz -C /geo_data/shp
else
  echo "file cote ivoire administrative boundaries not found"
  exit 1
fi

chown -R root:root /geo_data/shp/*
chmod a+x /geo_data/shp/*
echo "extract Done"
shp2pgsql -s 2154  /geo_data/shp/civ_admbndp_admALL_cntig_ocha_itos_20180706/civ_admbndp_admALL_cntig_ocha_itos_20180706.shp public.administrative_boundaries_cote_ivoire > import-administrative_boundaries_cote_ivoire.sql
echo "import-administrative_boundaries_cote_ivoire.sql file generation Done"


