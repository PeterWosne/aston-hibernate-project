package ru.ermakow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ermakow.converters.ProjectConverter;
import ru.ermakow.dtos.PositionDTO;
import ru.ermakow.dtos.ProjectDTO;
import ru.ermakow.entities.Project;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.repositories.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectConverter converter;

    public List<ProjectDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(converter::entityToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getPositionById(Long id) {
        Project project = repository.findById(id);
        //если в БД позиции с таким айди нет бросим эксепшн
        if (project == null) {
            throw new ResourceNotFoundException("Position with id " + id + " not found");
        }
        return converter.entityToDTO(project);
    }
}
