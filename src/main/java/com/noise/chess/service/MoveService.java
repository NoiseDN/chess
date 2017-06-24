package com.noise.chess.service;

import com.noise.chess.core.Coordinates;
import com.noise.chess.core.Coordinates.YCoordinate;
import com.noise.chess.core.Coordinates.XCoordinate;
import com.noise.chess.core.Figure;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoveService {
    public List<Coordinates> getPossibleMoves(Figure figure) {
        switch (figure.getFigureType()) {
            case King:
                return getKingMoves(figure.getCoordinates());
//            case Queen:
//                return getQueenMoves(figure.getColor(), figure.getCoordinates());
//            case Bishop:
//                return getBishopMoves(figure.getColor(), figure.getCoordinates());
//            case Knight:
//                return getKnightMoves(figure.getColor(), figure.getCoordinates());
//            case Rook:
//                return getRookMoves(figure.getColor(), figure.getCoordinates());
//            case Pawn:
//                return getPawnMoves(figure.getColor(), figure.getCoordinates());
            default:
                throw new RuntimeException("Unknown figure type " + figure.getFigureType());
        }
    }

    private List<Coordinates> getKingMoves(Coordinates coordinates) {
        List<Coordinates> kingMoves = new ArrayList<>(8);

        int X = coordinates.getX().ordinal();
        int Y = coordinates.getY().ordinal();

        try { kingMoves.add(Coordinates.of(XCoordinate.get(X + 1),    YCoordinate.get(Y + 1))); } catch (RuntimeException e) { /* swallow */ }
        try { kingMoves.add(Coordinates.of(XCoordinate.get(X + 1),    YCoordinate.get(Y)));     } catch (RuntimeException e) { /* swallow */ }
        try { kingMoves.add(Coordinates.of(XCoordinate.get(X + 1),    YCoordinate.get(Y - 1))); } catch (RuntimeException e) { /* swallow */ }
        try { kingMoves.add(Coordinates.of(XCoordinate.get(X),        YCoordinate.get(Y + 1))); } catch (RuntimeException e) { /* swallow */ }
        try { kingMoves.add(Coordinates.of(XCoordinate.get(X),        YCoordinate.get(Y - 1))); } catch (RuntimeException e) { /* swallow */ }
        try { kingMoves.add(Coordinates.of(XCoordinate.get(X - 1),    YCoordinate.get(Y - 1))); } catch (RuntimeException e) { /* swallow */ }
        try { kingMoves.add(Coordinates.of(XCoordinate.get(X - 1),    YCoordinate.get(Y)));     } catch (RuntimeException e) { /* swallow */ }
        try { kingMoves.add(Coordinates.of(XCoordinate.get(X - 1),    YCoordinate.get(Y + 1))); } catch (RuntimeException e) { /* swallow */ }

        return kingMoves;
    }
}
