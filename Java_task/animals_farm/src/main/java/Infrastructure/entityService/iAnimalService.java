package Infrastructure.entityService;

import models.Animal;

import java.util.List;

public interface iAnimalService extends iService {

    void create(Animal animal);

    void createFromList(List<Animal> list);

    public List<Animal> getAnimals();

    Animal getLastAnimal();

    long getTotalRecords(String name);

    List<Animal> getPaginateCondition(int pageNumber, int pageSize);

    List<Animal> getPaginateConditionByName(int pageNumber, int pageSize, String name);
    void updateItem(Animal animal);
    Animal getById(long id);
}