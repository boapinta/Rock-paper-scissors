package com.rps;

import com.rps.domain.GamePlayOption;
import com.rps.domain.Player;
import com.rps.strategy.RandomGamePlayStrategy;
import com.rps.strategy.FixedGamePlayStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

import static com.rps.domain.GamePlayResult.DRAW;
import static com.rps.domain.GamePlayResult.WIN;
import static java.util.stream.Collectors.toList;

public class GamePlaySessionRunner {

    private static final Logger logger = LoggerFactory.getLogger(GamePlaySessionRunner.class);

    private static final int DEFAULT_TURNS = 100;

    private final Player player1;
    private final Player player2;

    public GamePlaySessionRunner(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public GamePlaySessionResult runSession(int turns) {
        logger.info("Running session with {} turns", turns);
        return new GamePlaySessionResult(IntStream
                .rangeClosed(1, turns)
                .mapToObj(i -> singleGamePlay(player1, player2))
                .collect(toList()));
    }

    //default modifier to make method visible for test
    GamePlaySessionResult.SingleGamePlay singleGamePlay(Player player1, Player player2) {
        GamePlayOption option1 = player1.play();
        GamePlayOption option2 = player2.play();

        return GamePlaySessionResult.createSingleGamePlay(
                GameRules.valueOf(option1.name()).check(option2),
                GameRules.valueOf(option2.name()).check(option1));
    }

    public static void main(String... args) {
        int turns = getTurns(args);

        Player player1 = new Player(new FixedGamePlayStrategy(GamePlayOption.ROCK));
        Player player2 = new Player(new RandomGamePlayStrategy());

        GamePlaySessionRunner runner = new GamePlaySessionRunner(player1, player2);
        GamePlaySessionResult result = runner.runSession(turns);

        logger.info("Players played {} times", turns);
        logger.info("Player1 won {} times", result.countForPlayer1ByResult(WIN));
        logger.info("Player2 won {} times", result.countForPlayer2ByResult(WIN));
        logger.info("There were {} draws", result.countForPlayer1ByResult(DRAW));
    }

    private static int getTurns(String... args) {
        if (args.length == 1) {
            try {
                return Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.warn("NumberFormatException for {}. Using default value {}",
                        args[0], DEFAULT_TURNS);
            }
        }
        return DEFAULT_TURNS;
    }
}
