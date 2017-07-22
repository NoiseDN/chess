package com.noise.chess.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.noise.chess.serialiser.CoordinatesDeserialiser;
import com.noise.chess.serialiser.CoordinatesSerialiser;

import java.util.stream.Stream;

@JsonSerialize(using = CoordinatesSerialiser.class)
@JsonDeserialize(using = CoordinatesDeserialiser.class)
public class Coordinates {
    private final X x;
    private final Y y;

    private Coordinates(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates of(X x, Y y) {
        return new Coordinates(x, y);
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public enum X {
        A("A"),
        B("B"),
        C("C"),
        D("D"),
        E("E"),
        F("F"),
        G("G"),
        H("H");

        final String text;

        X(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static X of(int index) {
            return Stream.of(X.values()).filter(c -> c.ordinal() == index)
                .findFirst()
                .orElse(null);
        }
    }

    public enum Y {
        Eight("8"),
        Seven("7"),
        Six("6"),
        Five("5"),
        Four("4"),
        Three("3"),
        Two("2"),
        One("1");

        final String text;

        Y(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static Y of(int index) {
            return Stream.of(Y.values()).filter(c -> c.ordinal() == index)
                .findFirst()
                .orElse(null);
        }
    }

    @Override
    public String toString() {
        return x.ordinal() + "," + y.ordinal();
    }
}
