package com.blackrock.challenge.model;

public class InvalidTransaction {

    private Transaction transaction;
    private String reason;

    public InvalidTransaction() {
    }

    public InvalidTransaction(Transaction transaction, String reason) {
        this.transaction = transaction;
        this.reason = reason;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
