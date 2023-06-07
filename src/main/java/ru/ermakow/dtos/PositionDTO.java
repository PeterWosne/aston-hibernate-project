package ru.ermakow.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PositionDTO {
    private Long id;

    private String title;

    private List<String> employees;

    public PositionDTO(String title) {
        this.title = title;
    }
}
