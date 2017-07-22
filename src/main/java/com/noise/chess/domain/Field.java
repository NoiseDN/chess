package com.noise.chess.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private final boolean active;
    private final boolean playWhites;
    private final Set<Figure> figures;
    private final String playerName;
    private final List<HistoryEntryDTO> history;

    private Field(Long id,
                  boolean active,
                  boolean playWhites,
                  String nickName,
                  Set<Figure>
                      figures,
                  List<HistoryEntryDTO> history) {
        this.id = id;
        this.active = active;
        this.playWhites = playWhites;
        this.figures = figures;
        this.playerName = nickName;
        this.history = history;
    }

    private Field(Long id, boolean playWhites, String nickName) {
        this.id = id;
        this.active = true;
        this.playWhites = playWhites;
        this.figures = getNewGameFigures(playWhites);
        this.playerName = nickName;
        this.history = new ArrayList<>();
    }

    public static Field of(Long id, boolean active, boolean playWhites, String nickName, Set<Figure> figures, List<HistoryEntryDTO> history) {
        return new Field(id, active, playWhites, nickName, figures, history);
    }

    public static Field of(boolean playWhites, String nickName) {
        return new Field(null, playWhites, nickName);
    }

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPlayWhites() {
        return playWhites;
    }

    public Set<Figure> getFigures() {
        return figures;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<HistoryEntryDTO> getHistory() {
        return history;
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
