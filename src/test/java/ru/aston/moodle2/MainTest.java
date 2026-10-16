package ru.aston.moodle2;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void printsStudentsAndYearOfShortestSuitableBook() throws IOException {
        String output = captureOutput(Path.of("src/main/resources/students.json"));

        assertTrue(output.contains("Анна"));
        assertTrue(output.contains("Борис"));
        assertTrue(output.contains("Елена"));
        assertTrue(output.contains("Год выпуска найденной книги: 2002"));
    }

    @Test
    void printsAbsenceMessageWhenAllBooksAreOld() throws IOException {
        String output = captureOutput(Path.of("src/test/resources/students-old-books.json"));

        assertTrue(output.contains("Подходящая книга отсутствует"));
    }

    private String captureOutput(Path path) throws IOException {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            Main.process(path);
            return output.toString(StandardCharsets.UTF_8);
        } finally {
            System.setOut(originalOut);
        }
    }
}
