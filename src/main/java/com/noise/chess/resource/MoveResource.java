package com.noise.chess.resource;

import com.noise.chess.domain.Coordinates;
import com.noise.chess.domain.Figure;
import com.noise.chess.service.MoveService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MoveResource(MoveService moveService) {
        this.moveService = moveService;
    }

    @RequestMapping(value = "moves/{fieldId}", method = RequestMethod.POST)
    public Set<Coordinates> getPossibleMoves(@PathVariable Long fieldId,
                                             @RequestBody Figure figure) {
        return moveService.getPossibleMoves(fieldId, figure);
    }
}
