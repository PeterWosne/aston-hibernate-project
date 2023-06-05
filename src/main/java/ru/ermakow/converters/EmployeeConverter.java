package ru.ermakow.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ermakow.dtos.EmployeeDTO;
import ru.ermakow.dtos.PositionDTO;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Position;

@Component
@RequiredArgsConstructor
public class EmployeeConverter {

    private final PositionConverter converter;

    public EmployeeDTO entityToDTO(Employee e) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setPositionTitle(e.getPosition().getTitle());
        return dto;
    }
}
