package org.olympics.competition.business.domain;

public class Result {
    private Athlete athlete;
    private int totalScore;
    private int place;

    public Result(Athlete athlete, int totalScore) {
        this.athlete = athlete;
        this.totalScore = totalScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
