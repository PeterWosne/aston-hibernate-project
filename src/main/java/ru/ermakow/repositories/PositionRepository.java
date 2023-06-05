package ru.ermakow.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.ermakow.entities.Position;
import ru.ermakow.hibernate.HibernateSessionFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PositionRepository {

    private final HibernateSessionFactory sessionFactory;

    public Position findById(Long id) {
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Position position = session.get(Position.class, id);
            session.getTransaction().commit();
            return position;
        }
    }

    public Position findByTitle(String positionTitle) {
        try(Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Position position = (Position) session.createQuery("SELECT p FROM Position p WHERE p.title = :title")
                    .setParameter("title", positionTitle)
                    .getSingleResult();
            session.getTransaction().commit();
            return position;
        }
    }

    public List<Position> findAll() {
        try(Session session = sessionFactory.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Position> positions = session
                    .createQuery("select p from Position p", Position.class).getResultList();
            session.getTransaction().commit();
            return positions;
        }
    }

    public void save(String title) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Position position = new Position();
            position.setTitle(title);
            session.save(position);
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Position position = session.get(Position.class, id);
            session.delete(position);
            session.getTransaction().commit();
        }
    }

    public void modifyTitle(Long id, String title) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Position position = session.get(Position.class,id);
            position.setTitle(title);
            session.merge(position);
            session.getTransaction().commit();
        }
    }
}
