package com.blackrock.challenge.model;

import java.util.List;

public class TransactionValidationResult {

    private List<Transaction> validTransactions;
    private List<InvalidTransaction> invalidTransactions;

    public TransactionValidationResult() {
    }

    public TransactionValidationResult(List<Transaction> validTransactions,
                                       List<InvalidTransaction> invalidTransactions) {
        this.validTransactions = validTransactions;
        this.invalidTransactions = invalidTransactions;
    }

    public List<Transaction> getValidTransactions() {
        return validTransactions;
    }

    public void setValidTransactions(List<Transaction> validTransactions) {
        this.validTransactions = validTransactions;
    }

    public List<InvalidTransaction> getInvalidTransactions() {
        return invalidTransactions;
    }

    public void setInvalidTransactions(List<InvalidTransaction> invalidTransactions) {
        this.invalidTransactions = invalidTransactions;
    }
}
