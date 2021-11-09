package com.starling.roundup.helper;

import com.starling.roundup.DTO.Transaction.TransactionFeedItem;
import com.starling.roundup.DTO.Transaction.TransactionFeedResponse;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class RoundUpUtil {

    public BigDecimal getRoundedAmount(final TransactionFeedResponse transactions) {
        // not handling accounts without any transactions
        BigDecimal total = BigDecimal.ZERO;
        for (TransactionFeedItem transaction : transactions.getFeedItems()) {
            if (transaction.getDirection().equals("OUT")){ // assumption on requirements to only include outgoing transactions
                BigDecimal rounded = this.calculateDifferenceToNearestPound(transaction.getAmount().getMinorUnits());
                total = total.add(rounded);
            }
        }
        return total;
    }

    private BigDecimal calculateDifferenceToNearestPound(final BigDecimal transactionAmount) {
        return transactionAmount.setScale(-2, RoundingMode.UP).subtract(transactionAmount);
    }
}