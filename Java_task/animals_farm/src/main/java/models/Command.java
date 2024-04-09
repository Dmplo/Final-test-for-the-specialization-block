package models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    private String text;
    @ManyToMany(mappedBy = "animalsCommands")
    private List<Animal> animals = new ArrayList<>();

    public Command(String name) {
        this.name = name;
    }

    public Command() {

    }

    public Command(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(id, command.id) && Objects.equals(name, command.name) && Objects.equals(text, command.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text);
    }
}

