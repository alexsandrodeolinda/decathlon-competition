package org.olympics.competition.service.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.olympics.competition.business.domain.Athlete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class AthleteRankingServiceTest {
    private List<Athlete> athletes;

    @BeforeEach
    void setUp() {
        athletes = new ArrayList<>();
        Athlete smith = new Athlete("John Smith", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60, 2.60, 35.81, 325.72});
        smith.setTotalScore(4200);
        athletes.add(smith);
        Athlete lukeSky = new Athlete("Luke Sky", new double[]{13.04, 4.53, 7.79, 1.55, 64.72, 18.74, 24.20, 2.40, 28.20, 411.01});
        lukeSky.setTotalScore(7500);
        athletes.add(lukeSky);

    }

    @Test
    void buildOrderedRanking() {
        RankingService rankingService = new AthleteRankingService();
        List<Athlete> orderedList = rankingService.buildOrderedRanking(athletes, Comparator.comparing(Athlete::getTotalScore).reversed()).getAthleteList();
        Assertions.assertEquals("Luke Sky", orderedList.get(0).getName());


    }

    @Test
    void getAthletePosition() {
        RankingService rankingService = new AthleteRankingService();
        List<Athlete> orderedList = rankingService.buildOrderedRanking(athletes, Comparator.comparing(Athlete::getTotalScore).reversed()).getAthleteList();
        Assertions.assertEquals("Luke Sky", orderedList.get(0).getName());
        Assertions.assertEquals("1", orderedList.get(0).getPlace());

    }
}