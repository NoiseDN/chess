package com.noise.chess.repository;

import com.noise.chess.entity.HistoryEntry;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface HistoryRepository extends Repository<HistoryEntry, Long> {
    List<HistoryEntry> findAll();

    HistoryEntry findOne(Long id);

    HistoryEntry save(HistoryEntry historyEntry);
}
