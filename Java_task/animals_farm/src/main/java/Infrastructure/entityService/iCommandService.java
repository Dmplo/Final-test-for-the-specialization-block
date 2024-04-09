package Infrastructure.entityService;

import models.Command;

import java.util.List;

public interface iCommandService extends iService {

   void create(Command command);
    void createFromList(List<Command> commands);
    public List<Command> getCommands();

    Command getByName(String name);
}