package com.noise.chess.service;

import com.noise.chess.domain.Coordinates;
import com.noise.chess.domain.Field;
import com.noise.chess.domain.Figure;
import com.noise.chess.domain.FigureType;
import com.noise.chess.domain.Move;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.noise.chess.domain.Coordinates.X;
import static com.noise.chess.domain.Coordinates.Y;
import static com.noise.chess.domain.Coordinates.of;

@Service
public class MoveService {

    private FieldService fieldService;
    
    private final static Set<Direction> possibleDirections = new HashSet<>();

    @Autowired
    public MoveService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public Set<Move> getPossibleMoves(Long fieldId, Figure figure) {
        Optional<Field> fieldOp = fieldService.getField(fieldId);

        if (!fieldOp.isPresent()) {
            throw new RuntimeException("No field found for id " + fieldId);
        }

        Field field = fieldOp.get();

        switch (figure.getFigureType()) {
            case King:
                return getKingMoves(field, figure);
            case Queen:
                return getQueenMoves(field, figure);
            case Bishop:
                return getBishopMoves(field, figure);
            case Knight:
                return getKnightMoves(field, figure);
            case Rook:
                return getRookMoves(field, figure);
            case Pawn:
                return getPawnMoves(field, figure);
            default:
                throw new RuntimeException("Unknown figure type " + figure.getFigureType());
        }
    }

    private Set<Move> getPawnMoves(Field field, Figure figure) {
        Set<Move> pawnMoves = new HashSet<>();

        possibleDirections.clear();

        int x = figure.getCoordinates().getX().ordinal();
        int y = figure.getCoordinates().getY().ordinal();

        //TODO: fix BLACK attack diagonal (OPPONENT)

        switch (figure.getColor()) {
            case WHITE:
                possibleDirections.add(Direction.Down);
                possibleDirections.add(Direction.DownRight);
                possibleDirections.add(Direction.DownLeft);
                add(pawnMoves, of(X.of(x),     Y.of(y - 1)), field, figure, Direction.Down);
                add(pawnMoves, of(X.of(x),     Y.of(y - 2)), field, figure, Direction.Down);
                add(pawnMoves, of(X.of(x + 1), Y.of(y - 1)), field, figure, Direction.DownRight);
                add(pawnMoves, of(X.of(x - 1), Y.of(y - 1)), field, figure, Direction.DownLeft);
                break;
            case BLACK:
                possibleDirections.add(Direction.Up);
                possibleDirections.add(Direction.UpRight);
                possibleDirections.add(Direction.UpLeft);
                add(pawnMoves, of(X.of(x),     Y.of(y + 1)), field, figure, Direction.Up);
                add(pawnMoves, of(X.of(x),     Y.of(y + 2)), field, figure, Direction.Up);
                add(pawnMoves, of(X.of(x + 1), Y.of(y + 1)), field, figure, Direction.UpRight);
                add(pawnMoves, of(X.of(x - 1), Y.of(y + 1)), field, figure, Direction.UpLeft);
                break;
            default:
                throw new RuntimeException("Unknown color " + figure.getColor());
        }

        return pawnMoves;
    }

    private Set<Move> getRookMoves(Field field, Figure figure) {
        Set<Move> rookMoves = new HashSet<>();

        possibleDirections.clear();
        possibleDirections.addAll(Direction.cross());

        int x = figure.getCoordinates().getX().ordinal();
        int y = figure.getCoordinates().getY().ordinal();

        for (int i = 1; i < 8; i++) {
            add(rookMoves, of(X.of(x),     Y.of(y + i)), field,  figure, Direction.Down);
            add(rookMoves, of(X.of(x + i), Y.of(y)),     field,  figure, Direction.Right);
            add(rookMoves, of(X.of(x),     Y.of(y - i)), field,  figure, Direction.Up);
            add(rookMoves, of(X.of(x - i), Y.of(y)),     field,  figure, Direction.Left);
        }

        return rookMoves;
    }

    private Set<Move> getKnightMoves(Field field, Figure figure) {
        Set<Move> knightMoves = new HashSet<>();

        possibleDirections.clear();
        possibleDirections.add(Direction.Knight);

        int x = figure.getCoordinates().getX().ordinal();
        int y = figure.getCoordinates().getY().ordinal();

        add(knightMoves, of(X.of(x - 2), Y.of(y - 1)), field, figure, Direction.Knight);
        add(knightMoves, of(X.of(x - 1), Y.of(y - 2)), field, figure, Direction.Knight);

        add(knightMoves, of(X.of(x + 2), Y.of(y - 1)), field, figure, Direction.Knight);
        add(knightMoves, of(X.of(x + 1), Y.of(y - 2)), field, figure, Direction.Knight);

        add(knightMoves, of(X.of(x - 2), Y.of(y + 1)), field, figure, Direction.Knight);
        add(knightMoves, of(X.of(x - 1), Y.of(y + 2)), field, figure, Direction.Knight);

        add(knightMoves, of(X.of(x + 2), Y.of(y + 1)), field, figure, Direction.Knight);
        add(knightMoves, of(X.of(x + 1), Y.of(y + 2)), field, figure, Direction.Knight);

        return knightMoves;
    }

    private Set<Move> getBishopMoves(Field field, Figure figure) {
        Set<Move> bishopMoves = new HashSet<>();

        possibleDirections.clear();
        possibleDirections.addAll(Direction.diagonal());

        int x = figure.getCoordinates().getX().ordinal();
        int y = figure.getCoordinates().getY().ordinal();

        for (int i = 1; i < 8; i++) {
            add(bishopMoves, of(X.of(x + i), Y.of(y + i)), field, figure, Direction.DownRight);
            add(bishopMoves, of(X.of(x + i), Y.of(y - i)), field, figure, Direction.UpRight);
            add(bishopMoves, of(X.of(x - i), Y.of(y - i)), field, figure, Direction.UpLeft);
            add(bishopMoves, of(X.of(x - i), Y.of(y + i)), field, figure, Direction.DownLeft);
        }
        
        return bishopMoves;
    }

    private Set<Move> getQueenMoves(Field field, Figure figure) {
        Set<Move> queenMoves = new HashSet<>();

        possibleDirections.clear();
        possibleDirections.addAll(Direction.all());

        int x = figure.getCoordinates().getX().ordinal();
        int y = figure.getCoordinates().getY().ordinal();

        for (int i = 1; i < 8; i++) {
            add(queenMoves, of(X.of(x + i), Y.of(y + i)), field, figure, Direction.DownRight);
            add(queenMoves, of(X.of(x + i), Y.of(y)),     field, figure, Direction.Right);
            add(queenMoves, of(X.of(x + i), Y.of(y - i)), field, figure, Direction.UpRight);
            add(queenMoves, of(X.of(x),     Y.of(y + i)), field, figure, Direction.Down);
            add(queenMoves, of(X.of(x),     Y.of(y - i)), field, figure, Direction.Up);
            add(queenMoves, of(X.of(x - i), Y.of(y - i)), field, figure, Direction.UpLeft);
            add(queenMoves, of(X.of(x - i), Y.of(y)),     field, figure, Direction.Left);
            add(queenMoves, of(X.of(x - i), Y.of(y + i)), field, figure, Direction.DownLeft);
        }

        return queenMoves;
    }

    private Set<Move> getKingMoves(Field field, Figure figure) {
        Set<Move> kingMoves = new HashSet<>(8);

        possibleDirections.clear();
        possibleDirections.addAll(Direction.all());

        int x = figure.getCoordinates().getX().ordinal();
        int y = figure.getCoordinates().getY().ordinal();

        add(kingMoves, of(X.of(x + 1), Y.of(y + 1)), field, figure, Direction.DownRight);
        add(kingMoves, of(X.of(x + 1), Y.of(y)),     field, figure, Direction.Right);
        add(kingMoves, of(X.of(x + 1), Y.of(y - 1)), field, figure, Direction.UpRight);
        add(kingMoves, of(X.of(x),     Y.of(y + 1)), field, figure, Direction.Down);
        add(kingMoves, of(X.of(x),     Y.of(y - 1)), field, figure, Direction.Up);
        add(kingMoves, of(X.of(x - 1), Y.of(y - 1)), field, figure, Direction.UpLeft);
        add(kingMoves, of(X.of(x - 1), Y.of(y)),     field, figure, Direction.Left);
        add(kingMoves, of(X.of(x - 1), Y.of(y + 1)), field, figure, Direction.DownLeft);

        return kingMoves;
    }

    private boolean add(Set<Move> moves, Coordinates coordinates, Field field, Figure figure, Direction direction) {
        X x = coordinates.getX();
        Y y = coordinates.getY();

        if (x == null || y == null) {
            return possibleDirections.remove(direction);
        }

        boolean thereArePlayerFiguresOnTheWay = field.getFigures().stream()
            .filter(Figure::isPlayer)
            .anyMatch(f -> f.getCoordinates().getX() == x && f.getCoordinates().getY() == y);
        boolean thereAreOpponentFiguresOnTheWay = field.getFigures().stream()
            .filter(Figure::isOpponent)
            .anyMatch(f -> f.getCoordinates().getX() == x && f.getCoordinates().getY() == y);

        // Knights can jump
        if (figure.getFigureType() == FigureType.Knight) {
            if (thereAreOpponentFiguresOnTheWay) {
                return moves.add(Move.attack(coordinates));
            } else if (!thereArePlayerFiguresOnTheWay) {
                return moves.add(Move.move(coordinates));
            }
        }

        // Pawns can attack by diagonal
        if (figure.getFigureType() == FigureType.Pawn) {
            if (direction.isDiagonal()) {
                possibleDirections.remove(direction);
                if (thereAreOpponentFiguresOnTheWay) {
                    return moves.add(Move.attack(coordinates));
                }
            } else if (thereAreOpponentFiguresOnTheWay) {
                return possibleDirections.remove(direction);
            }
        }

        if (thereArePlayerFiguresOnTheWay) {
            return possibleDirections.remove(direction);
        }

        if (possibleDirections.contains(direction)) {
            if (thereAreOpponentFiguresOnTheWay) {
                possibleDirections.remove(direction);
                return moves.add(Move.attack(coordinates));
            }

            return moves.add(Move.move(coordinates));
        }

        return false;
    }

    private enum Direction {
        Up, Down, Left, Right, UpRight, UpLeft, DownRight, DownLeft, Knight;

        static List<Direction> all() {
            return Arrays.stream(values()).collect(Collectors.toList());
        }

        static List<Direction> diagonal() {
            return Arrays.asList(UpLeft, UpRight, DownLeft, DownRight);
        }

        static List<Direction> cross() {
            return Arrays.asList(Up, Right, Left, Right);
        }

        public boolean isDiagonal() {
            return diagonal().contains(this);
        }
    }
}
