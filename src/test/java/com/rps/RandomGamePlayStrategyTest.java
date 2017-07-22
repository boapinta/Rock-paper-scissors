package com.rps;


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
        Map<PlayOption, Long> playOptions = IntStream
                .rangeClosed(1, 100)
                .mapToObj(i -> strategy.play())
                .collect(groupingBy(identity(), counting()));

        assertThat(playOptions.keySet())
                .contains(PlayOption.ROCK, PlayOption.PAPER, PlayOption.SCISORS);
    }
}
