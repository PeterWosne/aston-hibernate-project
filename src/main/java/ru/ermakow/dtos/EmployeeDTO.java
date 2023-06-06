package ru.ermakow.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String positionTitle;

    private List<String> projects;

    public EmployeeDTO(String name) {
        this.name = name;
    }

    public EmployeeDTO(String name, String positionTitle) {
        this.name = name;
        this.positionTitle = positionTitle;
    }
}
