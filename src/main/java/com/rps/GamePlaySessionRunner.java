package com.rps;

import com.rps.domain.GamePlayOption;
import com.rps.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class GamePlaySessionRunner {

    private static final Logger logger = LoggerFactory.getLogger(GamePlaySessionRunner.class);

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

    //default modifier to make method visible in test
    GamePlaySessionResult.SingleGamePlay singleGamePlay(Player player1, Player player2) {
        GamePlayOption option1 = player1.play();
        GamePlayOption option2 = player2.play();

        return GamePlaySessionResult.createSingleGamePlay(
                GameRules.valueOf(option1.name()).check(option2),
                GameRules.valueOf(option2.name()).check(option1));
    }
}
