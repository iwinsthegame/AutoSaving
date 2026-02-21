package com.blackrock.challenge.service;

import com.blackrock.challenge.model.IndexFundReturn;
import com.blackrock.challenge.model.KPeriodResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexFundService {

    private static final double INDEX_FUND_RATE = 0.1449;

    /**
     * Calculates index fund returns for each k period result.
     */
    public List<IndexFundReturn> calculateReturns(List<KPeriodResult> kResults,
                                                   int age,
                                                   double inflationRate) {

        List<IndexFundReturn> returns = new ArrayList<>();

        if (kResults == null) {
            return returns;
        }

        int years = calculateYears(age);

        for (KPeriodResult k : kResults) {
            double invested = k.getAmount();

            double finalValue = invested *
                    Math.pow(1 + INDEX_FUND_RATE, years);

            double realValue = finalValue /
                    Math.pow(1 + inflationRate, years);

            returns.add(new IndexFundReturn(
                    k.getStart(),
                    k.getEnd(),
                    invested,
                    finalValue,
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
}
