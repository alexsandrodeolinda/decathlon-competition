package org.olympics.competition.business.domain;

import java.util.Arrays;
import java.util.Objects;

public class Athlete {
    private String name;
    private double[] performances;

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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(name, athlete.name) && Arrays.equals(performances, athlete.performances);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(performances);
        return result;
    }
}
