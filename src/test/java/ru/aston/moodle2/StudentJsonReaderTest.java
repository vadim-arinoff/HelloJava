package ru.aston.moodle2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentJsonReaderTest {

    @Test
    void readsThreeStudentsFromJson() throws IOException {
        List<Student> students = StudentJsonReader.read(
                Path.of("src/main/resources/students.json")
        );

        assertEquals(3, students.size());
    }

    @Test
    void everyStudentHasAtLeastFiveBooks() throws IOException {
        List<Student> students = StudentJsonReader.read(
                Path.of("src/main/resources/students.json")
        );

        assertTrue(students.stream().allMatch(student -> student.getBooks().size() >= 5));
    }

    @Test
    void missingJsonFileThrowsIOException() {
        assertThrows(
                IOException.class,
                () -> StudentJsonReader.read(Path.of("missing-students.json"))
        );
    }

    @Test
    void malformedJsonThrowsIOException() {
        assertThrows(
                IOException.class,
                () -> StudentJsonReader.read(Path.of("src/test/resources/students-malformed.json"))
        );
    }

    @Test
    void rejectsStudentWithFewerThanFiveBooks() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> StudentJsonReader.read(Path.of("src/test/resources/students-too-few-books.json"))
        );

        assertTrue(exception.getMessage().contains("Иван"));
    }
}
