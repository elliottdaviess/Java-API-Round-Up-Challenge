package com.starling.roundup.service;

import com.starling.roundup.DTO.Account.Account;
import com.starling.roundup.DTO.Amount.Amount;
import com.starling.roundup.DTO.Amount.AmountResponse;
import com.starling.roundup.DTO.SavingGoal.NewSavingGoalBody;
import com.starling.roundup.DTO.SavingGoal.NewSavingGoalResponse;
import com.starling.roundup.DTO.Transaction.TransactionFeedResponse;
import com.starling.roundup.configuration.HeadersConfiguration;
import com.starling.roundup.helper.RoundUpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SavingsGoalService {

    private final RestTemplate restTemplate;
    private final HeadersConfiguration headersConfiguration;
    private final AccountService accountService;
    private final TransactionFeedService transactionFeedService;
    private final RoundUpUtil roundUpUtil;

    @Autowired
    public SavingsGoalService(RestTemplate restTemplate, HeadersConfiguration headersConfiguration, AccountService accountService, TransactionFeedService transactionFeedService, RoundUpUtil roundUpUtil) {
        this.restTemplate = restTemplate;
        this.headersConfiguration = headersConfiguration;
        this.accountService = accountService;
        this.transactionFeedService = transactionFeedService;
        this.roundUpUtil = roundUpUtil;
    }

    public NewSavingGoalResponse performRoundUp() {
        NewSavingGoalResponse defaultResp = new NewSavingGoalResponse();
        try{
            final Account account = this.accountService.getAccountDetails();
            final TransactionFeedResponse transactions = this.transactionFeedService.getTransactions(account);
            final BigDecimal roundedTotal = this.roundUpUtil.getRoundedAmount(transactions);
            final String UUID = this.createSavingsGoal(account.getAccountUid());
            return this.transferRoundedAmount(UUID, account.getAccountUid(), roundedTotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultResp;
    }

    public NewSavingGoalResponse transferRoundedAmount(final String savingsGoalUUID, final String accountUid, final BigDecimal roundedUpValue) {
        final Map<String, String> params = new HashMap<>();
        final String uuid = UUID.randomUUID().toString();
        params.put("accountId", accountUid);
        params.put("goalId", savingsGoalUUID);
        params.put("transferId", uuid);

        final HttpEntity<AmountResponse> req = this.getAmountHttpEntity(roundedUpValue);
        return this.restTemplate
                .exchange("https://api-sandbox.starlingbank.com/api/v2/account/{accountId}/savings-goals/{goalId}/add-money/{transferId}",
                        HttpMethod.PUT,
                        req,
                        new ParameterizedTypeReference<NewSavingGoalResponse>() {
                        },
                        params)
                .getBody();
    }

    public String createSavingsGoal(String accountUid) {
        final Map<String, String> params = new HashMap<>();
        params.put("id", accountUid);

        final HttpEntity<NewSavingGoalBody> req = this.getSavingGoalHttpEntity();
        final NewSavingGoalResponse resp = this.restTemplate
                .exchange(
                    "https://api-sandbox.starlingbank.com/api/v2/account/{id}/savings-goals",
                    HttpMethod.PUT,
                    req,
                    new ParameterizedTypeReference<NewSavingGoalResponse>() {},
                    params)
                .getBody();
        assert resp != null;
        return resp.getSavingsGoalUid();
    }

    private HttpEntity<NewSavingGoalBody> getSavingGoalHttpEntity() {
        final HttpHeaders headers = this.headersConfiguration.getHeaders();
        final NewSavingGoalBody body = new NewSavingGoalBody();
        return new HttpEntity<>(body, headers);
    }

    private HttpEntity<AmountResponse> getAmountHttpEntity(final BigDecimal roundedUpValue) {
        final HttpHeaders headers = this.headersConfiguration.getHeaders();
        final AmountResponse body = new AmountResponse();
        final Amount amount = new Amount();
        amount.setMinorUnits(roundedUpValue);
        amount.setCurrency(Currency.getInstance("GBP"));
        body.setAmount(amount);
        return new HttpEntity<>(body, headers);
    }
}
