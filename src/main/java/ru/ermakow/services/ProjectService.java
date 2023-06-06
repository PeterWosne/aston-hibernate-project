package ru.ermakow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ermakow.converters.ProjectConverter;
import ru.ermakow.dtos.ProjectDTO;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Project;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.repositories.EmployeeRepository;
import ru.ermakow.repositories.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;
    private final EmployeeRepository employeeRepository;
    private final ProjectConverter converter;

    public List<ProjectDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(converter::entityToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = repository.findById(id);
        //если в БД позиции с таким айди нет бросим эксепшн
        if (project == null) {
            throw new ResourceNotFoundException("Position with id " + id + " not found");
        }
        return converter.entityToDTO(project);
    }

    public void createNewProject(ProjectDTO projectDTO) {
        repository.save(projectDTO.getTitle());
    }

    public void changeProjectTitle(Long id, ProjectDTO projectDTO) {
        repository.modifyTitle(id, projectDTO.getTitle());
    }

    public void deleteProjectById(Long id) {
        repository.delete(id);
    }

    public void assignEmpl2Pro(Long project_id, Long employee_id) {
        Employee employee = employeeRepository.findById(employee_id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id " + employee_id + " not found");
        }
        repository.assignEmployee(project_id, employee);
    }

    public void removeEmplFrmPro(Long project_id, Long employee_id) {
        Employee employee = employeeRepository.findById(employee_id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id " + employee_id + " not found");
        }
        repository.removeEmployee(project_id, employee);
    }
}
