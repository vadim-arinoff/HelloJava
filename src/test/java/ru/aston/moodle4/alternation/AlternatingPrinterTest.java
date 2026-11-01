package ru.aston.moodle4.alternation;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlternatingPrinterTest {
    @Test
    void startsWithOneAndAlternates() throws InterruptedException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(bytes, false, StandardCharsets.UTF_8);

        AlternatingPrinter.print(output, 10);

        assertEquals("1212121212", bytes.toString(StandardCharsets.UTF_8));
    }

    @Test
    void oddLengthEndsWithoutWaitingForever() throws InterruptedException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(bytes, false, StandardCharsets.UTF_8);

        AlternatingPrinter.print(output, 5);

        assertEquals("12121", bytes.toString(StandardCharsets.UTF_8));
    }
}
