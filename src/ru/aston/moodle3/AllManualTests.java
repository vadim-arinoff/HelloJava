package ru.aston.moodle3;

import ru.aston.moodle3.adapter.AdapterManualTest;
import ru.aston.moodle3.builder.BuilderManualTest;
import ru.aston.moodle3.chain.ChainManualTest;
import ru.aston.moodle3.decorator.DecoratorManualTest;
import ru.aston.moodle3.proxy.ProxyManualTest;
import ru.aston.moodle3.strategy.StrategyManualTest;

public class AllManualTests {
    public static void main(String[] args) {
        StrategyManualTest.run();
        ChainManualTest.run();
        BuilderManualTest.run();
        ProxyManualTest.run();
        DecoratorManualTest.run();
        AdapterManualTest.run();
        System.out.println("Все шесть проверок пройдены.");
    }
}
