package com.noise.chess.service;

import com.noise.chess.domain.Coordinates;
import com.noise.chess.domain.Field;
import com.noise.chess.domain.Figure;
import com.noise.chess.domain.HistoryEntryDTO;
import com.noise.chess.domain.Move;
import com.noise.chess.entity.FieldEntity;
import com.noise.chess.entity.FigureEntity;
import com.noise.chess.entity.HistoryEntry;
import com.noise.chess.repository.FieldRepository;
import com.noise.chess.repository.FigureRepository;
import com.noise.chess.repository.HistoryRepository;
import com.noise.chess.util.CoordinateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FieldService {
    private final static Logger LOG = LoggerFactory.getLogger(FieldService.class);

    private final FieldRepository fieldRepository;
    private final FigureRepository figureRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository,
                        FigureRepository figureRepository,
                        HistoryRepository historyRepository) {
        this.fieldRepository = fieldRepository;
        this.figureRepository = figureRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * Create field object and store it in DB
     *
     * @param playWhites
     * @return
     */
    public Field createField(boolean playWhites, String nickName) {
        Field newField = Field.of(playWhites, nickName);

        FieldEntity createdField = fieldRepository.save(toEntity(newField));

        newField.getFigures().stream().map(f -> toEntity(f, createdField)).forEach(figureRepository::save);

        LOG.info("New field created with id " + createdField.getId());

        return Field.of(createdField.getId(), playWhites, nickName, newField.getFigures(), newField.getHistory());
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

    /**
     * Applies to figure a given Move
     *
     * @param figureId
     * @param move
     * @return
     */
    public boolean moveFigure(Long figureId, Move move) {
        FigureEntity figure = figureRepository.findOne(figureId);
        String toCoordinates = move.getCoordinates().toString();
        String record = move.getMoveType() + ": " + figure + " to " + CoordinateUtil.toChessFormat(toCoordinates);
        HistoryEntry historyEntry = new HistoryEntry(record, figure.getField());

        if (move.isAttack()) {
            Optional<FigureEntity> figureKilled = figure.getField().getFigures().stream()
                .filter(f -> f.getCoordinates().equals(toCoordinates))
                .findFirst();

            if (!figureKilled.isPresent()) {
                LOG.warn("Cannot kill figure at " + toCoordinates + ". No figures on the way.");
            } else {
                figureRepository.delete(figureKilled.get());
                LOG.info("Opponent figure killed at {}", toCoordinates);
            }
        }

        figure.setCoordinates(toCoordinates);

        figureRepository.save(figure);
        LOG.info("Figure moved: {}", toFigure(figure));

        HistoryEntry savedEntry = historyRepository.save(historyEntry);
        LOG.info("History entry saved: {}", savedEntry.getId());

        return true;
    }

    private Field toField(FieldEntity entity) {
        return Field.of(
            entity.getId(),
            entity.isPlayWhites(),
            entity.getPlayerName(),
            entity.getFigures().stream()
                .map(this::toFigure).collect(Collectors.toSet()),
            entity.getHistory().stream()
                .map(this::toHistoryDto).collect(Collectors.toList()));
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

    private HistoryEntryDTO toHistoryDto(HistoryEntry historyEntry) {
        return HistoryEntryDTO.of(
            historyEntry.getId(),
            historyEntry.getRecord()
        );
    }

    private Coordinates toCoordinates(String coordinates) {
        String[] split = coordinates.split(",");

        return Coordinates.of(Coordinates.X.of(Integer.parseInt(split[0].trim())), Coordinates.Y.of(Integer.parseInt(split[1].trim())));
    }

    private FieldEntity toEntity(Field dto) {
        return new FieldEntity(dto.isPlayWhites(), dto.getPlayerName());
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
