package ru.aston.moodle3.builder;

public class BuilderManualTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        User first = new User.Builder()
                .name("Анна")
                .age(25)
                .height(170)
                .build();

        User second = new User.Builder()
                .height(180)
                .name("Иван")
                .age(30)
                .build();

        if (!first.getName().equals("Анна") || first.getAge() != 25 || first.getHeight() != 170) {
            throw new AssertionError("Первый пользователь заполнен неправильно");
        }
        if (!second.getName().equals("Иван") || second.getAge() != 30 || second.getHeight() != 180) {
            throw new AssertionError("Порядок вызовов Builder не должен менять значения");
        }

        try {
            new User.Builder().age(20).height(175).build();
            throw new AssertionError("Пользователь без имени не должен создаваться");
        } catch (IllegalArgumentException expected) {
            // Builder проверил обязательное имя.
        }

        System.out.println("Билдер: OK (поля задаются в любом порядке)");
    }
}
