package com.starling.roundup.service;

import com.starling.roundup.configuration.HeadersConfiguration;
import com.starling.roundup.DTO.Account.Account;
import com.starling.roundup.DTO.Account.AccountResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    private final RestTemplate restTemplate;
    private final HeadersConfiguration headersConfiguration;

    public AccountService(RestTemplate restTemplate, HeadersConfiguration headersConfiguration) {
        this.restTemplate = restTemplate;
        this.headersConfiguration = headersConfiguration;
    }

    public Account getAccountDetails() {
        final HttpEntity<AccountResponse> req = new HttpEntity<>(this.headersConfiguration.getHeaders());
        final AccountResponse resp = this.restTemplate
                .exchange("https://api-sandbox.starlingbank.com/api/v2/accounts",
                        HttpMethod.GET,
                        req,
                        new ParameterizedTypeReference<AccountResponse>() {})
                .getBody();
        assert resp != null;
        return resp.getAccounts().get(0);
    }

}
