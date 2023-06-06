package ru.ermakow.converters;

import org.springframework.stereotype.Component;
import ru.ermakow.dtos.EmployeeDTO;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Project;

import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    public EmployeeDTO entityToDTO(Employee e) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setPositionTitle(e.getPosition().getTitle());
        if(e.getProjects() != null) {
            dto.setProjects(e.getProjects().stream().map(Project::getTitle).collect(Collectors.toList()));
        }
        return dto;
    }
}
