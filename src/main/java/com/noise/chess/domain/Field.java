package com.noise.chess.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.noise.chess.domain.Coordinates.X;
import static com.noise.chess.domain.Coordinates.X.A;
import static com.noise.chess.domain.Coordinates.X.B;
import static com.noise.chess.domain.Coordinates.X.C;
import static com.noise.chess.domain.Coordinates.X.D;
import static com.noise.chess.domain.Coordinates.X.E;
import static com.noise.chess.domain.Coordinates.X.F;
import static com.noise.chess.domain.Coordinates.X.G;
import static com.noise.chess.domain.Coordinates.X.H;
import static com.noise.chess.domain.Coordinates.Y.Eight;
import static com.noise.chess.domain.Coordinates.Y.One;
import static com.noise.chess.domain.Coordinates.Y.Seven;
import static com.noise.chess.domain.Coordinates.Y.Two;

public final class Field {

    private final Long id;
    private final boolean playWhites;
    private final Set<Figure> figures;

    private Field(Long id, boolean playWhites, Set<Figure> figures) {
        this.id = id;
        this.playWhites = playWhites;
        this.figures = figures;
    }

    private Field(Long id, boolean playWhites) {
        this.id = id;
        this.playWhites = playWhites;
        this.figures = getNewGameFigures(playWhites);
    }

    public static Field of(Long id, boolean playWhites, Set<Figure> figures) {
        return new Field(id, playWhites, figures);
    }

    public static Field of(boolean playWhites) {
        return new Field(null, playWhites);
    }

    public Long getId() {
        return id;
    }

    public boolean isPlayWhites() {
        return playWhites;
    }

    public Set<Figure> getFigures() {
        return figures;
    }

    private Set<Figure> getNewGameFigures(boolean playWhites) {
        return Stream.concat(getOpponentFigures(playWhites ? Color.BLACK : Color.WHITE).stream(),
                             getPlayerFigures(playWhites ? Color.WHITE : Color.BLACK).stream())
            .collect(Collectors.toSet());
    }

    private Set<Figure> getOpponentFigures(Color color) {
        Set<Figure> figures = new HashSet<>();

        // Rook
        figures.add(Figure.rook(Coordinates.of(A, One), color, true));
        figures.add(Figure.rook(Coordinates.of(H, One), color, true));

        // Knight
        figures.add(Figure.knight(Coordinates.of(B, One), color, true));
        figures.add(Figure.knight(Coordinates.of(G, One), color, true));

        // Bishop
        figures.add(Figure.bishop(Coordinates.of(C, One), color, true));
        figures.add(Figure.bishop(Coordinates.of(F, One), color, true));

        // Pawns
        for (int i = 0; i < 8; i++) {
            figures.add(Figure.pawn(Coordinates.of(X.of(i), Two), color, true));
        }

        // Queen
        figures.add(Figure.queen(Coordinates.of(D, One), color, true));

        // King
        figures.add(Figure.king(Coordinates.of(E, One), color, true));

        return figures;
    }

    private Set<Figure> getPlayerFigures(Color color) {
        Set<Figure> figures = new HashSet<>();

        // Rook
        figures.add(Figure.rook(Coordinates.of(A, Eight), color, false));
        figures.add(Figure.rook(Coordinates.of(H, Eight), color, false));

        // Knight
        figures.add(Figure.knight(Coordinates.of(B, Eight), color, false));
        figures.add(Figure.knight(Coordinates.of(G, Eight), color, false));

        // Bishop
        figures.add(Figure.bishop(Coordinates.of(C, Eight), color, false));
        figures.add(Figure.bishop(Coordinates.of(F, Eight), color, false));

        // Pawns
        for (int i = 0; i < 8; i++) {
            figures.add(Figure.pawn(Coordinates.of(X.of(i), Seven), color, false));
        }

        // Queen
        figures.add(Figure.queen(Coordinates.of(D, Eight), color, false));

        // King
        figures.add(Figure.king(Coordinates.of(E, Eight), color, false));

        return figures;
    }
}
