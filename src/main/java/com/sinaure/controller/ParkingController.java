package com.sinaure.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinaure.config.InstantParking;
import com.sinaure.service.ParkingService;

@RestController
@RequestMapping("parking")
public class ParkingController {
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
	
	@Autowired
	private ParkingService parkingService;

	
	@GetMapping("/instant/{parkingName}")
	public ResponseEntity<InstantParking> getInstantParking( @PathVariable String parkingName) {
		return new ResponseEntity<InstantParking>(parkingService.getLastInstantParkingByName(parkingName), HttpStatus.OK);
	}
	@GetMapping("/instant/{parkingName}/temporal")
	public ResponseEntity<List<InstantParking>> getInstantParkingInterval( @PathVariable String parkingName, @RequestParam("start") String start, @RequestParam("end") String end) {
	    try {
			Timestamp s = new java.sql.Timestamp(df.parse(start).getTime());
			Timestamp e = new java.sql.Timestamp(df.parse(end).getTime());
			return new ResponseEntity<List<InstantParking>>(parkingService.getInstantParkingByName(parkingName,s,e), HttpStatus.OK);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<List<InstantParking>>(new ArrayList<InstantParking>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
