package com.noise.chess.resource;

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
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "api", produces = "application/json")
public class MoveResource {

    private final MoveService moveService;
    private final FieldService fieldService;

    @Autowired
    public MoveResource(MoveService moveService,
                        FieldService fieldService) {
        this.moveService = moveService;
        this.fieldService = fieldService;
    }

    @RequestMapping(value = "moves/{fieldId}", method = RequestMethod.POST)
    public Set<Move> getPossibleMoves(@PathVariable Long fieldId,
                                      @RequestBody Figure figure) {
        return moveService.getPossibleMoves(fieldId, figure);
    }
    @RequestMapping(value = "move/{figureId}", method = RequestMethod.PUT)
    public ResponseEntity moveFigure(@PathVariable Long figureId,
                                     @RequestBody String coordinates) {
        if (fieldService.moveFigure(figureId, coordinates)) {
            return ResponseEntity.ok().build();
        }

        throw new UnsupportedOperationException("Cannot move figure " + figureId + " to " + coordinates);
    }
}
