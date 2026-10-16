package ru.aston.moodle2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        process(Path.of("src/main/resources/students.json"));
    }

    static void process(Path path) throws IOException {
        StudentJsonReader.read(path).stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::getPageCount))
                .distinct()
                .filter(book -> book.getPublicationYear() > 2000)
                .limit(3)
                .map(Book::getPublicationYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Подходящая книга отсутствует")
                );
    }
}
