package services;

import Infrastructure.entityService.*;
import models.Animal;
import models.Command;

import java.util.ArrayList;
import java.util.List;

public class ProfileService {

   private final iAnimalService animalService = ServiceFactory.getService(iAnimalService.class);

    public void addCommand(Animal animal, String name, String text) {
        AnimalsCommandsService service = new AnimalsCommandsService();
        service.addCommand(animal, name, text);
    }

    public Animal getById(long id) {
        assert this.animalService != null;
        return this.animalService.getById(id);
    }

    public List<Command> getCommands(List<Command> animalCommands) {
        List<Command> list = new ArrayList<>();
        iCommandService commandService = ServiceFactory.getService(iCommandService.class);
        assert commandService != null;
        for (Command command : commandService.getCommands()) {
            if (!animalCommands.contains(command)) {
                list.add(command);
            }
        }
        return list;
    }
}
