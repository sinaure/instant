package com.sinaure.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinaure.model.MutationsByCodinsee;
import com.sinaure.model.MutationsLocView;
import com.sinaure.model.MutationsParcelView;
import com.sinaure.service.FoncierService;

@RestController
@RequestMapping("foncier")
public class FoncierController {
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
	
	@Autowired
	private FoncierService foncierService;

	
	@GetMapping("/stats/parcel/{codinsee}")
	public ResponseEntity<List<MutationsParcelView>> getParcel( @PathVariable String codinsee) {
		return new ResponseEntity<List<MutationsParcelView>>(foncierService.getTerrainsParcelMutationsByCodinsee(codinsee), HttpStatus.OK);
	}
	
	@GetMapping("/stats/loc/{codinsee}")
	public ResponseEntity<List<MutationsLocView>> getLoc( @PathVariable String codinsee) {
		return new ResponseEntity<List<MutationsLocView>>(foncierService.getAptMutationsByCodinsee(codinsee), HttpStatus.OK);
	}
	
	@GetMapping("/stats/{codinsee}")
	public ResponseEntity<List<MutationsByCodinsee>> getStatsRepository( @PathVariable String codinsee) {
		return new ResponseEntity<List<MutationsByCodinsee>>(foncierService.getStatsByCodinsee(codinsee), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public String getHome( ) {
		return "OK";
	}

}
