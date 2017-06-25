package com.noise.chess.service;

import com.noise.chess.core.Coordinates;
import com.noise.chess.core.Figure;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.noise.chess.core.Coordinates.X;
import static com.noise.chess.core.Coordinates.Y;
import static com.noise.chess.core.Coordinates.of;

@Service
public class MoveService {
    public Set<Coordinates> getPossibleMoves(Figure figure) {
        switch (figure.getFigureType()) {
            case King:
                return getKingMoves(figure.getCoordinates());
            case Queen:
                return getQueenMoves(figure.getCoordinates());
            case Bishop:
                return getBishopMoves(figure.getCoordinates());
            case Knight:
                return getKnightMoves(figure.getCoordinates());
//            case Rook:
//                return getRookMoves(figure.getColor(), figure.getCoordinates());
//            case Pawn:
//                return getPawnMoves(figure.getColor(), figure.getCoordinates());
            default:
                throw new RuntimeException("Unknown figure type " + figure.getFigureType());
        }
    }

    private Set<Coordinates> getKnightMoves(Coordinates coordinates) {
        Set<Coordinates> knightMoves = new HashSet<>();

        int x = coordinates.getX().ordinal();
        int y = coordinates.getY().ordinal();

        add(knightMoves, of(X.of(x - 2), Y.of(y - 1)));
        add(knightMoves, of(X.of(x - 1), Y.of(y - 2)));

        add(knightMoves, of(X.of(x + 2), Y.of(y - 1)));
        add(knightMoves, of(X.of(x + 1), Y.of(y - 2)));

        add(knightMoves, of(X.of(x - 2), Y.of(y + 1)));
        add(knightMoves, of(X.of(x - 1), Y.of(y + 2)));

        add(knightMoves, of(X.of(x + 2), Y.of(y + 1)));
        add(knightMoves, of(X.of(x + 1), Y.of(y + 2)));

        return knightMoves;
    }

    private Set<Coordinates> getBishopMoves(Coordinates coordinates) {
        Set<Coordinates> bishopMoves = new HashSet<>();

        int x = coordinates.getX().ordinal();
        int y = coordinates.getY().ordinal();

        for (int i = 1; i < 8; i++) {
            add(bishopMoves, of(X.of(x + i), Y.of(y + i)));
            add(bishopMoves, of(X.of(x + i), Y.of(y - i)));
            add(bishopMoves, of(X.of(x - i), Y.of(y - i)));
            add(bishopMoves, of(X.of(x - i), Y.of(y + i)));
        }
        
        return bishopMoves;
    }

    private Set<Coordinates> getQueenMoves(Coordinates coordinates) {
        Set<Coordinates> queenMoves = new HashSet<>();

        int x = coordinates.getX().ordinal();
        int y = coordinates.getY().ordinal();

        for (int i = 1; i < 8; i++) {
            add(queenMoves, of(X.of(x + i), Y.of(y + i)));
            add(queenMoves, of(X.of(x + i), Y.of(y)));
            add(queenMoves, of(X.of(x + i), Y.of(y - i)));
            add(queenMoves, of(X.of(x),     Y.of(y + i)));
            add(queenMoves, of(X.of(x),     Y.of(y - i)));
            add(queenMoves, of(X.of(x - i), Y.of(y - i)));
            add(queenMoves, of(X.of(x - i), Y.of(y)));
            add(queenMoves, of(X.of(x - i), Y.of(y + i)));
        }

        return queenMoves;
    }

    private Set<Coordinates> getKingMoves(Coordinates coordinates) {
        Set<Coordinates> kingMoves = new HashSet<>(8);

        int x = coordinates.getX().ordinal();
        int y = coordinates.getY().ordinal();

        add(kingMoves, of(X.of(x + 1), Y.of(y + 1)));
        add(kingMoves, of(X.of(x + 1), Y.of(y)));
        add(kingMoves, of(X.of(x + 1), Y.of(y - 1)));
        add(kingMoves, of(X.of(x),     Y.of(y + 1)));
        add(kingMoves, of(X.of(x),     Y.of(y - 1)));
        add(kingMoves, of(X.of(x - 1), Y.of(y - 1)));
        add(kingMoves, of(X.of(x - 1), Y.of(y)));
        add(kingMoves, of(X.of(x - 1), Y.of(y + 1)));

        return kingMoves;
    }

    private void add(Set<Coordinates> moves, Coordinates coordinates) {
        if (coordinates.getX() != null && coordinates.getY() != null) {
            moves.add(coordinates);
        }
    }
}
