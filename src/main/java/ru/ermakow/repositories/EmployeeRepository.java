package ru.ermakow.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Position;
import ru.ermakow.hibernate.HibernateSessionFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final HibernateSessionFactory sessionFactory;
    private final PositionRepository positionRepository;

    public Employee findById(Long id) {
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.getTransaction().commit();
            return employee;
        }
    }

    public List<Employee> findAll() {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Employee> employees = session
                    .createQuery("select e from Employee e", Employee.class).getResultList();
            session.getTransaction().commit();
            return employees;
        }
    }

    public void save(String name, String positionTitle) {
        Position position = positionRepository.findByTitle(positionTitle);
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Employee employee = new Employee();
            employee.setName(name);
            employee.setPosition(position);
            session.save(employee);
            session.getTransaction().commit();
        }
    }

    public void modifyName(Long id, String newName) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            employee.setName(newName);
            session.getTransaction().commit();
        }
    }

    public void updatePosition(Long id, Position position) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class,id);
            employee.setPosition(position);
            session.save(employee);
            session.getTransaction().commit();
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            session.getTransaction().commit();
        }
    }
}
