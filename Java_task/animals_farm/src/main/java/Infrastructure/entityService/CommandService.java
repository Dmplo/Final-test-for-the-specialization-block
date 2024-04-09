package Infrastructure.entityService;

import Infrastructure.config.DBService;
import Infrastructure.dao.DaoFactory;
import Infrastructure.dao.iCommandDAO;
import jakarta.persistence.NoResultException;
import models.Command;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;

public class CommandService implements iCommandService {

    public void create(Command command) {
        Transaction transaction = DBService.getTransaction();
        try {
            iCommandDAO dao = DaoFactory.getDao(iCommandDAO.class);
            assert dao != null;
            dao.create(command);
            transaction.commit();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createFromList(List<Command> commands) {
        Transaction transaction = DBService.getTransaction();
        try {
            iCommandDAO dao = DaoFactory.getDao(iCommandDAO.class);
            assert dao != null;
            for (Command command : commands) {
                dao.create(command);
            }
            transaction.commit();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Command> getCommands() {
        Transaction transaction = DBService.getTransaction();
        try {
            iCommandDAO dao = DaoFactory.getDao(iCommandDAO.class);
            assert dao != null;
            return dao.getAll();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Command getByName(String name) {
        Transaction transaction = DBService.getTransaction();
        Command command;
        try {
            iCommandDAO dao = DaoFactory.getDao(iCommandDAO.class);
            assert dao != null;
            command = dao.getByName(name);
            transaction.commit();
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new RuntimeException(e);
        }

        return command;
    }

}