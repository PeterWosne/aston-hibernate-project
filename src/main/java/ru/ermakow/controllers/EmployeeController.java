package ru.ermakow.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ermakow.dtos.EmployeeDTO;
import ru.ermakow.exceptions.AppError;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public List<EmployeeDTO> retrieveAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO retrieveEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping(consumes = {"application/xml","application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        service.createNewEmployee(employeeDTO);
    }

    @PutMapping("/updn/{id}")
    public void updateName(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        service.updateName(id, employeeDTO.getName());
    }

    @PutMapping("/updp/{id}")
    public void updatePosition(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        service.updatePosition(id, employeeDTO.getPositionTitle());
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        service.deleteEmployeeById(id);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> resourceNotFoundExcHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError("RESOURCE NOT FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
