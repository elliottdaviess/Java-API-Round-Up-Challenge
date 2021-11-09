package com.starling.roundup.DTO.SavingGoal;

import lombok.Data;

import java.util.List;

@Data
public class SavingGoalsResponse {
    private List<SavingGoal> savingGoals;
}
