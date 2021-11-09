package com.starling.roundup.DTO.Account;

import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {
    private List<Account> accounts;
}
