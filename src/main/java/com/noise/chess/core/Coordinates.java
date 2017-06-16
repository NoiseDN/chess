package com.noise.chess.core;

import java.util.stream.Stream;

public class Coordinates {
    private final XCoordinate x;
    private final YCoordinate y;

    private Coordinates(XCoordinate x, YCoordinate y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates of(XCoordinate x, YCoordinate y) {
        return new Coordinates(x, y);
    }

    public XCoordinate getX() {
        return x;
    }

    public YCoordinate getY() {
        return y;
    }

    public static enum XCoordinate {
        A("A"),
        B("B"),
        C("C"),
        D("D"),
        E("E"),
        F("F"),
        G("G"),
        H("H");

        final String text;

        XCoordinate(String text) {
            this.text = text;
        }

        public static XCoordinate get(int index) {
            return Stream.of(XCoordinate.values()).filter(c -> c.ordinal() == index)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not get X coordinate for index " + index));
        }
    }

    public static enum YCoordinate {
        One("1"),
        Two("2"),
        Three("3"),
        Four("4"),
        Five("5"),
        Six("6"),
        Seven("7"),
        Eight("8");

        final String text;

        YCoordinate(String text) {
            this.text = text;
        }

        public static YCoordinate get(int index) {
            return Stream.of(YCoordinate.values()).filter(c -> c.ordinal() == index).findFirst()
                .orElseThrow(() -> new RuntimeException("Could not get Y coordinate for index " + index));
        }
    }

    @Override
    public String toString() {
        return "[" + x.text + ", " + y.text + "]";
    }
}
