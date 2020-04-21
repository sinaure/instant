#!/bin/bash

set -e
set -u

echo "create views"

psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/views/create_views.sql
psql -d $INSTANT_DB_DATABASE -U postgres -f /geo_data/views/create_views_by_codinsee.sql