package org.olympics.competition.business.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void testEquals() {
        Athlete john = new Athlete("John Smith", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72});
        Athlete luke = new Athlete("Luke Sky", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72});
        john.setTotalScore(4200);
        luke.setTotalScore(4200);
        Assertions.assertTrue(john.equals(luke));
    }

    @Test
    void testHashCode() {
        Athlete john = new Athlete("John Smith", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72});
        Athlete luke = new Athlete("Luke Sky", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72});
        john.setTotalScore(4200);
        luke.setTotalScore(4200);
        Assertions.assertTrue(john.hashCode() == luke.hashCode());
    }
}