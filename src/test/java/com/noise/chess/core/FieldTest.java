package com.noise.chess.core;

import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

public class FieldTest {

    private final Field whitesField = Field.instance(true);

    @Test
    public void creates_singleton_field() {
        final Field fieldDuplicate = Field.instance(false);

        assertThat(whitesField).isEqualTo(fieldDuplicate);
    }

    @Test
    public void checks_colors_positions_playing_whites() {
        Set<Figure> playerFigures = whitesField.getPlayerFigures();
        Set<Figure> opponentFigures = whitesField.getOpponentFigures();

        assertThat(playerFigures.stream().map(Figure::getColor).allMatch(color -> color.equals(Color.WHITE))).isTrue();
        assertThat(opponentFigures.stream().map(Figure::getColor).allMatch(color -> color.equals(Color.BLACK))).isTrue();
    }
}
