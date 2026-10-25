package ru.aston.moodle3.strategy;

public class StrategyManualTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        if (calculator.calculate(1000) != 1000) {
            throw new AssertionError("Цена без скидки должна быть 1000");
        }

        calculator.setStrategy(new TenPercentDiscount());
        if (calculator.calculate(1000) != 900) {
            throw new AssertionError("Цена со скидкой 10% должна быть 900");
        }

        System.out.println("Стратегия: OK (скидка меняется во время работы)");
    }
}
