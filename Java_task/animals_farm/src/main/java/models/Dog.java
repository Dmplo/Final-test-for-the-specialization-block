package models;

import Infrastructure.enums.MainGroup;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Dog extends Animal {
    @Transient
    private final Map<String, String> listCommands = new HashMap<>();

    public Dog(String name, LocalDate birth) {
        super(name, MainGroup.PET, birth);
        this.writeCommands();
    }

    public Dog() {}

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
        listCommands.put("Stay", "I'm standing");
        listCommands.put("Fetch", "I'm fetching a stick");
    }
}
