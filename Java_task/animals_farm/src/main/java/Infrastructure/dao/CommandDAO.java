package Infrastructure.dao;

import Infrastructure.config.DBService;
import models.*;
import org.hibernate.Session;

import java.util.List;

public class CommandDAO implements iCommandDAO {

    public CommandDAO() {
    }

    public void create(Command command) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        session.persist(command);
        session.flush();
    }

    public List<Command> getAll() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Command ", Command.class).list();
    }

    public Command getByName(String name) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("FROM Command WHERE lower(name) LIKE :name", Command.class)
                .setParameter("name", "%" + name.toLowerCase() + "%").uniqueResult();
    }
}
