package com.noise.chess.domain;

public enum FigureType {
    Bishop("Bishop"),
    King("King"),
    Knight("Knight"),
    Pawn("Pawn"),
    Queen("Queen"),
    Rook("Rook");

    public final String text;

    FigureType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static boolean isKing(FigureType figureType) {
        return figureType.equals(King);
    }
}
