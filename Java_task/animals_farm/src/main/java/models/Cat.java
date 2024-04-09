package models;

import Infrastructure.enums.MainGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Cat extends Animal {
    @Transient
    private final Map<String, String> listCommands = new HashMap<>();

    public Cat(String name, LocalDate birth) {
        super(name, MainGroup.PET, birth);
        this.writeCommands();
    }

    public Cat() {}

    @Override
    public Map<String, String> getListCommands() {
        return listCommands;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    private void writeCommands() {
        listCommands.put("Sit", "I'm sitting");
        listCommands.put("Pounce", "I'm pounding the target");
    }
}
