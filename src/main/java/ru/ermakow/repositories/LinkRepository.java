package ru.ermakow.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.ermakow.entities.link.Project2Employee;
import ru.ermakow.hibernate.HibernateSessionFactory;

@Repository
@RequiredArgsConstructor
public class LinkRepository {

    private final HibernateSessionFactory sessionFactory;

    public void delete(Long employee_id, Long project_id) {
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Project2Employee entity = session.createQuery("FROM Project2Employee WHERE employeeId = :employeeId AND projectId = :projectId", Project2Employee.class)
                    .setParameter("employeeId", employee_id)
                    .setParameter("projectId", project_id)
                            .getSingleResult();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }
}
