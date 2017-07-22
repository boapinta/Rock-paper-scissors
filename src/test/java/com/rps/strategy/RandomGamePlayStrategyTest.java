package com.rps.strategy;


import com.rps.GamePlayOption;
import com.rps.strategy.RandomGamePlayStrategy;
import org.junit.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class RandomGamePlayStrategyTest {

    private RandomGamePlayStrategy strategy = new RandomGamePlayStrategy();

    @Test
    public void shouldReturnVariousResults() {
        Map<GamePlayOption, Long> playOptions = IntStream
                .rangeClosed(1, 100)
                .mapToObj(i -> strategy.play())
                .collect(groupingBy(identity(), counting()));

        assertThat(playOptions.keySet())
                .contains(GamePlayOption.ROCK, GamePlayOption.PAPER, GamePlayOption.SCISSORS);
    }
}
