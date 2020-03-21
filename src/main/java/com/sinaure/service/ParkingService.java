package com.sinaure.service;

import java.io.StringReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinaure.config.model.InstantParking;
import com.sinaure.repository.InstantParkingRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;

@Component
public class ParkingService {
	
	public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  
	private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

	
	@Autowired
    private InstantParkingRepository instantParkingRepository;

	
	public List<InstantParking> extractParkingRecord(String object) {
		JsonReader jsonReader = Json.createReader(new StringReader(object));
		JsonObject obj = jsonReader.readObject();
	    JsonArray array = obj.getJsonArray("records");
	    List<InstantParking> out = new ArrayList<InstantParking>();
	    for (javax.json.JsonValue record : array) {
	    	InstantParking p = new InstantParking();
	    	if(record.asJsonObject().getJsonObject("fields").containsKey("max") && record.asJsonObject().getJsonObject("fields").containsKey("free")) {
	    		p.setMax(record.asJsonObject().getJsonObject("fields").getInt("max"));
		    	p.setFree(record.asJsonObject().getJsonObject("fields").getInt("free"));
	    	}
	    	p.setName(record.asJsonObject().getJsonObject("fields").getString("key").toLowerCase());
	    	double lat = Double.parseDouble(record.asJsonObject().getJsonObject("geometry").getJsonArray("coordinates").get(0).toString());
            double lon = Double.parseDouble(record.asJsonObject().getJsonObject("geometry").getJsonArray("coordinates").get(1).toString());
            Point point = geometryFactory.createPoint(new Coordinate(lat, lon));
	    	p.setLocation(point);
	    	Date o = new Date(System.currentTimeMillis());
	    	p.setObservedAt(o);
	    	out.add(p);
	    }
	    instantParkingRepository.saveAll(out);
	    return out;
	}
	
	public InstantParking getLastInstantParkingByName(String name){
		List <InstantParking> parkings = instantParkingRepository.findByName(name);
		parkings = parkings.stream().sorted(Comparator.comparing(InstantParking::getObservedAt)).collect(Collectors.toList());
		return parkings.stream().findFirst().orElse(null);
	}
	
	public List<InstantParking> getInstantParkingByName(String name, Timestamp start, Timestamp end){
		List <InstantParking> parkings = instantParkingRepository.findByNameBetween(name, start, end);
		return parkings.stream().sorted(Comparator.comparing(InstantParking::getObservedAt)).collect(Collectors.toList());
	}
	
}
