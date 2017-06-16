package com.noise.chess.core;

public enum Color {
    BLACK("Black"),
    WHITE("White");

    public String text;

    Color(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return this.text;
    }
}
