package com.sinaure;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncThreadMeasuresProducer {
	static Logger log = Logger.getLogger(AsyncThreadMeasuresProducer.class.getName());
	ExecutorService executor = Executors.newWorkStealingPool();
	
	@Autowired
	private GlobalProperties globalProperties;
	
	private static String cachedContent = "";
	
	//every minute check for api updates
	@Scheduled(initialDelay=20000, fixedRate = 60000)
	public void checkContent()  {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response  = restTemplate.getForEntity(globalProperties.getEndpoint(), String.class);
		log.info(response.getBody());
		if(cachedContent!=response.getBody()) {
			cachedContent = response.getBody();
			log.info("content updated!");
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
