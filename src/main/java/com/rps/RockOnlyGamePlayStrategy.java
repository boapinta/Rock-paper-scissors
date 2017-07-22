package com.rps;

public class RockOnlyGamePlayStrategy implements GamePlayStrategy {

    public GamePlayOption play() {
        return GamePlayOption.ROCK;
    }
}
