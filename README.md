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

# Geospatial
Download https://cerema.app.box.com/v/dvfplus-opendata/folder/93294544543

# Convert shp to sql files

docker exec  -i -t osmworker /scripts/shp_to_sql.sh

# Insert sql to postgres

docker exec  -i -t postgres /scripts/insert_data.shdocker exec  -i -t postgres /scripts/insert_data.sh

# Create views

# Create Nosql dumps via elasticsearch
