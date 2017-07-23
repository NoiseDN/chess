package com.noise.chess.resource;

import com.noise.chess.ai.ArtificialService;
import com.noise.chess.domain.Field;
import com.noise.chess.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api", consumes = "application/json", produces = "application/json")
public class ArtificialResource {

    private final FieldService fieldService;
    private final ArtificialService artificialService;

    @Autowired
    public ArtificialResource(FieldService fieldService,
                              ArtificialService artificialService) {
        this.fieldService = fieldService;
        this.artificialService = artificialService;
    }

    @RequestMapping(value = "ai/{fieldId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity moveFigure(@PathVariable Long fieldId) {
        Field field = fieldService.getField(fieldId).orElseThrow(() -> new RuntimeException("Could not find field for id " + fieldId));

        artificialService.makeAiMove(field);

        return ResponseEntity.ok().build();
    }
}
