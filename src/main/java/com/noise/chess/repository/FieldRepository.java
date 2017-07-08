package com.noise.chess.repository;

import com.noise.chess.entity.FieldEntity;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface FieldRepository extends Repository<FieldEntity, Long> {
    List<FieldEntity> findAll();

    FieldEntity findOne(Long id);

    FieldEntity save(FieldEntity field);
}
