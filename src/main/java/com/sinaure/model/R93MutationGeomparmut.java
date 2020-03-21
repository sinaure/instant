package com.sinaure.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Polygon;


/**
 * The persistent class for the r93_mutation_geomparmut database table.
 * 
 */
@Entity
@Table(name="r93_mutation_geomparmut")
@NamedQuery(name="R93MutationGeomparmut.findAll", query="SELECT r FROM R93MutationGeomparmut r")
public class R93MutationGeomparmut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer gid;

	private Polygon geom;

	private double idmutation;

	public R93MutationGeomparmut() {
	}

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Polygon getGeom() {
		return this.geom;
	}

	public void setGeom(Polygon geom) {
		this.geom = geom;
	}

	public double getIdmutation() {
		return this.idmutation;
	}

	public void setIdmutation(double idmutation) {
		this.idmutation = idmutation;
	}

}