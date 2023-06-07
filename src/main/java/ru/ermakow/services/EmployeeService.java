package ru.ermakow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ermakow.converters.EmployeeConverter;
import ru.ermakow.dtos.EmployeeDTO;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Position;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.repositories.EmployeeRepository;
import ru.ermakow.repositories.PositionRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final PositionRepository positionRepository;
    private final EmployeeConverter converter;

    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(converter::entityToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = repository.findById(id);
        //если в БД позиции с таким айди нет бросим эксепшн
        if (employee == null) {
            throw new ResourceNotFoundException("Position with id " + id + " not found");
        }
        return converter.entityToDTO(employee);
    }

    public void createNewEmployee(EmployeeDTO employeeDTO) {
        String name = employeeDTO.getName();
        repository.save(name, employeeDTO.getPositionTitle());
    }

    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }

    public void updateName(Long id, String name) {
        repository.modifyName(id, name);
    }

    public void updatePosition(Long id, String positionTitle) {
        Position position = positionRepository.findByTitle(positionTitle);
        repository.updatePosition(id, position);
    }
}
