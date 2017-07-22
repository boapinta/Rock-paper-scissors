package com.rps;

import com.rps.domain.GamePlayResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static com.rps.GamePlaySessionResult.SingleGamePlay.ILLEGAL_RESULT_COMBINATION_ERROR_MSG;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;

public class GamePlaySessionResultTest {

    @Test
    public void shouldThrowExceptionForWinWinOrLoseLose() {
        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.WIN, GamePlayResult.WIN);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.LOSE, GamePlayResult.LOSE);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.DRAW, GamePlayResult.LOSE);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.DRAW, GamePlayResult.WIN);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.WIN, GamePlayResult.DRAW);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.LOSE, GamePlayResult.DRAW);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageStartingWith(ILLEGAL_RESULT_COMBINATION_ERROR_MSG);
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.DRAW, GamePlayResult.DRAW);
        } catch (IllegalArgumentException e) {
            fail("No exception should be thrown");
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.WIN, GamePlayResult.LOSE);
        } catch (IllegalArgumentException e) {
            fail("No exception should be thrown");
        }

        try {
            new GamePlaySessionResult.SingleGamePlay(GamePlayResult.LOSE, GamePlayResult.WIN);
        } catch (IllegalArgumentException e) {
            fail("No exception should be thrown");
        }
    }

    @Test
    @Ignore
    public void test1() {
        Collection<GamePlaySessionResult.SingleGamePlay> gamePlays = new LinkedList<>();
        gamePlays.add(new GamePlaySessionResult.SingleGamePlay(GamePlayResult.WIN, GamePlayResult.LOSE));
        gamePlays.add(new GamePlaySessionResult.SingleGamePlay(GamePlayResult.WIN, GamePlayResult.LOSE));
        gamePlays.add(new GamePlaySessionResult.SingleGamePlay(GamePlayResult.WIN, GamePlayResult.LOSE));

        gamePlays.add(new GamePlaySessionResult.SingleGamePlay(GamePlayResult.DRAW, GamePlayResult.DRAW));

        gamePlays.add(new GamePlaySessionResult.SingleGamePlay(GamePlayResult.LOSE, GamePlayResult.WIN));
        gamePlays.add(new GamePlaySessionResult.SingleGamePlay(GamePlayResult.LOSE, GamePlayResult.WIN));
        new GamePlaySessionResult(gamePlays);
    }
}
