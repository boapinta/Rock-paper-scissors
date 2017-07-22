package com.rps;

import java.util.Random;

public class RandomGamePlayStrategy implements GamePlayStrategy {

    private final static int RANDOM_MIN = 1;
    private final static int RANDOM_MAX = 4;
    private final static Random RANDOM = new Random();

    public PlayOption play() {
        int randomInt = RANDOM.ints(RANDOM_MIN, RANDOM_MAX)
                .findFirst().getAsInt();
        if (randomInt % 3 == 0) {
            return PlayOption.ROCK;
        } else if (randomInt % 2 == 0) {
            return PlayOption.PAPER;
        } else {
            return PlayOption.SCISORS;
        }
    }
}