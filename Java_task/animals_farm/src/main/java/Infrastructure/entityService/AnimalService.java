package Infrastructure.entityService;

import Infrastructure.config.DBService;
import Infrastructure.dao.DaoFactory;
import Infrastructure.dao.iAnimalDAO;
import jakarta.persistence.NoResultException;
import models.Animal;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;

public class AnimalService implements iAnimalService {

    public void create(Animal animal) {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            dao.create(animal);
            transaction.commit();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    public void createFromList(List<Animal> list) {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            for (Animal animal : list) {
                dao.create(animal);
            }
            transaction.commit();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    public List<Animal> getAnimals() {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            return dao.getAll();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    public Animal getLastAnimal() {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            Animal animal = dao.getLastAnimal();
            transaction.commit();
            return animal;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    public Animal getById(long id) {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            Animal animal = dao.getById(id);
            transaction.commit();
            return animal;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    public void updateItem(Animal animal) {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            dao.update(animal);
            transaction.commit();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    public long getTotalRecords(String name) {
        Transaction transaction = DBService.getTransaction();
        try {
            long totalRecords;
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            if (name.isEmpty()) {
                totalRecords = dao.getTotalRecords();
            } else {
                totalRecords = dao.getTotalRecordsByName(name);
            }
            transaction.commit();
            return totalRecords;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Animal> getPaginateCondition(int pageNumber, int pageSize) {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            List<Animal> matches = dao.getPaginateCondition(pageNumber, pageSize);
            transaction.commit();
            return matches;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    public List<Animal> getPaginateConditionByName(int pageNumber, int pageSize, String name) {
        Transaction transaction = DBService.getTransaction();
        try {
            iAnimalDAO dao = DaoFactory.getDao(iAnimalDAO.class);
            assert dao != null;
            List<Animal> matches = dao.getPaginateConditionByName(pageNumber, pageSize, name);
            transaction.commit();
            return matches;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }
}