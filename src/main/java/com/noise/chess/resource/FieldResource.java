package com.noise.chess.resource;

import com.noise.chess.core.Figure;
import com.noise.chess.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "api", produces = "application/json")
public class FieldResource {
    private final FieldService fieldService;

    @Autowired
    public FieldResource(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @RequestMapping(value = "field", method = RequestMethod.GET)
    public Set<Figure> getField(@RequestParam("playWhites") Boolean playWhites) {
        return fieldService.getField(playWhites);
    }

    @RequestMapping(value = "field/reset", method = RequestMethod.GET)
    public ResponseEntity resetField() {

        if (fieldService.resetField()) {
            return ResponseEntity.ok("FIELD has been reset");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
