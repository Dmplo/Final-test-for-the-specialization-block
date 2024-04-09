package Infrastructure.dao;

import Infrastructure.config.DBService;
import models.Animal;
import org.hibernate.Session;

import java.util.List;

public class AnimalDAO implements iAnimalDAO {

    public AnimalDAO() {
    }

    public void create(Animal animal) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        session.persist(animal);
        session.flush();
    }

    public List<Animal> getAll() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Animal ", Animal.class).list();
    }

    public Animal getLastAnimal() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Animal ORDER BY id DESC", Animal.class).setMaxResults(1).uniqueResult();
    }
    public Animal getById(long id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Animal WHERE id = :id", Animal.class).setParameter("id", id).uniqueResult();
    }

    public long getTotalRecords() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT COUNT (e.id) FROM Animal e", Long.class).getSingleResult();
    }

    public void update(Animal animal) {
        DBService.getSessionFactory().getCurrentSession()
                .merge(animal);
    }

    public long getTotalRecordsByName(String value) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT COUNT (e.id) FROM Animal e WHERE lower(e.name) LIKE :value OR lower(e.mainGroup) LIKE :value", Long.class)
                .setParameter("value", "%" + value.toLowerCase() + "%").getSingleResult();
    }


    public List<Animal> getPaginateCondition(int pageNumber, int pageSize) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Animal e ORDER BY e.birth", Animal.class)
                .setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }
    public List<Animal> getPaginateConditionByName(int pageNumber, int pageSize, String value) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Animal e WHERE lower(e.name) LIKE :value OR lower(e.mainGroup) LIKE :value ORDER BY e.birth", Animal.class)
                .setParameter("value", "%" + value.toLowerCase() + "%").setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }
}
