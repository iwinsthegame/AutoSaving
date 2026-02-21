package com.blackrock.challenge.model;

import java.time.LocalDateTime;

public class NpsReturn {

    private LocalDateTime start;
    private LocalDateTime end;
    private double investedAmount;
    private double finalValue;
    private double taxBenefit;
    private double realValue;

    public NpsReturn() {
    }

    public NpsReturn(LocalDateTime start,
                     LocalDateTime end,
                     double investedAmount,
                     double finalValue,
                     double taxBenefit,
                     double realValue) {
        this.start = start;
        this.end = end;
        this.investedAmount = investedAmount;
        this.finalValue = finalValue;
        this.taxBenefit = taxBenefit;
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

    public double getTaxBenefit() {
        return taxBenefit;
    }

    public void setTaxBenefit(double taxBenefit) {
        this.taxBenefit = taxBenefit;
    }

    public double getRealValue() {
        return realValue;
    }

    public void setRealValue(double realValue) {
        this.realValue = realValue;
    }
}
