package ru.aston.moodle3.chain;

public class ChainManualTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        RegistrationChain chain = new RegistrationChain();
        if (!chain.isValid("Анна", 25)) {
            throw new AssertionError("Совершеннолетний пользователь с именем должен пройти проверку");
        }
        if (chain.isValid(" ", 25)) {
            throw new AssertionError("Пустое имя должно быть отклонено первым обработчиком");
        }
        if (chain.isValid("Иван", 16)) {
            throw new AssertionError("Возраст меньше 18 должен быть отклонён вторым обработчиком");
        }

        System.out.println("Цепочка обязанностей: OK (имя → возраст)");
    }
}
