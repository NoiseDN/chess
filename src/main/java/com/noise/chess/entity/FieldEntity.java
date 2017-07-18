package com.noise.chess.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "field")
@Table
public class FieldEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIELD_ID")
    private Long id;

    @Column(nullable = false)
    private boolean playWhites;

    @Column(nullable = false)
    private String playerName;

    @OneToMany(mappedBy = "field")
    private Set<FigureEntity> figures;

    @OneToMany(mappedBy = "field")
    private List<HistoryEntry> history;

    protected FieldEntity() {}

    public FieldEntity(boolean playWhites,
                       String playerName) {
        super();
        this.playWhites = playWhites;
        this.playerName = playerName;
    }

    public Long getId() {
        return id;
    }

    public boolean isPlayWhites() {
        return playWhites;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Set<FigureEntity> getFigures() {
        return figures;
    }

    public List<HistoryEntry> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEntry> history) {
        this.history = history;
    }

    public void setFigures(Set<FigureEntity> figures) {
        this.figures = figures;
    }
}
