#!/bin/sh
echo "*********adding measure ext index********"
curl -X PUT "elasticsearch-iot:9200/measure-ext"  -H 'Content-Type: application/json' -d'
{}
'
echo "*********adding measure ext mapping********"
curl -X PUT "elasticsearch-iot:9200/measure-ext/_mapping/measure-ext-type" -H 'Content-Type: application/json' -d'
{
  "properties": {
  	"measure" : {
  		"type": "nested",
  		"properties" : {
  			"id" : { "type" : "text" }, 
		    "value" : { "type" : "long" }, 
			"unit" : { "type" : "text" }, 
		    "point" : { "type" : "geo_point" }
  		}
  		
  	},
  	"properties" : {
  		"type": "nested",
  		"properties" : {
  			"unit" : { "type" : "text" },
	  		"id" :  { "type" : "text" }, 
	  		"quantityKind" :  { "type" : "text" }, 
	  		"point" : { "type" : "geo_point" }
  		}
  	}
    
  }
}
'
echo "*********list existing indexes********"
curl 'elasticsearch-iot:9200/_cat/indices?v'
echo "*********list existing mappings********"
curl -X GET "elasticsearch-iot:9200/_all/_mapping"


