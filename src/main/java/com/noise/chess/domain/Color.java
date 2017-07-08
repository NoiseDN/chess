package com.noise.chess.domain;

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
