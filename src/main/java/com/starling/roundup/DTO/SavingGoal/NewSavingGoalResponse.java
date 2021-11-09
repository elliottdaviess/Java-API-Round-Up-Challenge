package com.starling.roundup.DTO.SavingGoal;

import lombok.Data;

import java.util.List;

@Data
public class NewSavingGoalResponse {
    private String savingsGoalUid;
    private boolean success = false;
    private List<Object> errors;
}
