package org.olympics.competition.business.domain;

public interface CombinedEvent {
    int calculateScore(double performance);
    int calculateTrackEventScore(double performance);
    int calculateFieldEventScore(double performance);
    int calculateJumpingEventScore(double performance);
    String getName();
}
