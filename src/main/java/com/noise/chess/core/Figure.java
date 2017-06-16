package com.noise.chess.core;

public class Figure {
    private final Color color;
    private final FigureType type;

    // Modifiable
    private Coordinates coordinates;

    private Figure(Coordinates coordinates, Color color, FigureType type) {
        this.coordinates = coordinates;
        this.color = color;
        this.type = type;
    }

    public static Figure bishop(Coordinates coordinates, Color color) {
        return new Figure(coordinates, color, FigureType.Bishop);
    }

    public static Figure king(Coordinates coordinates, Color color) {
        return new Figure(coordinates, color, FigureType.King);
    }

    public static Figure knight(Coordinates coordinates, Color color) {
        return new Figure(coordinates, color, FigureType.Knight);
    }

    public static Figure pawn(Coordinates coordinates, Color color) {
        return new Figure(coordinates, color, FigureType.Pawn);
    }

    public static Figure queen(Coordinates coordinates, Color color) {
        return new Figure(coordinates, color, FigureType.Queen);
    }

    public static Figure rook(Coordinates coordinates, Color color) {
        return new Figure(coordinates, color, FigureType.Rook);
    }

    public void move(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Color getColor() {
        return color;
    }

    public FigureType getFigureType() {
        return type;
    }

    public String toString() {
        return color + " " + type + " at " + coordinates;
    }
}
