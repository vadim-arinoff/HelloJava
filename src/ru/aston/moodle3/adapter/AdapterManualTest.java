package ru.aston.moodle3.adapter;

public class AdapterManualTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        CelsiusSensor zero = new FahrenheitAdapter(new OldFahrenheitSensor(32));
        CelsiusSensor warm = new FahrenheitAdapter(new OldFahrenheitSensor(77));

        if (zero.getCelsius() != 0 || warm.getCelsius() != 25) {
            throw new AssertionError("Адаптер должен переводить градусы Фаренгейта в Цельсии");
        }

        System.out.println("Адаптер: OK (32°F → 0°C, 77°F → 25°C)");
    }
}
