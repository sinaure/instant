package com.sinaure.instant.common.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties("db")
public class DBProperties {
	private String clustername;
	private String nodename;
	private String host;
	private String port;
	public String getClustername() {
		return clustername;
	}
	public void setClustername(String clustername) {
		this.clustername = clustername;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
