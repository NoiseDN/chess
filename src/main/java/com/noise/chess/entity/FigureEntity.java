package com.noise.chess.entity;

import com.noise.chess.domain.Color;
import com.noise.chess.domain.FigureType;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "figure")
@Table
public class FigureEntity implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIGURE_ID")
    private Long id;

    @Column(nullable = false)
    private String coordinates;

    @Enumerated(EnumType.ORDINAL)
    private Color color;

    @Enumerated(EnumType.ORDINAL)
    private FigureType figureType;

    @Column(nullable = false)
    private boolean opponent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FIELD_ID")
    private FieldEntity field;

    protected FigureEntity() {}

    public FigureEntity(String coordinates,
                        Color color,
                        FigureType figureType,
                        boolean opponent,
                        FieldEntity field) {
        this.coordinates = coordinates;
        this.color = color;
        this.figureType = figureType;
        this.opponent = opponent;
        this.field = field;
    }

    public Long getId() {
        return id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public Color getColor() {
        return color;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public boolean isOpponent() {
        return opponent;
    }

    public FieldEntity getField() {
        return field;
    }
}
