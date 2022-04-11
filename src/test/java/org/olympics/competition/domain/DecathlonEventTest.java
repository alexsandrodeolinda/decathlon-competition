package org.olympics.competition.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.olympics.competition.business.domain.CombinedEvent;
import org.olympics.competition.business.domain.DecathlonEvent;

class DecathlonEventTest {

    @Test
    void calculateScore_WhenEventIsSprint100m() {
        CombinedEvent event = DecathlonEvent.SPRINT_100M;
        int score = event.calculateScore(12.61);
        Assertions.assertEquals(536, score);

    }

    @Test
    void calculateScore_WhenEventIsJongJump() {
        CombinedEvent event = DecathlonEvent.LONG_JUMP;
        int score = event.calculateScore(5.00);
        Assertions.assertEquals(382, score);

    }

    @Test
    void calculateScore_WhenEventIsShotPut() {
        CombinedEvent event = DecathlonEvent.SHOT_PUT;
        int score = event.calculateScore(9.22);
        Assertions.assertEquals(439, score);

    }

    @Test
    void calculateScore_WhenEventIsHighJump() {
        CombinedEvent event = DecathlonEvent.HIGH_JUMP;
        int score = event.calculateScore(1.50);
        Assertions.assertEquals(389, score);

    }

    @Test
    void calculateScore_WhenEventIsSprint400() {
        CombinedEvent event = DecathlonEvent.SPRINT_400M;
        int score = event.calculateScore(60.39);
        Assertions.assertEquals(400, score);

    }

    @Test
    void calculateScore_WhenEventIsHurdles110() {
        CombinedEvent event = DecathlonEvent.HURDLES_110M;
        int score = event.calculateScore(16.43);
        Assertions.assertEquals(685, score);

    }

    @Test
    void calculateScore_WhenEventIsDiscusThrow() {
        CombinedEvent event = DecathlonEvent.DISCUS_THROW;
        int score = event.calculateScore(21.60);
        Assertions.assertEquals(302, score);

    }

    @Test
    void calculateScore_WhenEventIsPoleVault() {
        CombinedEvent event = DecathlonEvent.POLE_VAULT;
        int score = event.calculateScore(2.60);
        Assertions.assertEquals(264, score);

    }

    @Test
    void calculateScore_WhenEventIsJavelinThrow() {
        CombinedEvent event = DecathlonEvent.JAVELIN_THROW;
        int score = event.calculateScore(35.81);
        Assertions.assertEquals(382, score);

    }

    @Test
    void calculateScore_WhenEventIsRun1500() {
        CombinedEvent event = DecathlonEvent.RUN_1500M;
        int score = event.calculateScore(325.72); //5m:25s.72ss -> 5m = 300s + 25s + 0.72s = 325.72
        Assertions.assertEquals(421, score);

    }

    }