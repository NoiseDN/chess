package com.noise.chess.domain;

public class HistoryEntryDTO {

    private final Long id;
    private final String record;
    private final String timestamp;

    private HistoryEntryDTO(Long id,
                            String record,
                            String timestamp) {
        this.id = id;
        this.record = record;
        this.timestamp = timestamp;
    }

    public static HistoryEntryDTO of(Long id, String record, String timestamp) {
        return new HistoryEntryDTO(id, record, timestamp);
    }

    public Long getId() {
        return id;
    }

    public String getRecord() {
        return record;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
