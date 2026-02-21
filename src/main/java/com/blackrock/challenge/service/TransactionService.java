package com.blackrock.challenge.service;

import com.blackrock.challenge.model.Expense;
import com.blackrock.challenge.model.Transaction;
import com.blackrock.challenge.util.FinanceUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    /**
     * Converts a list of expenses into transactions by calculating
     * ceiling and remanent for each expense.
     */
    public List<Transaction> parseExpenses(List<Expense> expenses) {
        List<Transaction> transactions = new ArrayList<>();

        if (expenses == null) {
            return transactions;
        }

        for (Expense expense : expenses) {
            double ceiling = FinanceUtils.roundUpToNextHundred(expense.getAmount());
            double remanent = ceiling - expense.getAmount();

            Transaction transaction = new Transaction(
                    expense.getDate(),
                    expense.getAmount(),
                    ceiling,
                    remanent
            );

            transactions.add(transaction);
        }

        return transactions;
    }
}
