package com.rps.strategy;

import com.rps.domain.GamePlayOption;

public class RockOnlyGamePlayStrategy implements GamePlayStrategy {

    public GamePlayOption play() {
        return GamePlayOption.ROCK;
    }
}
