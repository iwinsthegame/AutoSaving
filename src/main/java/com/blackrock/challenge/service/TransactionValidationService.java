package com.blackrock.challenge.service;

import com.blackrock.challenge.model.InvalidTransaction;
import com.blackrock.challenge.model.Transaction;
import com.blackrock.challenge.model.TransactionValidationResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionValidationService {

    public TransactionValidationResult validate(List<Transaction> transactions) {

        List<Transaction> valid = new ArrayList<>();
        List<InvalidTransaction> invalid = new ArrayList<>();
        Set<LocalDateTime> seenDates = new HashSet<>();

        if (transactions == null) {
            return new TransactionValidationResult(valid, invalid);
        }

        for (Transaction transaction : transactions) {

            if (transaction.getAmount() <= 0) {
                invalid.add(new InvalidTransaction(
                        transaction,
                        "Amount must be greater than zero"
                ));
                continue;
            }

            if (seenDates.contains(transaction.getDate())) {
                invalid.add(new InvalidTransaction(
                        transaction,
                        "Duplicate transaction timestamp"
                ));
                continue;
            }

            seenDates.add(transaction.getDate());
            valid.add(transaction);
        }

        return new TransactionValidationResult(valid, invalid);
    }
}
