package ru.ermakow.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Project;
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

    public void save(String title) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Project project = new Project();
            project.setTitle(title);
            session.save(project);
            session.getTransaction().commit();
        }
    }

    public void modifyTitle(Long id, String title) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Project project = session.get(Project.class,id);
            project.setTitle(title);
            session.merge(project);
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Project project = session.get(Project.class, id);
            session.delete(project);
            session.getTransaction().commit();
        }
    }

    public void assignEmployee(Long project_id, Employee employee) {
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Project project = session.get(Project.class, project_id);
            project.getEmployees().add(employee);
            session.getTransaction().commit();
        }
    }

    public void removeEmployee(Long project_id, Employee employee) {
        List<Employee> employees = null;
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Project project = session.get(Project.class, project_id);
            employees = project.getEmployees();
            employees.remove(employee);
            project.setEmployees(employees);
            session.merge(project);
            session.getTransaction().commit();
        }
    }
}
