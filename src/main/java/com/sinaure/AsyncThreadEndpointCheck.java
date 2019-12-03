package com.sinaure;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sinaure.config.model.InstantParking;
import com.sinaure.service.ParkingService;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncThreadEndpointCheck {
	static Logger log = Logger.getLogger(AsyncThreadEndpointCheck.class.getName());
	ExecutorService executor = Executors.newWorkStealingPool();
	
	@Autowired
	private GlobalProperties globalProperties;
	private ObjectWriter writer = new ObjectMapper().registerModule(new JtsModule()).writer().withDefaultPrettyPrinter();
	@Autowired
    private ParkingService parkingService;
	
	private static String cachedContent = "";
	
	//every minute check for api updates
	@Scheduled(initialDelay=20000, fixedRate = 60000)
	public void checkContent()  {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response  = restTemplate.getForEntity(globalProperties.getEndpoint(), String.class);
		if(!cachedContent.equalsIgnoreCase(response.getBody())) {
			cachedContent = response.getBody();
			List<InstantParking> instantParking= parkingService.extractParkingRecord(response.getBody());
			log.info("content updated!");
			try {
				log.info(writer.writeValueAsString(instantParking));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	@PreDestroy
	public void beandestroy() {
		log.info("destroyng executor");
		if (executor != null) {
			executor.shutdownNow();
		}
	}
	

}
