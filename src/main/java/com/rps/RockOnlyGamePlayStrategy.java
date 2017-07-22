package com.rps;

public class RockOnlyGamePlayStrategy implements GamePlayStrategy {

    public PlayOption play() {
        return PlayOption.ROCK;
    }
}
