package org.olympics.competition.service.calculation;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.business.domain.Ranking;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AthleteRankingService implements RankingService<Athlete> {
    @Override
    public Ranking buildOrderedRanking(List<Athlete> athletes, Comparator<Athlete> comparator) {
        Collections.sort(athletes, comparator);
        athletes.stream().forEach((a -> a.setPlace(getAthletePosition(a, athletes))));
        return new Ranking(athletes);
    }


    protected String getAthletePosition(Athlete myAthlete, List<Athlete> athletes) {
        int firstOccurrence = athletes.indexOf(myAthlete) + 1;
        int lastOccurrence = athletes.lastIndexOf(myAthlete) + 1;

       return (lastOccurrence > firstOccurrence) ? firstOccurrence + "-" + lastOccurrence : String.valueOf(firstOccurrence);
    }


}
