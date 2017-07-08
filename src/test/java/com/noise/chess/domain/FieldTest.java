package com.noise.chess.domain;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;

public class FieldTest {
    @Test
    public void checks_that_opponent_plays_black_for_white_player() {
        Field field = Field.of(true);

        assertThat(field.isPlayWhites()).isTrue();

        Set<Figure> figures = field.getFigures();

        List<Color> opponentColors = figures.stream()
            .filter(Figure::isOpponent)
            .map(Figure::getColor)
            .distinct()
            .collect(Collectors.toList());

        assertThat(opponentColors).hasSize(1);
        assertThat(opponentColors.get(0)).isEqualTo(Color.BLACK);
    }

    @Test
    public void checks_that_opponent_plays_white_for_black_player() {
        Field field = Field.of(false);

        assertThat(field.isPlayWhites()).isFalse();

        Set<Figure> figures = field.getFigures();

        List<Color> opponentColors = figures.stream()
            .filter(Figure::isOpponent)
            .map(Figure::getColor)
            .distinct()
            .collect(Collectors.toList());

        assertThat(opponentColors).hasSize(1);
        assertThat(opponentColors.get(0)).isEqualTo(Color.WHITE);
    }
}
