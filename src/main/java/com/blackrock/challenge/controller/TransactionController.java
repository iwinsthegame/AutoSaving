package com.blackrock.challenge.controller;

import com.blackrock.challenge.model.Expense;
import com.blackrock.challenge.model.Transaction;
import com.blackrock.challenge.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blackrock/challenge/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Parses expenses into transactions by calculating ceiling and remanent.
     */
    @PostMapping("/parse")
    public List<Transaction> parseTransactions(@RequestBody List<Expense> expenses) {
        return transactionService.parseExpenses(expenses);
    }
}
