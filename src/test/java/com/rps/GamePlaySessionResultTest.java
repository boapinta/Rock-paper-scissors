package com.rps;

import com.rps.domain.GamePlayResult;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static com.rps.GamePlaySessionResult.SingleGamePlay.ILLEGAL_RESULT_COMBINATION_ERROR_MSG;
import static com.rps.domain.GamePlayResult.DRAW;
import static com.rps.domain.GamePlayResult.LOSE;
import static com.rps.domain.GamePlayResult.WIN;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;

public class GamePlaySessionResultTest {

    private static final int P1_WINS = 3;
    private static final int P2_WINS = 2;
    private static final int DRAWS = 1;

    @Test
    public void testCountForPlayerByResult() {
        //given
        Collection<GamePlaySessionResult.SingleGamePlay> gamePlays = new LinkedList<>();
        IntStream.rangeClosed(1, P1_WINS).forEach(i ->
                gamePlays.add(GamePlaySessionResult.createSingleGamePlay(WIN, LOSE)));
        IntStream.rangeClosed(1, DRAWS).forEach(i ->
                gamePlays.add(GamePlaySessionResult.createSingleGamePlay(DRAW, DRAW)));
        IntStream.rangeClosed(1, P2_WINS).forEach(i ->
                gamePlays.add(GamePlaySessionResult.createSingleGamePlay(LOSE, WIN)));
        GamePlaySessionResult result = new GamePlaySessionResult(gamePlays);

        //then
        assertThat(result.countForPlayer1ByResult(WIN)).isEqualTo(P1_WINS);
        assertThat(result.countForPlayer1ByResult(LOSE)).isEqualTo(P2_WINS);
        assertThat(result.countForPlayer1ByResult(DRAW)).isEqualTo(DRAWS);

        assertThat(result.countForPlayer2ByResult(WIN)).isEqualTo(P2_WINS);
        assertThat(result.countForPlayer2ByResult(LOSE)).isEqualTo(P1_WINS);;
        assertThat(result.countForPlayer2ByResult(DRAW)).isEqualTo(DRAWS);
    }
    
    @Test
    public void shouldThrowExceptionForIllegalResultCombination() {
        try {
            GamePlaySessionResult.createSingleGamePlay(WIN, WIN);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(LOSE, LOSE);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(GamePlayResult.DRAW, LOSE);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(GamePlayResult.DRAW, WIN);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(WIN, GamePlayResult.DRAW);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(LOSE, GamePlayResult.DRAW);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(GamePlayResult.DRAW, GamePlayResult.DRAW);
        } catch (IllegalArgumentException e) {
            fail("No exception should be thrown");
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(WIN, LOSE);
        } catch (IllegalArgumentException e) {
            fail("No exception should be thrown");
        }

        try {
            GamePlaySessionResult.createSingleGamePlay(LOSE, WIN);
        } catch (IllegalArgumentException e) {
            fail("No exception should be thrown");
        }
    }
    
}
