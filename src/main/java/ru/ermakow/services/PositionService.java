package ru.ermakow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ermakow.dtos.PositionDTO;
import ru.ermakow.converters.PositionConverter;
import ru.ermakow.entities.Position;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.repositories.PositionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionConverter converter;
    private final PositionRepository repository;

    public List<PositionDTO> getAllPositions() {
        return repository.findAll()
                .stream()
                .map(converter::entityToDTO)
                .collect(Collectors.toList());
    }

    public PositionDTO getPositionById(Long id) {
        Position position = repository.findById(id);
        //если в БД позиции с таким айди нет бросим эксепшн
        if (position == null) {
            throw new ResourceNotFoundException("Position with id " + id + " not found");
        }
        return converter.entityToDTO(position);
    }

    public void createNewPosition(PositionDTO positionDTO) {
        repository.save(positionDTO.getTitle());
    }

    public void deletePositionById(Long id) {
        repository.delete(id);
    }

    public void changeTitle(Long id, PositionDTO positionDTO) {
        repository.modifyTitle(id, positionDTO.getTitle());
    }
}
