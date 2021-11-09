package com.starling.roundup.service;

import com.starling.roundup.configuration.HeadersConfiguration;
import com.starling.roundup.DTO.Account.Account;
import com.starling.roundup.DTO.Transaction.TransactionFeedResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionFeedService {
    private final RestTemplate restTemplate;
    private final HeadersConfiguration headersConfiguration;

    public TransactionFeedService(RestTemplate restTemplate, HeadersConfiguration headersConfiguration) {
        this.restTemplate = restTemplate;
        this.headersConfiguration = headersConfiguration;
    }

    public TransactionFeedResponse getTransactions(Account account) {
        final Map<String, String> params = new HashMap<>();
        params.put("id", account.getAccountUid());
        params.put("cat", account.getDefaultCategory());
        params.put("date", account.getCreatedAt());
        final HttpEntity<TransactionFeedResponse> req = new HttpEntity<>(this.headersConfiguration.getHeaders());
        return this.restTemplate
                .exchange("https://api-sandbox.starlingbank.com/api/v2/feed/account/{id}/category/{cat}?changesSince={date}",
                    HttpMethod.GET,
                    req,
                    new ParameterizedTypeReference<TransactionFeedResponse>(){},
                    params)
                .getBody();
    }
}
