package com.blackrock.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class IndexFundReturnRequest {

    @JsonProperty("kResults")
    private List<KPeriodResult> kResults;
    private int age;
    private double inflation;

    public IndexFundReturnRequest() {
    }

    public List<KPeriodResult> getKResults() {
        return kResults;
    }

    public void setKResults(List<KPeriodResult> kResults) {
        this.kResults = kResults;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }
}
