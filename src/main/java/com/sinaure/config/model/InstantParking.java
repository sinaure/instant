package com.sinaure.config.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.geom.Point;
@Entity
@Table(name = "instantParking")
public class InstantParking {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'")
	private Date observedAt;
	public Date getObservedAt() {
		return observedAt;
	}
	public void setObservedAt(Date observedAt) {
		this.observedAt = observedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Integer free;
	private Integer max;
	private String name;
	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    private Point location;
	public Integer getFree() {
		return free;
	}
	public void setFree(Integer free) {
		this.free = free;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
}
