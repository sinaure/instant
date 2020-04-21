package com.sinaure.model;

import java.math.BigDecimal;

public class MutationsByYear {
    private  Integer aneemut;
    private BigDecimal transactions;
    private Double totval;

    public BigDecimal getTransactions() {
        return transactions;
    }

    public void setTransactions(BigDecimal transactions) {
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

    public Integer getAneemut() {
        return aneemut;
    }

    public void setAneemut(Integer aneemut) {
        this.aneemut = aneemut;
    }
}
