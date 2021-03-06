package com.noise.chess.resource;

import com.noise.chess.domain.CreateFieldDTO;
import com.noise.chess.domain.Field;
import com.noise.chess.domain.GameStatus;
import com.noise.chess.service.FieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api", consumes = "application/json", produces = "application/json")
public class FieldResource {
    private final FieldService fieldService;

    @Autowired
    public FieldResource(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @RequestMapping(value = "field", method = RequestMethod.POST)
    public Field createField(@RequestBody CreateFieldDTO createFieldDTO) {
        return fieldService.createField(createFieldDTO.isPlayWhites(), createFieldDTO.getNickName());
    }

    @RequestMapping(value = "field", method = RequestMethod.GET)
    public List<Field> getFields() {
        return fieldService.getFields();
    }

    @RequestMapping(value = "field/{id}", method = RequestMethod.GET)
    public ResponseEntity getField(@PathVariable Long id) {
        return fieldService.getField(id)
            .<ResponseEntity>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @RequestMapping(value = "field/{id}/status", method = RequestMethod.GET)
    public GameStatus getFieldStatus(@PathVariable Long id) {
        return fieldService.getGameStatus(id);
    }
}
