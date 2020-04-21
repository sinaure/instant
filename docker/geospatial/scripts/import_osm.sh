#!/bin/bash

echo "Download France PBF start..."
curl -L -f https://download.geofabrik.de/africa/ivory-coast-latest.osm.pbf --create-dirs -o /app/src/coteivoire-latest.osm.pbf
osmconvert coteivoire-latest.osm.pbf >coteivoire-latest.osm

echo "End curl PBF file"

osm2pgsql -c --slim -H postgres -d $INSTANT_DB_DATABASE -U $INSTANT_DB_USER  -C 10000  /app/src/coteivoire-latest.osm
echo "Import PBF file done"

curl -L -f http://download.geofabrik.de/europe/monaco-latest.osm.pbf --create-dirs -o /app/src/monaco-latest.osm.pbf
osmconvert monaco-latest.osm.pbf >monaco-latest.osm
echo "End curl PBF file"

osm2pgsql -c --slim -H postgres -d $INSTANT_DB_DATABASE -U $INSTANT_DB_USER -C 10000 /app/src/monaco-latest.osm
echo "Import PBF file done"
