package services;

import Infrastructure.entityService.ServiceFactory;
import Infrastructure.entityService.iAnimalService;
import jakarta.servlet.http.HttpServlet;
import models.Animal;
import models.Pagination;

import java.util.List;

public class AllAnimalsService extends HttpServlet {
    private final iAnimalService animalService = ServiceFactory.getService(iAnimalService.class);

    private final Pagination<Animal> paginateAnimals;
    int pageSize;

    public AllAnimalsService(int pageSize) {
        this.pageSize = pageSize;
        this.paginateAnimals = new Pagination<>();
    }

    public void init(String animalName, int pageNumber) {
        int lastPageNumber;
        long totalRecords;

        assert this.animalService != null;
        totalRecords = this.animalService.getTotalRecords(animalName);

        if (totalRecords == 0) {
            throw new RuntimeException(String.format("Value \"%s\" is not found", animalName));
        }
        if (totalRecords + 1 >= (long) pageNumber * pageSize) {
            if (totalRecords % this.pageSize == 0) {
                lastPageNumber = (int) (totalRecords / this.pageSize);
            } else {
                lastPageNumber = (int) (totalRecords / this.pageSize) + 1;
            }
            this.paginateAnimals.setCurrentPageNumber(pageNumber);
            this.paginateAnimals.setPageSize(this.pageSize);
            this.paginateAnimals.setLastPageNumber(lastPageNumber);
            this.paginateAnimals.setTotalRecords(totalRecords);
        } else {
            throw new RuntimeException("Not valid page number");
        }
    }

    public void updateAnimalsByCondition(int pageNumber) {
        this.init("", pageNumber);
        assert this.animalService != null;
        List<Animal> animals = this.animalService.getPaginateCondition(this.paginateAnimals.getCurrentPageNumber(), this.paginateAnimals.getPageSize());
        this.paginateAnimals.setRecords(animals);
    }

    public void updateAnimalsByCondition(String name, int pageNumber) {
        this.init(name, pageNumber);
        assert this.animalService != null;
        List<Animal> animals = this.animalService.getPaginateConditionByName(this.paginateAnimals.getCurrentPageNumber(), this.paginateAnimals.getPageSize(), name);
        this.paginateAnimals.setRecords(animals);
    }

    public List<Animal> getAnimalsByPageNumber(int pageNumber) {
        this.updateAnimalsByCondition(pageNumber);
        return this.paginateAnimals.getRecords();
    }

    public List<Animal> getAnimalsByParams(int pageNumber, String animalName) {
        this.updateAnimalsByCondition(animalName, pageNumber);
        return this.paginateAnimals.getRecords();
    }

    public Pagination<Animal> getPaginateAnimals() {
        return paginateAnimals;
    }
}
