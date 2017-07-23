package com.noise.chess.util;

import com.noise.chess.domain.Color;
import com.noise.chess.domain.Figure;
import com.noise.chess.entity.Coloured;
import com.noise.chess.entity.FigureEntity;

public class FigureToString {

    public static String of(FigureEntity entity) {
        return getFigureChar(entity) + " " + CoordinateUtil.toChessFormat(entity.getCoordinates());
    }

    public static String of(Figure figure) {
        return getFigureChar(figure) + " " + CoordinateUtil.toChessFormat(figure.getCoordinates());
    }

    private static String getFigureChar(Coloured coloured) {
        boolean white = coloured.getColor().equals(Color.WHITE);

        switch (coloured.getFigureType()) {
            case King:
                return white ? "♔" : "♚";
            case Queen:
                return white ? "♕" : "♛";
            case Bishop:
                return white ? "♗" : "♝";
            case Rook:
                return white ? "♖" : "♜";
            case Knight:
                return white ? "♘" : "♞";
            case Pawn:
                return white ? "♙" : "♟";
            default:
                return "?";
        }
    }
}
