package com.blackrock.challenge.service;

import com.blackrock.challenge.model.KPeriodResult;
import com.blackrock.challenge.model.NpsReturn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NpsService {

    private static final double NPS_RATE = 0.0711;
    private static final double MAX_NPS_DEDUCTION = 200000.0;

    public List<NpsReturn> calculateReturns(List<KPeriodResult> kResults,
                                            int age,
                                            double annualIncome,
                                            double inflationRate) {

        List<NpsReturn> returns = new ArrayList<>();

        if (kResults == null) {
            return returns;
        }

        int years = calculateYears(age);

        for (KPeriodResult k : kResults) {
            double invested = k.getAmount();

            double finalValue =
                    invested * Math.pow(1 + NPS_RATE, years);

            double deduction = calculateNpsDeduction(invested, annualIncome);
            double taxBenefit =
                    calculateTax(annualIncome) -
                    calculateTax(annualIncome - deduction);

            double realValue =
                    finalValue / Math.pow(1 + inflationRate, years);

            returns.add(new NpsReturn(
                    k.getStart(),
                    k.getEnd(),
                    invested,
                    finalValue,
                    taxBenefit,
                    realValue
            ));
        }

        return returns;
    }

    private int calculateYears(int age) {
        if (age >= 60) {
            return 5;
        }
        return 60 - age;
    }

    private double calculateNpsDeduction(double invested,
                                         double annualIncome) {

        double tenPercentIncome = annualIncome * 0.10;

        return Math.min(
                invested,
                Math.min(tenPercentIncome, MAX_NPS_DEDUCTION)
        );
    }

    /**
     * Simplified tax calculation as per PDF slabs.
     */
    private double calculateTax(double income) {
        double tax = 0.0;

        if (income <= 700000) {
            return 0.0;
        }

        if (income > 700000) {
            tax += Math.min(income - 700000, 300000) * 0.10;
        }

        if (income > 1000000) {
            tax += Math.min(income - 1000000, 200000) * 0.15;
        }

        if (income > 1200000) {
            tax += Math.min(income - 1200000, 300000) * 0.20;
        }

        if (income > 1500000) {
            tax += (income - 1500000) * 0.30;
        }

        return tax;
    }
}
