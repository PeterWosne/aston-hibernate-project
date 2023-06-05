package ru.ermakow.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.ermakow.entities.Employee;
import ru.ermakow.entities.Position;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeRepository {

    SessionFactory factory = new org.hibernate.cfg.Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    EntityManager em = factory.createEntityManager();

    public Employee findById(Long id) {
        Employee employee = em.find(Employee.class, id);
        return employee;
    }

    public List<Employee> findAll() {
        List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        return employees;
    }

    public void save(String name, Position position) {
        em.getTransaction().begin();
        Employee employee = new Employee(name, position);
        em.persist(employee);
        em.flush();
        em.getTransaction().commit();
    }
}
