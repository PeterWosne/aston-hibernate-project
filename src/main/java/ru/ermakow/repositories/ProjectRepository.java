package ru.ermakow.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ermakow.entities.Position;
import ru.ermakow.entities.Project;
import ru.ermakow.exceptions.AppError;
import ru.ermakow.exceptions.ResourceNotFoundException;
import ru.ermakow.hibernate.HibernateSessionFactory;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final HibernateSessionFactory sessionFactory;

    public List<Project> findAll() {
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Project> projects = session
                    .createQuery("SELECT p FROM Project p", Project.class)
                    .getResultList();
            session.getTransaction().commit();
            return projects;
        }
    }

    public Project findById(Long id) {
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Project project = session.get(Project.class, id);
            session.getTransaction().commit();
            return project;
        }
    }
}
