package com.starling.roundup.controller;

import com.starling.roundup.DTO.SavingGoal.NewSavingGoalResponse;
import com.starling.roundup.service.SavingsGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoundUpController {

    private final SavingsGoalService savingsGoalService;

    @Autowired
    public RoundUpController(SavingsGoalService savingsGoalService) {
        this.savingsGoalService = savingsGoalService;
    }

    @GetMapping(value = "/round-up")
    public ResponseEntity<String> roundUp() {
        NewSavingGoalResponse newSavingGoal = this .savingsGoalService.performRoundUp();
        return ResponseEntity.ok("Success: " + newSavingGoal.isSuccess() );
    }
}
