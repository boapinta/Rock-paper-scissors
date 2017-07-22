package com.rps;

import com.rps.utils.MapWrapper;

import java.util.Map;

public enum GameRules {

    ROCK(MapWrapper.<GamePlayOption, GamePlayResult> instance()
        .put(GamePlayOption.ROCK, GamePlayResult.DRAW)
        .put(GamePlayOption.PAPER, GamePlayResult.LOSE)
        .put(GamePlayOption.SCISSORS, GamePlayResult.WIN)
        .get()),
    PAPER(MapWrapper.<GamePlayOption, GamePlayResult> instance()
        .put(GamePlayOption.ROCK, GamePlayResult.WIN)
        .put(GamePlayOption.PAPER, GamePlayResult.DRAW)
        .put(GamePlayOption.SCISSORS, GamePlayResult.LOSE)
        .get()),
    SCISSORS(MapWrapper.<GamePlayOption, GamePlayResult> instance()
        .put(GamePlayOption.ROCK, GamePlayResult.LOSE)
        .put(GamePlayOption.PAPER, GamePlayResult.WIN)
        .put(GamePlayOption.SCISSORS, GamePlayResult.DRAW)
        .get());
;
    private final Map<GamePlayOption, GamePlayResult> rulesMap;

    GameRules(Map<GamePlayOption, GamePlayResult> rulesMap) {
        this.rulesMap = rulesMap;
    }

    public GamePlayResult check(GamePlayOption option) {
        return rulesMap.get(option);
    }
}
