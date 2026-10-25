package ru.aston.moodle3.chain;

// Каждый обработчик проверяет своё условие и передаёт запрос следующему.
abstract class ValidationHandler {
    private ValidationHandler next;

    public void setNext(ValidationHandler next) {
        this.next = next;
    }

    public final boolean handle(String name, int age) {
        if (!isValid(name, age)) {
            return false;
        }
        return next == null || next.handle(name, age);
    }

    protected abstract boolean isValid(String name, int age);
}

class NameValidator extends ValidationHandler {
    @Override
    protected boolean isValid(String name, int age) {
        return name != null && !name.isBlank();
    }
}

class AgeValidator extends ValidationHandler {
    @Override
    protected boolean isValid(String name, int age) {
        return age >= 18;
    }
}

public class RegistrationChain {
    private final ValidationHandler first;

    public RegistrationChain() {
        NameValidator nameValidator = new NameValidator();
        nameValidator.setNext(new AgeValidator());
        first = nameValidator;
    }

    public boolean isValid(String name, int age) {
        return first.handle(name, age);
    }
}
