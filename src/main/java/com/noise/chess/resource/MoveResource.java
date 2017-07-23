package com.noise.chess.resource;

import com.noise.chess.ai.ArtificialService;
import com.noise.chess.domain.Field;
import com.noise.chess.domain.Figure;
import com.noise.chess.domain.Move;
import com.noise.chess.service.FieldService;
import com.noise.chess.service.MoveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "api", consumes = "application/json", produces = "application/json")
public class MoveResource {

    private final MoveService moveService;
    private final FieldService fieldService;
    private final ArtificialService artificialService;

    @Autowired
    public MoveResource(MoveService moveService,
                        FieldService fieldService,
                        ArtificialService artificialService) {
        this.moveService = moveService;
        this.fieldService = fieldService;
        this.artificialService = artificialService;
    }

    @RequestMapping(value = "moves/{fieldId}", method = RequestMethod.POST)
    public Set<Move> getPossibleMoves(@PathVariable Long fieldId,
                                      @RequestBody Figure figure) {
        return moveService.getPossibleMoves(fieldId, figure);
    }

    @RequestMapping(value = "move/{figureId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity moveFigure(@PathVariable Long figureId,
                                                 @RequestBody Move move) {
        fieldService.moveFigure(figureId, move);
        Field field = fieldService.getFieldByFigureId(figureId);
        artificialService.makeAiMove(field);

        return ResponseEntity.ok().build();
    }
}
