package com.noise.chess.core;

public class Figure {
    private final Color color;
    private final FigureType figureType;

    // Modifiable
    private Coordinates coordinates;

    private Figure() {
        this.color = null;
        this.figureType = null;
    }

    private Figure(Coordinates coordinates, Color color, FigureType figureType) {
        this.coordinates = coordinates;
        this.color = color;
        this.figureType = figureType;
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
        return figureType;
    }

    public String toString() {
        return color + " " + figureType + " at " + coordinates;
    }
}
