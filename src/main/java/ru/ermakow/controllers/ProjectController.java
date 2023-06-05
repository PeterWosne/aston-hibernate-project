package ru.ermakow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ermakow.dtos.ProjectDTO;
import ru.ermakow.exceptions.AppError;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public List<ProjectDTO> retrieveAllProjects() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProjectDTO retrievePositionById(@PathVariable Long id) {
        return service.getPositionById(id);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> resourceNotFoundExcHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError("RESOURCE NOT FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
