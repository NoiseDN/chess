package com.noise.chess.ai;

import com.noise.chess.domain.Field;
import com.noise.chess.domain.Figure;
import com.noise.chess.domain.Move;
import com.noise.chess.service.FieldService;
import com.noise.chess.service.MoveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArtificialService {

    private final FieldService fieldService;
    private final MoveService moveService;

    @Autowired
    public ArtificialService(FieldService fieldService,
                             MoveService moveService) {
        this.fieldService = fieldService;
        this.moveService = moveService;
    }

    public boolean makeAiMove(Field field) {
        final Map<Figure, Set<Move>> allFigureMoves = field.getFigures().stream()
            .filter(Figure::isOpponent)
            .collect(Collectors.toMap(Function.identity(), figure -> moveService.getPossibleMoves(field.getId(), figure)));

        return getFigureThatCanAttack(allFigureMoves)
            .map(figure -> attackMove(allFigureMoves, figure))
            .orElseGet(() -> randomMove(allFigureMoves));
    }

    private boolean attackMove(Map<Figure, Set<Move>> allFigureMoves, Figure attackingFigure) {
        Move attackMove = allFigureMoves.get(attackingFigure).stream().filter(Move::isAttack).findFirst().get();

        return fieldService.moveFigure(attackingFigure.getId(), attackMove);
    }

    private Figure getRandomFigureThatCanMove(Map<Figure, Set<Move>> allFigureMoves) {
        List<Figure> figuresThatCanMove = allFigureMoves.entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        Collections.shuffle(figuresThatCanMove);

        return figuresThatCanMove.get(0);
    }

    private Optional<Figure> getFigureThatCanAttack(Map<Figure, Set<Move>> figuresMoves) {
        return figuresMoves.entrySet().stream()
            .filter(entry -> entry.getValue().stream().anyMatch(Move::isAttack))
            .map(Map.Entry::getKey)
            .findAny();
    }

    private boolean randomMove(Map<Figure, Set<Move>> allFigureMoves) {
        Figure randomFigureThatCanMove = getRandomFigureThatCanMove(allFigureMoves);
        List<Move> moves = new ArrayList<>(allFigureMoves.get(randomFigureThatCanMove));

        Collections.shuffle(moves);
        Move randomMove = moves.get(0);

        return fieldService.moveFigure(randomFigureThatCanMove.getId(), randomMove);
    }
}
