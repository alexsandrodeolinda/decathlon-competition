package org.olympics.competition.service.calculation;

import org.olympics.competition.business.domain.DecathlonEvent;

public class ScoreCalculationService implements ScoreCalculation {
    @Override
    public int getTotalScore(double[] performances) {
        int score = 0;
        for (int i = 0; i < performances.length; i++) {
            if (performances[i] > 0) {
                score += DecathlonEvent.values()[i].calculateScore(performances[i]);
            }

        }
        return score;
    }
}
