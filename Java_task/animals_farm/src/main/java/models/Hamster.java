package models;

import Infrastructure.enums.MainGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Hamster extends Animal {
    @Transient
    private final Map<String, String> listCommands = new HashMap<>();

    public Hamster(String name, LocalDate birth) {
        super(name, MainGroup.PET, birth);
        this.writeCommands();
    }

    public Hamster() {}

    @Override
    public Map<String, String> getListCommands() {
        return listCommands;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    private void writeCommands() {
        listCommands.put("Roll", "I'm rolling the wheel");
        listCommands.put("Hide", "I'm hiding");
    }
}
