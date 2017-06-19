package com.noise.chess.service;

import com.noise.chess.core.Field;
import com.noise.chess.dto.FieldDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FieldService {
    private final static Logger LOG = LoggerFactory.getLogger(FieldService.class);

    public FieldDTO getField(boolean playWhites) {
        LOG.info("Getting FIELD");
        return toDto(Field.instance(playWhites));
    }

    public boolean resetField() {
        LOG.info("FIELD has been reset");
        return Field.instance().resetField();
    }

    private FieldDTO toDto(Field field) {
        return new FieldDTO(field.getFigureMapping());
    }
}
