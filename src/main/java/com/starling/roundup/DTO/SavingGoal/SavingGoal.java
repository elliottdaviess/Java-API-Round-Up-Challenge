package com.starling.roundup.DTO.SavingGoal;

import lombok.Data;

@Data
public class SavingGoal {
    private String savingsGoalUid;
    private String name;
    private Target target;
    private TotalSaved totalSaved;
    private int savedPercentage;

    @Data
    public static class Target{
        private String currency;
        private int minorUnits;
    }

    @Data
    public static class TotalSaved{
        private String currency;
        private int minorUnits;
    }
}
