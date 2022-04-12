package org.olympics.competition.service.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculationServiceTest {

    @Test
    void getScoreForFirstDay() {
        //double[] performances = new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72};
        double[] performances = new double[]{12.61, 5.00, 9.22, 1.50, 60.39};

        ScoreCalculation scoreCalculation = new ScoreCalculationService();
        int totalScore = scoreCalculation.getTotalScore(performances);
        Assertions.assertEquals(2146, totalScore);
    }

    @Test
    void getTotalScore() {
        double[] performances = new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72};
        ScoreCalculation scoreCalculation = new ScoreCalculationService();
        int totalScore = scoreCalculation.getTotalScore(performances);
        Assertions.assertEquals(4200, totalScore);
    }
}