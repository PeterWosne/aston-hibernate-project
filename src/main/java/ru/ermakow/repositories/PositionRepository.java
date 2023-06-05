package ru.ermakow.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.ermakow.entities.Position;

import javax.persistence.*;
import java.util.List;

@Repository
public class PositionRepository {

    SessionFactory factory = new org.hibernate.cfg.Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    EntityManager em = factory.createEntityManager();

    public Position findById(Long id) {
        em.getTransaction().begin();
        Position position = em.find(Position.class, id);
        em.getTransaction().commit();
        return position;
    }

    public Position findByTitle(String positionTitle) {
        em.getTransaction().begin();
        Position position = (Position) em.createQuery("SELECT p FROM Position p WHERE p.title = :title").setParameter("title", positionTitle).getSingleResult();
        em.getTransaction().commit();
        return position;
    }

    public List<Position> findAll() {
        em.getTransaction().begin();
        List<Position> positions = em.createQuery("SELECT p FROM Position p", Position.class).getResultList();
        em.getTransaction().commit();
        return positions;
    }

    public void save(String title) {
        em.getTransaction().begin();
        Position position = new Position();
        position.setTitle(title);
        em.persist(position);
        em.getTransaction().commit();
    }

    public void delete(Position position) {
        em.getTransaction().begin();
        em.remove(position);
        em.getTransaction().commit();
    }

    public void modifyTitle(Long id, String title) {
        em.getTransaction().begin();
        Position old = em.find(Position.class,id);
        old.setTitle(title);
        em.merge(old);
        em.getTransaction().commit();
    }
}
