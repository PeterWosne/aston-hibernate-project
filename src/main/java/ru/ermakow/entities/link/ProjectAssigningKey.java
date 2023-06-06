package ru.ermakow.entities.link;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProjectAssigningKey implements Serializable {
    private static final long serialVersionUID = 4014176379219712769L;

    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "project_id")
    private Long projectId;
}
