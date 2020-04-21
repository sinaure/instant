package com.sinaure;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sinaure.config.InstantParking;
import com.sinaure.service.ParkingService;
import com.vividsolutions.jts.geom.Point;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncThreadEndpointCheck {
	static Logger log = Logger.getLogger(AsyncThreadEndpointCheck.class.getName());
	ExecutorService executor = Executors.newWorkStealingPool();
	@Autowired
	private GlobalProperties globalProperties;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
	private static final ObjectMapper objectMapper = new ObjectMapper().setDateFormat(df);;
	private ObjectWriter writer = objectMapper.registerModule(new JtsModule()).writer().withDefaultPrettyPrinter();
	
	public static Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
				@Override
				public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
					return new JsonPrimitive(formatter.format(src));
				}
			}).registerTypeAdapter(Point.class, new JsonSerializer<Point>() {
				@Override
				public JsonElement serialize(Point src, Type typeOfSrc, JsonSerializationContext context) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("coordinates", src.getCoordinates());
					map.put("SRID", src.getSRID());
					String json = "{}";
					try {
						json = objectMapper.writeValueAsString(map);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return new JsonPrimitive(json);
				}
			}).setPrettyPrinting().serializeSpecialFloatingPointValues().create();
	@Autowired
	private ParkingService parkingService;
	@Autowired
	@Qualifier("rabbitTemplate")
	private RabbitTemplate rabbitTemplate;

	private static String cachedContent = "";

	// every minute check for api updates
	@Scheduled(initialDelay = 5000, fixedRate = 60000)
	public void checkContent() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(globalProperties.getEndpoint(), String.class);
		if (!cachedContent.equalsIgnoreCase(response.getBody())) {
			cachedContent = response.getBody();
			List<InstantParking> instantParking = parkingService.extractParkingRecord(response.getBody());
			log.info("content updated!");
			try {
				String json = writer.writeValueAsString(instantParking);
				log.info(json);
				rabbitTemplate.send("main_exchange", "event", MessageBuilder.withBody(json.getBytes("UTF-8")).build());
			} catch (AmqpException | JsonProcessingException | UnsupportedEncodingException e) {
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
