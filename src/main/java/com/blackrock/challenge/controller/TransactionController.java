package com.blackrock.challenge.controller;

import com.blackrock.challenge.model.IndexFundReturn;
import com.blackrock.challenge.model.IndexFundReturnRequest;
import com.blackrock.challenge.model.KPeriodResult;
import com.blackrock.challenge.model.NpsReturn;
import com.blackrock.challenge.model.NpsReturnRequest;
import com.blackrock.challenge.model.Transaction;
import com.blackrock.challenge.model.TransactionFilterRequest;
import com.blackrock.challenge.model.TransactionFilterResponse;
import com.blackrock.challenge.service.IndexFundService;
import com.blackrock.challenge.service.NpsService;
import com.blackrock.challenge.service.TemporalRuleService;
import com.blackrock.challenge.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blackrock/challenge/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TemporalRuleService temporalRuleService;
    private final IndexFundService indexFundService;
    private final NpsService npsService;

    public TransactionController(TransactionService transactionService,
                                 TemporalRuleService temporalRuleService,
                                 IndexFundService indexFundService,
                                 NpsService npsService) {
        this.transactionService = transactionService;
        this.temporalRuleService = temporalRuleService;
        this.indexFundService = indexFundService;
        this.npsService = npsService;
    }

    @PostMapping("/parse")
    public List<Transaction> parseTransactions(@RequestBody List<com.blackrock.challenge.model.Expense> expenses) {
        return transactionService.parseExpenses(expenses);
    }

    /**
     * Applies q + p + k rules and returns grouped savings per k period.
     */
    @PostMapping("/filter")
    public TransactionFilterResponse filterTransactions(
            @RequestBody TransactionFilterRequest request) {

        List<Transaction> afterQ =
                temporalRuleService.applyQRules(
                        request.getTransactions(),
                        request.getQPeriods()
                );

        List<Transaction> afterP =
                temporalRuleService.applyPRules(
                        afterQ,
                        request.getPPeriods()
                );

        List<KPeriodResult> results =
                temporalRuleService.applyKRules(
                        afterP,
                        request.getKPeriods()
                );

        return new TransactionFilterResponse(results);
    }

    /**
     * Calculates Index Fund returns from k period results.
     */
    @PostMapping("/returns/index")
    public List<IndexFundReturn> calculateIndexFundReturns(
            @RequestBody IndexFundReturnRequest request) {

        return indexFundService.calculateReturns(
                request.getKResults(),
                request.getAge(),
                request.getInflation()
        );
    }

    /**
     * Calculates NPS returns from k period results.
     */
    @PostMapping("/returns/nps")
    public List<NpsReturn> calculateNpsReturns(
            @RequestBody NpsReturnRequest request) {

        return npsService.calculateReturns(
                request.getKResults(),
                request.getAge(),
                request.getAnnualIncome(),
                request.getInflation()
        );
    }
}
