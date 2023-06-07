package ru.ermakow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ermakow.dtos.ProjectDTO;
import ru.ermakow.exceptions.AppError;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.repositories.LinkRepository;
import ru.ermakow.services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;
    private final LinkRepository repository;

    @GetMapping
    public List<ProjectDTO> retrieveAllProjects() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProjectDTO retrievePositionById(@PathVariable Long id) {
        return service.getProjectById(id);
    }

    @PostMapping(consumes = {"application/xml","application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectDTO projectDTO) {
        service.createNewProject(projectDTO);
    }

    @PutMapping("/{id}")
    public void changeProjectTitle(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        service.changeProjectTitle(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePositionById(@PathVariable Long id) {
        service.deleteProjectById(id);
    }

    //добавление сотрудника на проект
    @GetMapping("/add/{employee_id}/to/{project_id}")
    public void assignEmployee2Project(@PathVariable Long employee_id, @PathVariable Long project_id) {
        service.assignEmpl2Pro(project_id, employee_id);
    }

    //удаление сотрудника с проекта
    @GetMapping("/remove/{employee_id}/fr/{project_id}")
    public void removeEmployeeFromProject(@PathVariable Long employee_id, @PathVariable Long project_id) {
        repository.delete(project_id, employee_id);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> resourceNotFoundExcHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError("RESOURCE NOT FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
