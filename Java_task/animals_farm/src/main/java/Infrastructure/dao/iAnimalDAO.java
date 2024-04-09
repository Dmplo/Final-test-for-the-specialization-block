package Infrastructure.dao;


import models.Animal;

import java.util.List;

public interface iAnimalDAO extends iDao {

    void create(Animal animal);

    List<Animal> getAll();

    Animal getLastAnimal();

    Animal getById(long id);

    long getTotalRecords();

    void update(Animal animal);

    long getTotalRecordsByName(String value);

    List<Animal> getPaginateCondition(int pageNumber, int pageSize);

    List<Animal> getPaginateConditionByName(int pageNumber, int pageSize, String value);
}
