package com.blackrock.challenge.service;

import com.blackrock.challenge.model.KPeriod;
import com.blackrock.challenge.model.KPeriodResult;
import com.blackrock.challenge.model.PPeriod;
import com.blackrock.challenge.model.QPeriod;
import com.blackrock.challenge.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemporalRuleService {

    // q logic (unchanged)
    public List<Transaction> applyQRules(List<Transaction> transactions,
                                         List<QPeriod> qPeriods) {

        List<Transaction> result = new ArrayList<>();

        if (transactions == null || qPeriods == null) {
            return transactions;
        }

        for (Transaction transaction : transactions) {
            double updatedRemanent = transaction.getRemanent();
            LocalDateTime txnDate = transaction.getDate();

            QPeriod selectedQ = null;

            for (QPeriod q : qPeriods) {
                if (isWithin(txnDate, q.getStart(), q.getEnd())) {
                    if (selectedQ == null ||
                            q.getStart().isAfter(selectedQ.getStart())) {
                        selectedQ = q;
                    }
                }
            }

            if (selectedQ != null) {
                updatedRemanent = selectedQ.getFixed();
            }

            result.add(new Transaction(
                    transaction.getDate(),
                    transaction.getAmount(),
                    transaction.getCeiling(),
                    updatedRemanent
            ));
        }

        return result;
    }

    // p logic (unchanged)
    public List<Transaction> applyPRules(List<Transaction> transactions,
                                         List<PPeriod> pPeriods) {

        List<Transaction> result = new ArrayList<>();

        if (transactions == null || pPeriods == null) {
            return transactions;
        }

        for (Transaction transaction : transactions) {
            double updatedRemanent = transaction.getRemanent();
            LocalDateTime txnDate = transaction.getDate();

            for (PPeriod p : pPeriods) {
                if (isWithin(txnDate, p.getStart(), p.getEnd())) {
                    updatedRemanent += p.getExtra();
                }
            }

            result.add(new Transaction(
                    transaction.getDate(),
                    transaction.getAmount(),
                    transaction.getCeiling(),
                    updatedRemanent
            ));
        }

        return result;
    }

    /**
     * Applies k-period grouping.
     * Groups transactions and sums remanent per k window.
     */
    public List<KPeriodResult> applyKRules(List<Transaction> transactions,
                                           List<KPeriod> kPeriods) {

        List<KPeriodResult> results = new ArrayList<>();

        if (transactions == null || kPeriods == null) {
            return results;
        }

        for (KPeriod k : kPeriods) {
            double sum = 0.0;

            for (Transaction transaction : transactions) {
                if (isWithin(transaction.getDate(), k.getStart(), k.getEnd())) {
                    sum += transaction.getRemanent();
                }
            }

            results.add(new KPeriodResult(
                    k.getStart(),
                    k.getEnd(),
                    sum
            ));
        }

        return results;
    }

    private boolean isWithin(LocalDateTime date,
                             LocalDateTime start,
                             LocalDateTime end) {
        return (date.isEqual(start) || date.isAfter(start))
                && (date.isEqual(end) || date.isBefore(end));
    }
}
