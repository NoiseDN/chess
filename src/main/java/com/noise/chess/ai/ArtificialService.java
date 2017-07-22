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

    public boolean makeAiRandomMove(Field field) {
        List<Figure> aiFigures = field.getFigures().stream().filter(Figure::isOpponent).collect(Collectors.toList());

        Figure randomFigure;
        List<Move> moves;
        do {
            Collections.shuffle(aiFigures);
            randomFigure = aiFigures.get(0);
            moves = new ArrayList<>(moveService.getPossibleMoves(field.getId(), randomFigure));
        } while (moves.size() == 0);

        Collections.shuffle(moves);
        Move randomMove = moves.get(0);

        return fieldService.moveFigure(randomFigure.getId(), randomMove);
    }
}
