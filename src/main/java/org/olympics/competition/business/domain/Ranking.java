package org.olympics.competition.business.domain;

import java.util.Comparator;
import java.util.Set;

public interface Ranking {
    Set<Athlete> sortAthletes(Comparator<Result> athleteComparator);
}
