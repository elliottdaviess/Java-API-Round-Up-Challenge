package com.starling.roundup.DTO.Account;

import lombok.Data;

/*
 Does this need annotation to ignore unmapped properties?
 e.g. @JsonIgnoreProperties(ignoreUnknown = true) https://spring.io/guides/gs/consuming-rest/
*/

@Data
public class Account {
    private String accountUid;
    private String accountType;
    private String defaultCategory;
    private String currency;
    private String createdAt;
    private String name;
}
