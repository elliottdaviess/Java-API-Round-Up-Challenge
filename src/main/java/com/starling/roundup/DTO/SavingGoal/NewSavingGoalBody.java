package com.starling.roundup.DTO.SavingGoal;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Currency;

@NoArgsConstructor
@Getter
public class NewSavingGoalBody {
    private final String name = "Starling Saving Goal";
    private final String currency = String.valueOf(Currency.getInstance("GBP"));
//    public Object target = new Target();
    private final String base64EncodedPhoto = "string";

//    @Data
//    private static class Target{
//        public String currency = String.valueOf(Currency.getInstance("GBP"));
//        public int minorUnits = 0;
//    }
}
