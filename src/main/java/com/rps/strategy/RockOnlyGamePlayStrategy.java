package com.rps.strategy;

import com.rps.GamePlayOption;

public class RockOnlyGamePlayStrategy implements GamePlayStrategy {

    public GamePlayOption play() {
        return GamePlayOption.ROCK;
    }
}
