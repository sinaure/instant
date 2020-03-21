package com.sinaure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;


/**
 * The persistent class for the mutations_by_codinsee database table.
 * 
 */
@MappedSuperclass
public  class MutationByYear  {
	@Id
	private Integer aneemut;

	@Column(name="mean_val")
	private Double meanVal;

	@Column(name="totval")
	private Double totVal;

	private Long transactions;

	public MutationByYear() {
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