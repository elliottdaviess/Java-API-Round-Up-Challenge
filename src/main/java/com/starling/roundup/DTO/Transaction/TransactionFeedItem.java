package com.starling.roundup.DTO.Transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionFeedItem{
    private String feedItemUid;
    private String categoryUid;
    private Amount amount;
    private SourceAmount sourceAmount;
    private String direction;
    private Date updatedAt;
    private Date transactionTime;
    private Date settlementTime;
    private String source;
    private String status;
    private String transactingApplicationUserUid;
    private String counterPartyType;
    private String counterPartyUid;
    private String counterPartyName;
    private String counterPartySubEntityUid;
    private String counterPartySubEntityName;
    private String counterPartySubEntityIdentifier;
    private String counterPartySubEntitySubIdentifier;
    private String reference;
    private String country;
    private String spendingCategory;
    private boolean hasAttachment;
    private boolean hasReceipt;

    @Data
    public static class Amount{
        private String currency;
        private BigDecimal minorUnits;
    }

    @Data
    public static class SourceAmount{
        private String currency;
        private BigDecimal minorUnits;
    }
}


