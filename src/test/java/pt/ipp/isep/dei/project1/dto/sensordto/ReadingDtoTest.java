package pt.ipp.isep.dei.project1.dto.sensordto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadingDtoTest {

    @Test
    void testToString (){
        ReadingDto readingDto = new ReadingDto();
        String expectedResult = readingDto.getIdOfSensor() +"\n"+ readingDto.getDateTime() +"\n"+ readingDto.getValue() +"\n"+ readingDto.getUnit() +"\n"+readingDto.getStatus();

        String result = readingDto.toString();

        assertEquals(expectedResult,result);
    }
}