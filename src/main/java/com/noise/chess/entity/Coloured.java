package com.noise.chess.entity;

import com.noise.chess.domain.Color;
import com.noise.chess.domain.FigureType;

public interface Coloured {
    Color getColor();
    FigureType getFigureType();
}
