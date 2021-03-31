package pt.ipp.isep.dei.project1.readers.readercsv;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.readers.xml.AreaSensorsFromXml;

import static org.junit.jupiter.api.Assertions.*;

class ReadingFromCSVTest {

    @Test
    void testToString() {
        //Arrange
        ReadingFromCSV readingFromCSV = new ReadingFromCSV();
        //Act
        String result = readingFromCSV.toString();
        //Assert
        assertEquals(readingFromCSV.toString(), result);
    }
}