package org.olympics.competition.business.domain;

import java.util.*;

public class Ranking {
    private List<Athlete> athleteList;

    public Ranking(List<Athlete> athleteList) {
        this.athleteList = athleteList;
    }

    public List<Athlete> getAthleteList() {
        return athleteList;
    }
}
