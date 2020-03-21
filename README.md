# Technology stack used

* AMQP RABBITMQ
* Typescript - NODEJS client
* Spring Boot
* Maven
* Timescaledb
* Postgis
* Docker

# Quick start

* Clone the repository

```
git clone https://github.com/sinaure/instant.git
```

* Add host alias in /etc/hosts file

```
192.168.99.100 instant-host
```

* start the app in dev mode:

```
mvn clean install
cd docker 
make build run-dev
mvn spring-boot:run 
```

* check database has been properly set up:

```
CREATE EXTENSION IF NOT EXISTS postgis;
SELECT create_hypertable('instant_parking', 'observed_at', if_not_exists => TRUE);
```
Working with hypertables can help us to get insight and analytics on parking usage stats




# Demo

REST API accessible at : http://localhost:8080/parking

After the App is bootstrapped every 1 minute data are fetched from  
https://data.rennesmetropole.fr/explore/dataset/export-api-parking-citedia/api/

Every 1 min the Scheduler check that the retrieved object has changed and push a payload via AMQP protocol to message broker RabbitMQ
Retrieved data are also stored in Timescqledb/Postgis database to have an history of changements. 

Endpoints are:

* http://localhost:8080/parking/instant/Arsenal 

```
{"observedAt":"2019-12-04T14:09:57.690Z","free":261,"max":605,"name":"Arsenal","location":{"type":"Point","coordinates":[-1.6847819587,48.1043058108]}}
```

This endpoint provide the last provided info by Parking name and can be used mainly for debug purposes.

* http://localhost:8080/parking/instant/Arsenal/temporal?start=2019-12-03T14:15:56.893Z&end=2019-12-05T14:15:56.893Z 



This endpoint will provide insight for revenue management and stats on parking usage 


# Bonus

a ui client has been set up under docker/subscriber. Is a very simple example of amqp client based on NODEJS. This technology is well suited for UI applications (es. a Map to show the points).
The data are feeded to the client UI application in Real Time. 

```
cd docker/subscriber
npm start prod
```

# Add to postgis

shp2pgsql -s 2154  R93_Provence_Alpes_Cote_d_Azur/r93_mutation_geomlocmut.shp public.geomlocmut > import-locmut.sql
shp2pgsql -s 2154  R93_Provence_Alpes_Cote_d_Azur/r93_mutation_geomlocmut.shp public.geompar > import-par.sql
shp2pgsql -s 2154  R93_Provence_Alpes_Cote_d_Azur/r93_mutation_geomlocmut.shp public.geomparmut > import-parmut.sql
shp2pgsql -s 2154  data\code-postal-code-insee-2015\code-postal-code-insee-2015.shp public.codes > import_codes.sql

psql -d geodata -h localhost -p 5342 -U postgres -f import.sql



CREATE VIEW mutations_apt_view AS SELECT t1.*,t2.geom
FROM   mutations  t1
LEFT JOIN   geomlocmut t2 ON t1.idmutation = t2.idmutation::bigint;

DROP VIEW mutations_all_view CASCADE;
CREATE MATERIALIZED VIEW mutations_all_view AS SELECT 
t1.*,
t1.l_codinsee[1] as codinsee,
ST_AsGeoJSON(ST_Transform(t2.geom,4326)) as geometry_point, ST_AsGeoJSON(ST_Transform(t3.geom,4326)) as geometry_parcel, ST_AsGeoJSON(ST_Transform(t4.geom,4326)) as geometry_parcel_mutation
FROM   mutations  t1
LEFT JOIN   geomlocmut t2 ON t1.idmutation = t2.idmutation::bigint
LEFT JOIN   r93_mutation_geompar t3 ON t1.idmutation = t3.idmutation::bigint
LEFT JOIN   r93_mutation_geomparmut t4 ON t1.idmutation = t4.idmutation::bigint
;



http://doc-datafoncier.cerema.fr/dv3f/doc/table/local

most frequent sell:

SELECT codtypbien, libtypbien, COUNT(*) as occurrences  from mutations_view GROUP BY codtypbien ORDER BY occurrences DESC; 

"121"	234502    APPARTAMENT
"111"	113397    MAISON
"131"	33262     DEPENDANCE (garage)
"101"	26141     BATI - INDETERMINE
"14"	19788     ACTIVITE'
"21"	16035     TERRAIN DE TYPE TAB
"2313"	12157     TERRAIN DE TYPE TERRE ET PRE
"122"	10926     DEUX APPARTAMENTS
"229"	8936      TERRAIN ARTIFICIALISE MIXTE
"152"	6174      BATI MIXTE - LOGEMENT/ACTIVITE
"233"	5177      TERRAIN LANDES ET EAUX
"232"	4956      TERRAIN FORESTIER
"20"	4883      TERRAIN NON BATI INDETERMINE
"2311"	4546      TERRAIN VITICOLE
"132"	4412
"112"	4193
"120"	4185      APPARTEMENT INDETERMINE
"2312"	3134      TERRAIN VERGER
"221"	1925
"151"	1535
"102"	1310
"239"	326
"223"	43
"2319"	35
"110"	22
"222"	12 

most valuable sell:
SELECT codtypbien, libtypbien, SUM(valeurfonc) as val  from mutations_view GROUP BY codtypbien ORDER BY val DESC; 

"121"	44230553199.4      APPARTAMENT
"111"	42516967954.15     MAISON
"14"	8508845083.41998   ACTIVITE
"101"	7002485610.02001   BATI - INDETERMINE
"152"	4222590678.34      BATI MIXTE - LOGEMENT/ACTIVITE
"21"	3841454162.44      TERRAIN DE TYPE TAB
"112"	3315681601.97      DES MAISONS
"122"	3235751251.57      DEUX APPARTAMENTS
"102"	3193216879.44      BATI - INDETERMINE
"120"	2252825350.27      APPARTEMENT INDETERMINE
"229"	1228698244.06      TERRAIN ARTIFICIALISE MIXTE
"131"	1022396708.79      DEPENDANCE (garage)
"2313"	991442775.54
"151"	842929816.92
"20"	736647339.9
"2311"	431100820.02
"232"	389401215.76
"132"	336810517.09
"233"	247087391.44
"221"	202940303.99
"2312"	195849332.24
"239"	11397886.14
"110"	3150290
"223"	1730440.61
"2319"	996348
"222"	639671

find code where a lot of transactions done for TERRAIN:

DROP VIEW mutations_by_codinsee CASCADE;
CREATE MATERIALIZED VIEW mutations_by_codinsee as (SELECT nested.*, (nested.val/nested.transactions) as mean_val from (SELECT codinsee, COUNT(*) as transactions, SUM(valeurfonc) as val from mutations_all_view  GROUP BY codinsee ) as nested  where nested.val>0 and transactions>1 ORDER BY mean_val DESC);

SELECT DISTINCT mut.*, insee.nom_com from mutations_by_codinsee mut  join insee_postal_code insee on  insee.insee_com = mut.l_codinsee[1] order by transactions desc;

# FIND average price and surface sold for 2018 in saint cezaire sur siagne
SELECT * from mutations_parcel_view where codinsee=06118 group by 

CREATE MATERIALIZED VIEW mutations_parcel_06118 as (SELECT nested.*, (nested.val/nested.transactions) as mean_val from (SELECT anneemut, COUNT(*) as transactions, SUM(valeurfonc) as val from mutations_parcel_view  where codinsee='06118' GROUP BY anneemut ) as nested  where nested.val>0 and transactions>1 ORDER BY mean_val DESC);

# FIND sell of house + terrain
select distinct idmutation, libtypbien, l_dcnt, datemut, valeurfonc::integer, sterr, sbati, l_codinsee, insee.nom_com, ST_ASTEXT(ST_Transform(mut.geom, 4326))   from mutations_all_view mut join insee_postal_code insee on insee.insee_com=mut.l_codinsee[1] where sterr>10000  and coddep='06' ;

