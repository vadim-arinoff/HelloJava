package ru.aston.moodle2;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void equalBooksHaveEqualHashCodesAndDistinctRemovesDuplicate() {
        Book first = new Book("Clean Code", "Robert Martin", 2008, 464);
        Book duplicate = new Book("Clean Code", "Robert Martin", 2008, 464);

        assertEquals(first, duplicate);
        assertEquals(first.hashCode(), duplicate.hashCode());
        assertEquals(1, Stream.of(first, duplicate).distinct().count());
    }
}
