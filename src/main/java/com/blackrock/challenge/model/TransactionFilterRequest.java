package com.blackrock.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransactionFilterRequest {

    private List<Transaction> transactions;
    @JsonProperty("qPeriods")
    private List<QPeriod> qPeriods;
    @JsonProperty("pPeriods")
    private List<PPeriod> pPeriods;
    @JsonProperty("kPeriods")
    private List<KPeriod> kPeriods;

    public TransactionFilterRequest() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<QPeriod> getQPeriods() {
        return qPeriods;
    }

    public void setQPeriods(List<QPeriod> qPeriods) {
        this.qPeriods = qPeriods;
    }

    public List<PPeriod> getPPeriods() {
        return pPeriods;
    }

    public void setPPeriods(List<PPeriod> pPeriods) {
        this.pPeriods = pPeriods;
    }

    public List<KPeriod> getKPeriods() {
        return kPeriods;
    }

    public void setKPeriods(List<KPeriod> kPeriods) {
        this.kPeriods = kPeriods;
    }
}
