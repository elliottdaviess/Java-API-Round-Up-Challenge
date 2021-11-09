package com.starling.roundup.controller;

import com.starling.roundup.DTO.Account.Account;
import com.starling.roundup.DTO.Transaction.TransactionFeedResponse;
import com.starling.roundup.service.AccountService;
import com.starling.roundup.service.RoundUpService;
import com.starling.roundup.service.SavingsGoalService;
import com.starling.roundup.service.TransactionFeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/manual-test")
public class ManualTestController {

    private final AccountService accountService;
    private final TransactionFeedService transactionFeedService;
    private final RoundUpService roundUpService;
    private final SavingsGoalService savingsGoalService;

    public ManualTestController(AccountService accountService, TransactionFeedService transactionFeedService, RoundUpService roundUpService, SavingsGoalService savingsGoalService) {
        this.accountService = accountService;
        this.transactionFeedService = transactionFeedService;
        this.roundUpService = roundUpService;
        this.savingsGoalService = savingsGoalService;
    }

    @GetMapping(value = "/id")
    public ResponseEntity<Account> getAccountUid() {
        Account account = this.accountService.getAccountDetails();
        return ResponseEntity.ok(account);
    }

    @GetMapping(value = "/feed")
    public ResponseEntity<TransactionFeedResponse> getTransactionFeed() {
        Account account = this.accountService.getAccountDetails();
        TransactionFeedResponse feed = this.transactionFeedService.getTransactions(account);
        return ResponseEntity.ok(feed);
    }

    @GetMapping(value = "/rounded-amount")
    public ResponseEntity<BigDecimal> getRoundedAmount() {
        BigDecimal rounded = this.roundUpService.roundUp();
        return ResponseEntity.ok(rounded);
    }

    @GetMapping(value = "/create-goal")
    public ResponseEntity<String> createGoal() {
        Account account = this.accountService.getAccountDetails();
        String id = this.savingsGoalService.createSavingsGoal(account.getAccountUid());
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "/putSavingGoal")
    public ResponseEntity<String> putSavingGoal() {
        String savingsGoalUUID = "{insertID}";
        BigDecimal dec = BigDecimal.valueOf(4.8);
        Account account = this.accountService.getAccountDetails();
        this.savingsGoalService.transferRoundedAmount(savingsGoalUUID, account.getAccountUid(), dec);
        return ResponseEntity.ok("Done");
    }

}