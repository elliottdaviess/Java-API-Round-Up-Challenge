package com.starling.roundup.DTO.Amount;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class Amount {
    private Currency currency;
    private BigDecimal minorUnits;
}