package ru.ermakow.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String name;

    private String positionTitle;

    public EmployeeDTO(String name, String positionTitle) {
        this.name = name;
        this.positionTitle = positionTitle;
    }
}
