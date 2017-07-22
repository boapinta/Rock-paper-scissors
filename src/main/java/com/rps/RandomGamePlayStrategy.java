package com.rps;

import java.util.Random;

public class RandomGamePlayStrategy implements GamePlayStrategy {

    private final static Random RANDOM = new Random();

    public PlayOption play() {
        int randomInt = RANDOM.ints(1, 3).findFirst().getAsInt();
        if (randomInt % 3 == 0) {
            return PlayOption.ROCK;
        } else if (randomInt % 2 == 0) {
            return PlayOption.PAPER;
        } else {
            return PlayOption.SCISORS;
        }
    }
}
