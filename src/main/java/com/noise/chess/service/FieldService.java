package com.noise.chess.service;

import com.noise.chess.core.Field;
import com.noise.chess.core.Figure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FieldService {
    private final static Logger LOG = LoggerFactory.getLogger(FieldService.class);

    public Set<Figure> getField(boolean playWhites) {
        return Field.instance(playWhites).getAllFigures();
    }

    public Set<Figure> getField() {
        return Field.instance().getAllFigures();
    }

    public boolean resetField() {
        LOG.info("FIELD has been reset");
        return Field.instance().resetField();
    }
}
