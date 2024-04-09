package Infrastructure.dao;


import models.Animal;
import models.Command;

import java.util.List;

public interface iCommandDAO extends iDao {

    void create(Command command);
    List<Command> getAll();
    Command getByName(String name);

}
