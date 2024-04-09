package services;

import Infrastructure.validarors.iValidator;
import models.Command;

import java.util.ArrayList;
import java.util.List;

public class ValidationService {

    private final iValidator stringValidator;

    public ValidationService(iValidator stringValidator) {
        this.stringValidator = stringValidator;
    }

    public List<String> validateStr(String str) {
        List<String> violation = new ArrayList<>();
        if (!this.stringValidator.validate(str)) {
            violation.add("Search value is empty!");
        }
        return violation;
    }

    public boolean validateCommand(List<Command> commands, String commandName) {
        for (Command command : commands) {
            System.out.println(command.getName());
            System.out.println(commandName);
            if (command.getName().equalsIgnoreCase(commandName)) {
                return true;
            }
        }
        return false;
    }

    public List<String> validateUserData(String firstStr, String secondStr, Long id) {
        List<String> violations = new ArrayList<>();
        if (!this.stringValidator.validate(firstStr)) {
            violations.add("field \"Name\" is empty!");
        }
        if (!this.stringValidator.validate(secondStr)) {
            violations.add("field \"Text\" is empty!");
        }
        if (id == null || id < 0) {
            violations.add("id is not correct!");
        }
        if (!violations.isEmpty()) {
            violations.add("empty_field_err");
        }
        return violations;
    }
    public List<String> validateNewAnimal(String name, String type, String birth) {
        List<String> violations = new ArrayList<>();
        if (!this.stringValidator.validate(name)) {
            violations.add("field \"Name\" is empty!");
        }
        if (!this.stringValidator.validate(type)) {
            violations.add("field \"Type\" is empty!");
        }
        if (!this.stringValidator.validate(birth)) {
            violations.add("field \"Birth\" is empty!");
        }
        return violations;
    }
}


