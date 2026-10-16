package ru.aston.moodle2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class StudentJsonReader {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private StudentJsonReader() {
    }

    public static List<Student> read(Path path) throws IOException {
        List<Student> students = OBJECT_MAPPER.readValue(
                path.toFile(),
                new TypeReference<List<Student>>() {
                }
        );

        for (Student student : students) {
            if (student.getBooks() == null || student.getBooks().size() < 5) {
                throw new IllegalArgumentException(
                        "У студента " + student.getName() + " должно быть минимум 5 книг"
                );
            }
        }

        return students;
    }
}
