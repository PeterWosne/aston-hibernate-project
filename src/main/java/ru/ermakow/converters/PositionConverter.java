package ru.ermakow.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ermakow.dtos.PositionDTO;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Position;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class PositionConverter {

    public PositionDTO entityToDTO(Position p) {
        PositionDTO dto = new PositionDTO();
        dto.setId(p.getId());
        dto.setTitle(p.getTitle());
        if(p.getEmployees() != null) {
            dto.setEmployees(p.getEmployees().stream().map(Employee::getName).collect(Collectors.toList()));
        }
        return dto;
    }
}
