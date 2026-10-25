package ru.aston.moodle3.adapter;

public interface CelsiusSensor {
    double getCelsius();
}

// Старая система умеет выдавать только градусы Фаренгейта.
class OldFahrenheitSensor {
    private final double fahrenheit;

    public OldFahrenheitSensor(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public double readFahrenheit() {
        return fahrenheit;
    }
}

// Адаптер переводит старый интерфейс и единицы измерения в новые.
class FahrenheitAdapter implements CelsiusSensor {
    private final OldFahrenheitSensor oldSensor;

    public FahrenheitAdapter(OldFahrenheitSensor oldSensor) {
        this.oldSensor = oldSensor;
    }

    @Override
    public double getCelsius() {
        return (oldSensor.readFahrenheit() - 32) * 5 / 9;
    }
}
