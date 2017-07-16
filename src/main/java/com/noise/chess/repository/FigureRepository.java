package com.noise.chess.repository;

import com.noise.chess.entity.FigureEntity;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface FigureRepository extends Repository<FigureEntity, Long> {
    List<FigureEntity> findAll();

    FigureEntity findOne(Long id);

    FigureEntity save(FigureEntity figure);

    Set<FigureEntity> save(Set<FigureEntity> figures);

    void delete(FigureEntity figure);
}
