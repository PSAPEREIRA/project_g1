package pt.ipp.isep.dei.project1.readers.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadingFromXmlTest {

    @Test
    void testToString() {
        //Arrange
        ReadingFromXml readingFromXml = new ReadingFromXml();
        //Act
        String expectedResult = readingFromXml.getIdOfSensor() + "\n" + readingFromXml.getDateTime() + "\n" + readingFromXml.getValue() + "\n" + readingFromXml.getUnit() + "\n";
        String result = readingFromXml.toString();
        //Assert
        assertEquals(expectedResult,result);
    }

}