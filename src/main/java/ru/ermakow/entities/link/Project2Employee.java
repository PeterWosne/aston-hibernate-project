package ru.ermakow.entities.link;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects2employees")
public class Project2Employee {

    @EmbeddedId
    private ProjectAssigningKey projectAssigningKey;
    @Column(name = "employee_id", insertable = false, updatable = false)
    private Long employeeId;
    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;
}
