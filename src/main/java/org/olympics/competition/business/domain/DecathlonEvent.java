package org.olympics.competition.business.domain;

import java.util.Objects;

/**
 * Here I'm using an Enum to implement Strategy Pattern in Java.
 * Could also be achieved by using an interface or/and abstract classes which would be implemented by each strategy.
 * Chose Enum for simplicity and avoid using 'if' statements in client classes, since the client must be aware of the differences between strategies in order to make the right choice.
 * A, B and C values taken from https://en.wikipedia.org/wiki/Decathlon#Men's_decathlon
 */
public enum DecathlonEvent implements CombinedEvent {
    SPRINT_100M(0, "100-metre dash", 25.4_347, 18, 1.81) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateTrackEventScore(performance);
        }
    },
    LONG_JUMP(1, "Long Jump", 0.14_354,	220,	1.4) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateJumpingEventScore(performance);
        }
    },
    SHOT_PUT(2, "Shot Put", 51.39, 1.5, 1.05) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateFieldEventScore(performance);
        }
    },
    HIGH_JUMP(3, "High Jump", 0.8_465, 75, 1.42) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateJumpingEventScore(performance);
        }
    },
    SPRINT_400M(4, "400-metre Dash", 1.53_775, 82, 1.81) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateTrackEventScore(performance);
        }
    },
    HURDLES_110M(5, "100-metre Hurdles", 5.74_352, 28.5, 1.92) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateTrackEventScore(performance);
        }
    },
    DISCUS_THROW(6, "Discus throw", 12.91,	4, 1.1) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateFieldEventScore(performance);
        }
    },
    POLE_VAULT(7, "Pole Vault", 0.2_797, 100, 1.35) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateJumpingEventScore(performance);
        }
    },
    JAVELIN_THROW(8, "Javelin Throw", 10.14, 7,	1.08) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateFieldEventScore(performance);
        }
    },
    RUN_1500M(9, "1500-metre Run", 0.03768, 480, 1.85) {
        @Override
        public int calculateScore(double performance) {
            return this.calculateTrackEventScore(performance);
        }
    };

    public static final int CM_TO_METER = 100;
    private final int id;
    private final String name;
    private final double a;
    private final double b;
    private final double c;

    DecathlonEvent(int id, String name, double a, double b, double c) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int calculateTrackEventScore(double performance) {
        Double score = this.a * Math.pow(this.b - performance, this.c);
        return score.intValue();
    }

    @Override
    public int calculateFieldEventScore(double performance) {
        Double score = this.a * Math.pow(performance - this.b, c);
        return score.intValue();
    }

    @Override
    public int calculateJumpingEventScore(double performance) {
        return this.calculateFieldEventScore(performance * CM_TO_METER);
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
