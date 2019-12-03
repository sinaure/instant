package com.sinaure.config.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.vividsolutions.jts.geom.Point;
@Entity
@Table(name = "instantParking")
public class InstantParking {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Integer free;
	private Integer max;
	@Id
    @Column(name = "name", nullable = false)
	private String name;
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
