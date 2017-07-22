package com.rps;

import com.rps.domain.GamePlayResult;

import java.util.Collection;
import java.util.function.Function;

/**
 * Wrapper for collection of SingleGamePlays.
 * It is able to count requested results for players.
 */
public class GamePlaySessionResult {

    public static class SingleGamePlay {

        public static final String ILLEGAL_RESULT_COMBINATION_ERROR_MSG =
                "Illegal results combination for ";

        private GamePlayResult player1Result;
        private GamePlayResult player2Result;

        private SingleGamePlay(GamePlayResult player1Result, GamePlayResult player2Result) {
            if ((player1Result == player2Result && player1Result != GamePlayResult.DRAW)
                    || (player1Result != player2Result && (player1Result == GamePlayResult.DRAW || player2Result == GamePlayResult.DRAW))
                    ) {
                throw new IllegalArgumentException(ILLEGAL_RESULT_COMBINATION_ERROR_MSG
                        + player1Result + " and " + player2Result);
            }

            this.player1Result = player1Result;
            this.player2Result = player2Result;
        }

        public GamePlayResult getPlayer1Result() {
            return player1Result;
        }

        public GamePlayResult getPlayer2Result() {
            return player2Result;
        }
    }

    public static SingleGamePlay createSingleGamePlay(GamePlayResult player1Result, GamePlayResult player2Result) {
        return new SingleGamePlay(player1Result, player2Result);
    }

    private final Collection<SingleGamePlay> gamePlays;

    public GamePlaySessionResult(Collection<SingleGamePlay> gamePlays) {
        this.gamePlays = gamePlays;
    }

    public int countForPlayer1ByResult(GamePlayResult result) {
        return countForPlayerByResult(SingleGamePlay::getPlayer1Result, result);
    }

    public int countForPlayer2ByResult(GamePlayResult result) {
        return countForPlayerByResult(SingleGamePlay::getPlayer2Result, result);
    }

    private int countForPlayerByResult(
            Function<SingleGamePlay, GamePlayResult> forPlayer,
            GamePlayResult result) {
        return (int) gamePlays.stream()
                .map(forPlayer)
                .filter(result::equals)
                .count();
    }
}
