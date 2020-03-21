package com.sinaure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the mutations_by_codinsee database table.
 * 
 */
@Entity
@Table(name="mutations_by_codinsee")
public class MutationsByCodinsee  {
	@Id
	private String codinsee;

	@Column(name="mean_val")
	private Double meanVal;

	@Column(name="val")
	private Double totVal;

	private Long transactions;

	public MutationsByCodinsee() {
	}

	public String getCodinsee() {
		return this.codinsee;
	}

	public void setCodinsee(String codinsee) {
		this.codinsee = codinsee;
	}

	public Double getMeanVal() {
		return this.meanVal;
	}

	public void setMeanVal(Double meanVal) {
		this.meanVal = meanVal;
	}

	public Double getTotVal() {
		return this.totVal;
	}

	public void setTotVal(Double totVal) {
		this.totVal = totVal;
	}

	public Long getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Long transactions) {
		this.transactions = transactions;
	}

}