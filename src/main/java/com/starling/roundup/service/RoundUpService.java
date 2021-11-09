package com.starling.roundup.service;

import com.starling.roundup.helper.RoundUpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
/**
 * Redundant in the round-up process. Purely for manual checking
 */
@Service
public class RoundUpService {

    private final RoundUpUtil roundUpUtil;
    private final TransactionFeedService transactionFeedService;
    private final AccountService accountService;

    @Autowired
    public RoundUpService(RoundUpUtil roundUpUtil, TransactionFeedService transactionFeedService, AccountService accountService) {
        this.roundUpUtil = roundUpUtil;
        this.transactionFeedService = transactionFeedService;
        this.accountService = accountService;
    }

    public BigDecimal roundUp() {
        return this.roundUpUtil.getRoundedAmount(this.transactionFeedService.getTransactions(this.accountService.getAccountDetails()));
    }

}
