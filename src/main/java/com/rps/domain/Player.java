package com.rps.domain;

import com.rps.domain.GamePlayOption;
import com.rps.strategy.GamePlayStrategy;

public class Player {

    private final GamePlayStrategy gamePlayStrategy;

    public Player(GamePlayStrategy gamePlayStrategy) {
        this.gamePlayStrategy = gamePlayStrategy;
    }

    public GamePlayOption play() {
        return gamePlayStrategy.play();
    }

}
