package com.blackrock.challenge.model;

import java.util.List;

public class TransactionFilterResponse {

    private List<KPeriodResult> results;

    public TransactionFilterResponse() {
    }

    public TransactionFilterResponse(List<KPeriodResult> results) {
        this.results = results;
    }

    public List<KPeriodResult> getResults() {
        return results;
    }

    public void setResults(List<KPeriodResult> results) {
        this.results = results;
    }
}
