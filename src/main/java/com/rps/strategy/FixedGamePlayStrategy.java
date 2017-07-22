package com.rps.strategy;

import com.rps.domain.GamePlayOption;

public class FixedGamePlayStrategy implements GamePlayStrategy {

    private final GamePlayOption option;

    public FixedGamePlayStrategy(GamePlayOption option) {
        this.option = option;
    }

    public GamePlayOption play() {
        return this.option;
    }
}
