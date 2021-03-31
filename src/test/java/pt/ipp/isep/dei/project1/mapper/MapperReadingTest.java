package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperReadingTest {

    @Test
    void testConstructor() {
        try {
            MapperReading mapperReading = new MapperReading();
        } catch (IllegalStateException e) {
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }

}