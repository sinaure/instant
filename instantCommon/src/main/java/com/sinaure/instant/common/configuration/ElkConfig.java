package com.sinaure.instant.common.configuration;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration

@ComponentScan({"com.sinaure.common.model", "com.sinaure.common.document"})
public class ElkConfig {
	static Logger log = Logger.getLogger(ElkConfig.class.getName());

	@Autowired
    private DBProperties dbProperties;
	@Configuration
	@EnableElasticsearchRepositories(basePackages = "com.egm.sictiam.sictiamElk.repository", elasticsearchTemplateRef = "elkCommon")
	public class ElkCommonConfig {}


	@Bean
	public Client client() throws NumberFormatException, UnknownHostException {
		log.info("configuring ES client for : "+dbProperties.getClustername()+" - "+dbProperties.getNodename());
		Settings elasticsearchSettings = Settings.builder()
		          .put("cluster.name", dbProperties.getClustername())
		          .put("node.name",dbProperties.getNodename() )
//		          .put("http.enabled", "true")
		          .put("client.transport.sniff", false)
		          .build();
		
		  TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
		        client.addTransportAddress(new TransportAddress(InetAddress.getByName(dbProperties.getHost() ), Integer.valueOf(dbProperties.getPort())));
		        return client;
		        
	}

	 @Bean(name="elkCommon")
	    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
	        return new ElasticsearchTemplate(client());
	    }
	 


}
