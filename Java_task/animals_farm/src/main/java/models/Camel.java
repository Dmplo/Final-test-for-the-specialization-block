package models;

import Infrastructure.enums.MainGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Camel extends Animal {
    @Transient
    private final Map<String, String> listCommands = new HashMap<>();

    public Camel(String name, LocalDate birth) {
        super(name, MainGroup.PACK_ANIMAL, birth);
        this.writeCommands();
    }

    public Camel() {}

    @Override
    public Map<String, String> getListCommands() {
        return listCommands;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    private void writeCommands() {
        listCommands.put("Walk", "I'm walking");
        listCommands.put("CarryLoad", "I'm carrying a big load");
    }
}
