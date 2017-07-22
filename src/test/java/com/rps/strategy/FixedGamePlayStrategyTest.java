package com.rps.strategy;

import com.rps.domain.GamePlayOption;
import org.junit.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static com.rps.domain.GamePlayOption.ROCK;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class FixedGamePlayStrategyTest {

    @Test
    public void shouldReturnRockOnly() {
        //given
        FixedGamePlayStrategy strategy = new FixedGamePlayStrategy(ROCK);

        //when
        Map<GamePlayOption, Long> playOptions = IntStream
                .rangeClosed(1, 100)
                .mapToObj(i -> strategy.play())
                .collect(groupingBy(identity(), counting()));

        //then
        assertThat(playOptions.keySet()).contains(ROCK);
        assertThat(playOptions.keySet()).doesNotContain(GamePlayOption.PAPER, GamePlayOption.SCISSORS);
    }
}
