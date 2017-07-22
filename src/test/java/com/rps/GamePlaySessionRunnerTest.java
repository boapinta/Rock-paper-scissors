package com.rps;

import com.rps.domain.GamePlayOption;
import com.rps.domain.GamePlayResult;
import com.rps.domain.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.rps.domain.GamePlayOption.PAPER;
import static com.rps.domain.GamePlayOption.ROCK;
import static com.rps.domain.GamePlayOption.SCISSORS;
import static com.rps.domain.GamePlayResult.DRAW;
import static com.rps.domain.GamePlayResult.LOSE;
import static com.rps.domain.GamePlayResult.WIN;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GamePlaySessionRunnerTest {

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    @InjectMocks
    private GamePlaySessionRunner gamePlaySessionRunner;

    private static final int TURNS = 100;

    @Test
    public void testExpectedResultsForSingleGamePlay() {
        assertResult(ROCK, ROCK, DRAW, DRAW);
        assertResult(ROCK, PAPER, LOSE, WIN);
        assertResult(ROCK, SCISSORS, WIN, LOSE);

        assertResult(PAPER, ROCK, WIN, LOSE);
        assertResult(PAPER, PAPER, DRAW, DRAW);
        assertResult(PAPER, SCISSORS, LOSE, WIN);

        assertResult(SCISSORS, ROCK, LOSE, WIN);
        assertResult(SCISSORS, PAPER, WIN, LOSE);
        assertResult(SCISSORS, SCISSORS, DRAW, DRAW);
    }

    private void assertResult(GamePlayOption option1, GamePlayOption option2, 
                              GamePlayResult expectedResult1, GamePlayResult expectedResult2) {
        //given
        when(player1.play()).thenReturn(option1);
        when(player2.play()).thenReturn(option2);

        //when
        GamePlaySessionResult.SingleGamePlay singleGamePlay = gamePlaySessionRunner.singleGamePlay(player1, player2);

        //then
        assertThat(singleGamePlay.getPlayer1Result()).isEqualTo(expectedResult1);
        assertThat(singleGamePlay.getPlayer2Result()).isEqualTo(expectedResult2);
    }

    @Test
    public void shouldRunGamePlaysNTimesInSession() {
        //given
        when(player1.play()).thenReturn(ROCK);
        when(player2.play()).thenReturn(PAPER);

        //when
        GamePlaySessionResult result = gamePlaySessionRunner.runSession(TURNS);

        //then
        int countForPlayer1 =
            result.countForPlayer1ByResult(WIN) +
            result.countForPlayer1ByResult(GamePlayResult.LOSE) +
            result.countForPlayer1ByResult(DRAW);

        int countForPlayer2 =
            result.countForPlayer2ByResult(WIN) +
            result.countForPlayer2ByResult(GamePlayResult.LOSE) +
            result.countForPlayer2ByResult(DRAW);

        assertThat(countForPlayer1).isEqualTo(TURNS);
        assertThat(countForPlayer2).isEqualTo(TURNS);
    }
}
