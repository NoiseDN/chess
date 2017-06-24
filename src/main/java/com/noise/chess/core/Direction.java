package com.noise.chess.core;

public enum Direction {
    Up, Down;

    public static Direction get(Color color) {
        switch (color) {
            case WHITE:
                return Up;
            case BLACK:
                return Down;
            default:
                throw new RuntimeException("Unknown color " + color);
        }
    }
}
