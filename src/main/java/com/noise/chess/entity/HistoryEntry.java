package com.noise.chess.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "history")
@Table
public class HistoryEntry implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HISTORY_ENTRY_ID")
    private Long id;

    @Column(nullable = false)
    private String record;

    @ManyToOne
    @JoinColumn(name = "FIELD_ID")
    private FieldEntity field;

    protected HistoryEntry() {}

    public HistoryEntry(String record,
                        FieldEntity field) {
        this.record = record;
        this.field = field;
    }

    public Long getId() {
        return id;
    }

    public String getRecord() {
        return record;
    }

    public FieldEntity getField() {
        return field;
    }
}
