package ru.aston.moodle3.decorator;

public class DecoratorManualTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        TextFormatter plain = new PlainFormatter();
        if (!plain.format("hello").equals("hello")) {
            throw new AssertionError("Базовый форматер должен оставить текст без изменений");
        }

        TextFormatter decorated = new PrefixDecorator(
                new UpperCaseDecorator(plain), "INFO: "
        );
        if (!decorated.format("hello").equals("INFO: HELLO")) {
            throw new AssertionError("Декораторы должны добавить префикс и изменить регистр");
        }

        System.out.println("Декоратор: OK (два улучшения поверх базового объекта)");
    }
}
