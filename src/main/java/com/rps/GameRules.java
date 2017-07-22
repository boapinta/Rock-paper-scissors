package com.rps;

import com.rps.domain.GamePlayOption;
import com.rps.domain.GamePlayResult;
import com.rps.utils.MapWrapper;

import java.util.Map;

/**
 * These values are consistent with values of {@link GamePlayOption}.
 * This makes it possible to call GameRules.valueOf(option.name())
 * to get rule for given option.
 */
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
