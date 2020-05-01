package com.sinaure.instant.common.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mutations_parcel_06")
public class MutationsParcel06 {

    public MutationsByYearId getMutationsByYearId() {
        return mutationsByYearId;
    }

    public void setMutationsByYearId(MutationsByYearId mutationsByYearId) {
        this.mutationsByYearId = mutationsByYearId;
    }

    @EmbeddedId
    private MutationsByYearId mutationsByYearId;


    private Long transactions;
    private Double totval;



    public Long getTransactions() {
        return transactions;
    }

    public void setTransactions(Long transactions) {
        this.transactions = transactions;
    }

    public Double getTotval() {
        return totval;
    }

    public void setTotval(Double totval) {
        this.totval = totval;
    }

    public Double getMean_val() {
        return mean_val;
    }

    public void setMean_val(Double mean_val) {
        this.mean_val = mean_val;
    }

    private Double mean_val;

}
