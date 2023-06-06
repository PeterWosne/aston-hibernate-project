package ru.ermakow.converters;

import org.springframework.stereotype.Component;
import ru.ermakow.dtos.ProjectDTO;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Project;

import java.util.stream.Collectors;

@Component
public class ProjectConverter {

    public ProjectDTO entityToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        if(project.getEmployees() != null) {
            dto.setEmployees(project.getEmployees().stream().map(Employee::getName).collect(Collectors.toList()));
        }
        return dto;
    }
}
