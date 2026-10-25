package ru.aston.moodle3.decorator;

import java.util.Locale;

public interface TextFormatter {
    String format(String text);
}

class PlainFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        return text;
    }
}

// Декораторы реализуют тот же интерфейс и хранят другой TextFormatter.
class UpperCaseDecorator implements TextFormatter {
    private final TextFormatter wrapped;

    public UpperCaseDecorator(TextFormatter wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String format(String text) {
        return wrapped.format(text).toUpperCase(Locale.ROOT);
    }
}

class PrefixDecorator implements TextFormatter {
    private final TextFormatter wrapped;
    private final String prefix;

    public PrefixDecorator(TextFormatter wrapped, String prefix) {
        this.wrapped = wrapped;
        this.prefix = prefix;
    }

    @Override
    public String format(String text) {
        return prefix + wrapped.format(text);
    }
}
