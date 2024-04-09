package services;

import Infrastructure.entityService.*;
import Infrastructure.enums.Names;
import models.Animal;
import models.Command;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimalsCommandsService {
   private final List<Command> commands = new ArrayList<>();
   private final iAnimalService animalService = ServiceFactory.getService(iAnimalService.class);
   private final iCommandService commandService = ServiceFactory.getService(iCommandService.class);

    public void addCommand(Animal animal, String name, String text) {
        Command command = this.firstOrCreate(name, text);
        animal.animalCommands(command);
        assert this.animalService != null;
        this.animalService.updateItem(animal);
    }

    public Command firstOrCreate(String name, String text) {
        try {
            assert this.commandService != null;
            Command command = this.commandService.getByName(name);
            if (command == null) {
                command = new Command(name, text);
                this.commandService.create(command);
            }
            return command;
        } catch (Exception e) {
            throw new RuntimeException("Error, failed to create command" + e.getMessage());
        }
    }

    public Animal createOneAnimal(String name, String type, LocalDate birth) {
        Animal animal = this.createAnimal(name, type, birth);
        this.getCommand(animal, true);
        assert this.commandService != null;
        this.commandService.createFromList(this.commands);
        assert this.animalService != null;
        this.animalService.create(animal);
        return animal;
    }

    public void createAnimals() {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < Names.values().length; i++) {
            Animal animal = this.createAnimal(String.valueOf(Names.values()[i]), String.valueOf(RandomDateOfBirth.getRndType()), RandomDateOfBirth.getDate());
            animals.add(animal);
            this.getCommand(animal, false);
        }
        assert this.commandService != null;
        this.commandService.createFromList(this.commands);
        assert this.animalService != null;
        this.animalService.createFromList(animals);
    }

    private void getCommand(Animal animal, Boolean state) {
        for (Map.Entry<String, String> entry : animal.getListCommands().entrySet()) {
            Command command = state ? firstOrCreate(entry.getKey(), entry.getValue()) : firstOrCreateSec(entry.getKey(), entry.getValue());
            animal.animalCommands(command);
        }
    }

    public Command firstOrCreateSec(String name, String text) {
        Command command = null;
        if (this.commands.isEmpty()) {
            command = new Command(name, text);
            this.commands.add(command);
            return command;
        }
        for (Command elem : this.commands) {
            if (elem.getName().equals(name)) {
                return elem;
            } else {
                command = new Command(name, text);
            }
        }
        this.commands.add(command);
        return command;
    }

    private Animal createAnimal(String name, String type, LocalDate birth) {
        try {
            Class<?> animal = Class.forName(String.format("models.%s", type));
            return (Animal) animal.getConstructor(String.class, LocalDate.class).newInstance(name, birth);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
