package pt.ipp.isep.dei.project1.dto.sensordto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadingForResponseEntityTest {

    @Test
    void getValue() {
        ReadingForResponseEntity readingForResponseEntity = new ReadingForResponseEntity();
        readingForResponseEntity.setValue(20);
        double result = readingForResponseEntity.getValue();
        assertEquals(20,result);
    }
}