package ru.aston.moodle3.strategy;

// Стратегия: общий способ вычисления цены.
interface DiscountStrategy {
    int apply(int price);
}

class NoDiscount implements DiscountStrategy {
    @Override
    public int apply(int price) {
        return price;
    }
}

class TenPercentDiscount implements DiscountStrategy {
    @Override
    public int apply(int price) {
        return price * 90 / 100;
    }
}

// Контекст работает с интерфейсом и может менять стратегию.
public class PriceCalculator {
    private DiscountStrategy strategy;

    public PriceCalculator(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int price) {
        return strategy.apply(price);
    }
}
