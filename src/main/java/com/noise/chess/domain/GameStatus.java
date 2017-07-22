package com.noise.chess.domain;

public class GameStatus {
    private final boolean over;
    private Color winner;

    public static final GameStatus NOT_FINISHED = new GameStatus(false);
    public static final GameStatus WHITE_WINS = new GameStatus(true, Color.WHITE);
    public static final GameStatus BLACK_WINS = new GameStatus(true, Color.BLACK);

    private GameStatus(boolean over, Color winner) {
        this.over = over;
        this.winner = winner;
    }

    private GameStatus(boolean over) {
        this.over = over;
    }

    public boolean isOver() {
        return over;
    }

    public Color getWinner() {
        return winner;
    }
}
