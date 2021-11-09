package com.starling.roundup.DTO.Transaction;

import lombok.Data;

import java.util.List;

@Data
public class TransactionFeedResponse {
    private List<TransactionFeedItem> feedItems;
}
