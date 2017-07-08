package com.noise.chess.service;

import com.noise.chess.domain.Coordinates;
import com.noise.chess.domain.Field;
import com.noise.chess.domain.Figure;
import com.noise.chess.entity.FieldEntity;
import com.noise.chess.entity.FigureEntity;
import com.noise.chess.repository.FieldRepository;
import com.noise.chess.repository.FigureRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class FieldService {
    private final static Logger LOG = LoggerFactory.getLogger(FieldService.class);

    private final FieldRepository fieldRepository;
    private final FigureRepository figureRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository,
                        FigureRepository figureRepository) {
        this.fieldRepository = fieldRepository;
        this.figureRepository = figureRepository;
    }

    /**
     * Create field object and store it in DB
     *
     * @param playWhites
     * @return
     */
    public Field createField(boolean playWhites) {
        Field newField = Field.of(playWhites);

        FieldEntity createdEntity = fieldRepository.save(toEntity(newField));

        newField.getFigures().stream().map(f -> toEntity(f, createdEntity)).forEach(figureRepository::save);

        LOG.info("New field created with id " + createdEntity.getId());

        return Field.of(createdEntity.getId(), playWhites, newField.getFigures());
    }

    /**
     * Return field by id
     *
     * @param id
     * @return Field object
     */
    public Optional<Field> getField(Long id) {
        FieldEntity field = fieldRepository.findOne(id);

        if (field == null) {
            LOG.error("Could not find field by id " + id);

            return Optional.empty();
        }

        return Optional.of(toField(field));
    }

    /**
     * Get all stored fields
     *
     * @return List of Field objects
     */
    public List<Field> getFields() {
        return fieldRepository.findAll().stream()
            .map(this::toField)
            .collect(Collectors.toList());
    }

    private Field toField(FieldEntity entity) {
        return Field.of(
            entity.getId(),
            entity.isPlayWhites(),
            entity.getFigures().stream()
                .map(this::toFigure).collect(Collectors.toSet()));
    }

    private Figure toFigure(FigureEntity entity) {
        return Figure.of(
            entity.getId(),
            toCoordinates(entity.getCoordinates()),
            entity.getColor(),
            entity.getFigureType(),
            entity.isOpponent()
        );
    }

    private Coordinates toCoordinates(String coordinates) {
        String[] split = coordinates.split(",");

        return Coordinates.of(Coordinates.X.of(Integer.parseInt(split[0].trim())), Coordinates.Y.of(Integer.parseInt(split[1].trim())));
    }

    private FieldEntity toEntity(Field dto) {
        return new FieldEntity(dto.isPlayWhites());
    }

    private FigureEntity toEntity(Figure figure, FieldEntity field) {
        return new FigureEntity(
            figure.getCoordinates().toString(),
            figure.getColor(),
            figure.getFigureType(),
            figure.isOpponent(),
            field);
    }
}
