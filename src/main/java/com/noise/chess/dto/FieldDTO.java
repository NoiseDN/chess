package com.noise.chess.dto;

import com.noise.chess.core.Coordinates;
import com.noise.chess.core.Figure;

import java.util.Map;

public class FieldDTO {
    private final Map<Coordinates, Figure> figureMapping;

    public FieldDTO(Map<Coordinates, Figure> figureMapping) {
        this.figureMapping = figureMapping;
    }

    public Map<Coordinates, Figure> getFigureMapping() {
        return figureMapping;
    }
}
