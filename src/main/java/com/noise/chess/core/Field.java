package com.noise.chess.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.noise.chess.core.Color.*;
import static com.noise.chess.core.Coordinates.XCoordinate.A;
import static com.noise.chess.core.Coordinates.XCoordinate.B;
import static com.noise.chess.core.Coordinates.XCoordinate.C;
import static com.noise.chess.core.Coordinates.XCoordinate.D;
import static com.noise.chess.core.Coordinates.XCoordinate.E;
import static com.noise.chess.core.Coordinates.XCoordinate.F;
import static com.noise.chess.core.Coordinates.XCoordinate.G;
import static com.noise.chess.core.Coordinates.XCoordinate.H;
import static com.noise.chess.core.Coordinates.XCoordinate.get;
import static com.noise.chess.core.Coordinates.YCoordinate.*;

public final class Field {
    private static Field instance;

    private final static Set<Figure> PLAYER_FIGURES = new HashSet<>();
    private final static Set<Figure> OPPONENT_FIGURES = new HashSet<>();

    private Field(boolean playWhites) {
        resetField(playWhites);
    }

    public static Field instance(boolean playWhites) {
        if (instance == null) {
            instance = new Field(playWhites);
        }

        return instance;
    }

    public Set<Figure> getPlayerFigures() {
        return PLAYER_FIGURES;
    }

    public Set<Figure> getOpponentFigures() {
        return OPPONENT_FIGURES;
    }

    public Set<Figure> getFigures(FigureType type) {
        Set<Figure> playerFigures = PLAYER_FIGURES.stream().filter(f -> f.getFigureType().equals(type)).collect(Collectors.toSet());
        Set<Figure> opponentFigures = OPPONENT_FIGURES.stream().filter(f -> f.getFigureType().equals(type)).collect(Collectors.toSet());

        playerFigures.addAll(opponentFigures);

        return playerFigures;
    }

    public boolean resetField(boolean playWhites) {
        addPlayerFigures(playWhites ? WHITE : BLACK);
        addOpponentFigures(playWhites ? BLACK : WHITE);

        return true;
    }

    private static void addPlayerFigures(Color color) {
        // Rook
        PLAYER_FIGURES.add(Figure.rook(Coordinates.of(A, One), color));
        PLAYER_FIGURES.add(Figure.rook(Coordinates.of(H, One), color));

        // Bishop
        PLAYER_FIGURES.add(Figure.bishop(Coordinates.of(B, One), color));
        PLAYER_FIGURES.add(Figure.bishop(Coordinates.of(G, One), color));

        // Knight
        PLAYER_FIGURES.add(Figure.knight(Coordinates.of(C, One), color));
        PLAYER_FIGURES.add(Figure.knight(Coordinates.of(F, One), color));

        // Pawns
        for (int i = 0; i < 8; i++) {
            PLAYER_FIGURES.add(Figure.pawn(Coordinates.of(get(i), Two), color));
        }

        // Queen
        PLAYER_FIGURES.add(Figure.queen(Coordinates.of(D, One), color));

        // King
        PLAYER_FIGURES.add(Figure.king(Coordinates.of(E, One), color));
    }

    private static void addOpponentFigures(Color color) {
        // Rook
        OPPONENT_FIGURES.add(Figure.rook(Coordinates.of(A, Eight), color));
        OPPONENT_FIGURES.add(Figure.rook(Coordinates.of(H, Eight), color));

        // Bishop
        OPPONENT_FIGURES.add(Figure.bishop(Coordinates.of(B, Eight), color));
        OPPONENT_FIGURES.add(Figure.bishop(Coordinates.of(G, Eight), color));

        // Knight
        OPPONENT_FIGURES.add(Figure.knight(Coordinates.of(C, Eight), color));
        OPPONENT_FIGURES.add(Figure.knight(Coordinates.of(F, Eight), color));

        // Pawns
        for (int i = 0; i < 8; i++) {
            OPPONENT_FIGURES.add(Figure.pawn(Coordinates.of(get(i), Seven), color));
        }

        // Queen
        OPPONENT_FIGURES.add(Figure.queen(Coordinates.of(E, Eight), color));

        // King
        OPPONENT_FIGURES.add(Figure.king(Coordinates.of(D, Eight), color));
    }

    public Map<Coordinates, Figure> getFigureMapping() {
        Map<Coordinates, Figure> mapping = new HashMap<>();

        PLAYER_FIGURES.forEach(figure -> mapping.put(figure.getCoordinates(), figure));
        OPPONENT_FIGURES.forEach(figure -> mapping.put(figure.getCoordinates(), figure));

        return mapping;
    }
}
