package org.olympics.competition.business.domain;

import java.util.Arrays;
import java.util.Objects;

public class Athlete {
    private String name;
    private double[] performances;
    private int totalScore;
    private String place;

    public Athlete() {
    }

    public Athlete(String name, double[] performances) {
        this.name = name;
        this.performances = performances;
    }

    public String getName() {
        return name;
    }

    public double[] getPerformances() {
        return performances;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "name='" + name + '\'' +
                ", performances=" + Arrays.toString(performances) +
                ", totalScore=" + totalScore +
                ", place=" + place +
                '}';
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return totalScore == athlete.totalScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalScore);
    }
}
