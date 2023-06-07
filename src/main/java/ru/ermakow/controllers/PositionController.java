package ru.ermakow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ermakow.dtos.PositionDTO;
import ru.ermakow.exceptions.AppError;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.services.PositionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService service;

    @GetMapping
    public List<PositionDTO> retrieveAllPositions() {
        return service.getAllPositions();
    }

    @GetMapping("/{id}")
    public PositionDTO retrievePositionById(@PathVariable Long id) {
        return service.getPositionById(id);
    }

    @PostMapping(consumes = {"application/xml","application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createPosition(@RequestBody PositionDTO positionDTO) {
        service.createNewPosition(positionDTO);
    }

    @PutMapping("/{id}")
    public void changePositionTitle(@PathVariable Long id, @RequestBody PositionDTO positionDTO) {
        service.changeTitle(id, positionDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePositionById(@PathVariable Long id) {
        service.deletePositionById(id);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> resourceNotFoundExcHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError("RESOURCE NOT FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
