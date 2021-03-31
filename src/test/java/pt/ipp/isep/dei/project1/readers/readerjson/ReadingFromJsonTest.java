package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadingFromJsonTest {

    @Test
    void testToString() {
        //Arrange
        ReadingFromJson readingFromJson = new ReadingFromJson();
        //Act
        String expectedResult = readingFromJson.getDateTime() +"\n"+readingFromJson.getValue() +"\n";
        String result = readingFromJson.toString();
        //Assert
        assertEquals(expectedResult,result);
    }
}