package com.noise.chess.ai;

import com.noise.chess.domain.Field;
import com.noise.chess.domain.Figure;
import com.noise.chess.domain.Move;
import com.noise.chess.entity.FigureEntity;
import com.noise.chess.repository.FigureRepository;
import com.noise.chess.service.FieldService;
import com.noise.chess.service.MoveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtificialService {
    private final static Logger LOG = LoggerFactory.getLogger(ArtificialService.class);

    private final FieldService fieldService;
    private final MoveService moveService;
    private final FigureRepository figureRepository;

    @Autowired
    public ArtificialService(FieldService fieldService,
                             MoveService moveService,
                             FigureRepository figureRepository) {
        this.fieldService = fieldService;
        this.moveService = moveService;
        this.figureRepository = figureRepository;
    }

    public void makeAiRandomMove(Long figureId) {
        FigureEntity figure = figureRepository.findOne(figureId);
        Long fieldId = figure.getField().getId();
        Field field = fieldService.getField(fieldId).orElseThrow(() -> new RuntimeException("Could not find field for id " + fieldId));
        List<Figure> aiFigures = field.getFigures().stream().filter(Figure::isOpponent).collect(Collectors.toList());

        Figure randomFigure;
        List<Move> moves;
        do {
            Collections.shuffle(aiFigures);
            randomFigure = aiFigures.get(0);
            moves = new ArrayList<>(moveService.getPossibleMoves(fieldId, randomFigure));
        } while (moves.size() == 0);

        Collections.shuffle(moves);
        Move randomMove = moves.get(0);
        fieldService.moveFigure(randomFigure.getId(), randomMove);

        LOG.info("AI moved: {} to {}", randomFigure, randomMove);
    }
}
