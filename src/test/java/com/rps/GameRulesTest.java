package com.rps;

import com.rps.domain.GamePlayOption;
import com.rps.domain.GamePlayResult;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GameRulesTest {

    @Test
    public void testExpectedResults() {
        assertResult(GamePlayOption.ROCK, GamePlayOption.ROCK, GamePlayResult.DRAW);
        assertResult(GamePlayOption.ROCK, GamePlayOption.PAPER, GamePlayResult.LOSE);
        assertResult(GamePlayOption.ROCK, GamePlayOption.SCISSORS, GamePlayResult.WIN);

        assertResult(GamePlayOption.PAPER, GamePlayOption.ROCK, GamePlayResult.WIN);
        assertResult(GamePlayOption.PAPER, GamePlayOption.PAPER, GamePlayResult.DRAW);
        assertResult(GamePlayOption.PAPER, GamePlayOption.SCISSORS, GamePlayResult.LOSE);

        assertResult(GamePlayOption.SCISSORS, GamePlayOption.ROCK, GamePlayResult.LOSE);
        assertResult(GamePlayOption.SCISSORS, GamePlayOption.PAPER, GamePlayResult.WIN);
        assertResult(GamePlayOption.SCISSORS, GamePlayOption.SCISSORS, GamePlayResult.DRAW);
    }

    private void assertResult(GamePlayOption option1, GamePlayOption option2, GamePlayResult expectedResult) {
        assertThat(GameRules.valueOf(option1.name()).check(option2)).isEqualTo(expectedResult);
    }
}
