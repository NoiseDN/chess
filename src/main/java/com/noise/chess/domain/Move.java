package com.noise.chess.domain;

public class Move {
    private final Coordinates coordinates;
    private final MoveType moveType;

    private Move() {
        this.coordinates = null;
        this.moveType = null;
    }

    private Move(MoveType moveType, Coordinates coordinates) {
        this.coordinates = coordinates;
        this.moveType = moveType;
    }

    public boolean isAttack() {
        return moveType != null && moveType.equals(MoveType.ATTACK);
    }

    public static Move move(Coordinates coordinates) {
        return new Move(MoveType.MOVE, coordinates);
    }

    public static Move attack(Coordinates coordinates) {
        return new Move(MoveType.ATTACK, coordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public MoveType getMoveType() {
        return moveType;
    }
}
