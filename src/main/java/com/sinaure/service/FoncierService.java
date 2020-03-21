package com.sinaure.service;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinaure.model.MutationsByCodinsee;
import com.sinaure.model.MutationsLocView;
import com.sinaure.model.MutationsParcelView;
import com.sinaure.repository.MutationsLocRepository;
import com.sinaure.repository.MutationsParcelRepository;
import com.sinaure.repository.StatsRepository;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

@Component
public class FoncierService {
	
	public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  
	private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

	
	@Autowired
    private MutationsParcelRepository mutationsParcelRepository;
	
	@Autowired
    private MutationsLocRepository mutationsLocRepository;
	
	@Autowired
    private StatsRepository statsRepository;

	public List<MutationsParcelView> getTerrainsParcelMutationsByCodinsee(String codinsee){
		return mutationsParcelRepository.findTerrainsByCodinsee(codinsee);
		
	}
	public List<MutationsLocView> getAptMutationsByCodinsee(String codinsee){
		return mutationsLocRepository.findAptByCodinsee(codinsee);
	}
	public List<MutationsByCodinsee> getStatsByCodinsee(String codinsee){
		return statsRepository.statsByCodinsee(codinsee);
	}
	
}
