package com.noise.chess.domain;

import com.noise.chess.entity.Coloured;
import com.noise.chess.util.FigureToString;

public class Figure implements Coloured {
    private final Long id;
    private final Color color;
    private final FigureType figureType;
    private final boolean opponent;

    // Modifiable
    private Coordinates coordinates;

    private Figure() {
        this.id = null;
        this.color = null;
        this.figureType = null;
        this.opponent = false;
        this.coordinates = null;
    }

    private Figure(Long id,
                   Coordinates coordinates,
                   Color color,
                   FigureType figureType,
                   boolean opponent) {
        this.id = id;
        this.coordinates = coordinates;
        this.color = color;
        this.figureType = figureType;
        this.opponent = opponent;
    }

    private Figure(Coordinates coordinates,
                   Color color,
                   FigureType figureType,
                   boolean opponent) {
        this.id = null;
        this.coordinates = coordinates;
        this.color = color;
        this.figureType = figureType;
        this.opponent = opponent;
    }

    public static Figure of(Long id, Coordinates coordinates, Color color, FigureType figureType, boolean opponent) {
        return new Figure(id, coordinates, color, figureType, opponent);
    }
    public static Figure of(Coordinates coordinates, Color color, FigureType figureType, boolean opponent) {
        return new Figure(coordinates, color, figureType, opponent);
    }

    static Figure bishop(Coordinates coordinates, Color color, boolean opponent) {
        return of(coordinates, color, FigureType.Bishop, opponent);
    }

    static Figure king(Coordinates coordinates, Color color, boolean opponent) {
        return of(coordinates, color, FigureType.King, opponent);
    }

    static Figure knight(Coordinates coordinates, Color color, boolean opponent) {
        return of(coordinates, color, FigureType.Knight, opponent);
    }

    static Figure pawn(Coordinates coordinates, Color color, boolean opponent) {
        return of(coordinates, color, FigureType.Pawn, opponent);
    }

    static Figure queen(Coordinates coordinates, Color color, boolean opponent) {
        return of(coordinates, color, FigureType.Queen, opponent);
    }

    static Figure rook(Coordinates coordinates, Color color, boolean opponent) {
        return of(coordinates, color, FigureType.Rook, opponent);
    }

    public void move(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Long getId() {
        return id;
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

    public boolean isOpponent() {
        return opponent;
    }

    public boolean isPlayer() {
        return !opponent;
    }

    public String toString() {
        return FigureToString.of(this);
    }
}
