package com.blackrock.challenge.model;

import java.time.LocalDateTime;

public class IndexFundReturn {

    private LocalDateTime start;
    private LocalDateTime end;
    private double investedAmount;
    private double finalValue;
    private double realValue;

    public IndexFundReturn() {
    }

    public IndexFundReturn(LocalDateTime start,
                           LocalDateTime end,
                           double investedAmount,
                           double finalValue,
                           double realValue) {
        this.start = start;
        this.end = end;
        this.investedAmount = investedAmount;
        this.finalValue = finalValue;
        this.realValue = realValue;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }

    public double getRealValue() {
        return realValue;
    }

    public void setRealValue(double realValue) {
        this.realValue = realValue;
    }
}
