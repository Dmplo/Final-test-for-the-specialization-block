package models;

import Infrastructure.enums.MainGroup;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Animal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private MainGroup mainGroup;
    @Column(columnDefinition = "DATE")
    private LocalDate birth;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "animals_commands",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "command_id")
    )
    private List<Command> animalsCommands = new ArrayList<>();
    public Animal(String name, MainGroup mainGroup, LocalDate birth) {
        this.name = name;
        this.mainGroup = mainGroup;
        this.birth = birth;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Animal() {

    }
    public abstract Map<String, String> getListCommands();
    public void animalCommands(Command command) {
        animalsCommands.add(command);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainGroup getMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(MainGroup mainGroup) {
        this.mainGroup = mainGroup;
    }

    public abstract String getType();

    public List<Command> getAnimalsCommands() {
        return animalsCommands;
    }

    public void setAnimalsCommands(List<Command> animalsCommands) {
        this.animalsCommands = animalsCommands;
    }
}