package com.sinaure.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Point;


/**
 * The persistent class for the geomlocmut database table.
 * 
 */
@Entity
@NamedQuery(name="Geomlocmut.findAll", query="SELECT g FROM Geomlocmut g")
public class Geomlocmut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer gid;
	@Column(columnDefinition="geometry(Point,4326)")
	private Point geom;

	private Double idmutation;

	public Geomlocmut() {
	}

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Point getGeom() {
		return this.geom;
	}

	public void setGeom(Point geom) {
		this.geom = geom;
	}

	public Double getIdmutation() {
		return this.idmutation;
	}

	public void setIdmutation(Double idmutation) {
		this.idmutation = idmutation;
	}

}