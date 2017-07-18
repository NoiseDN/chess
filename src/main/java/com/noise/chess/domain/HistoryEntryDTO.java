package com.noise.chess.domain;

public class HistoryEntryDTO {

    private final Long id;
    private final String record;

    private HistoryEntryDTO(Long id, String record) {
        this.id = id;
        this.record = record;
    }

    public static HistoryEntryDTO of(Long id, String record) {
        return new HistoryEntryDTO(id, record);
    }

    public Long getId() {
        return id;
    }

    public String getRecord() {
        return record;
    }
}
