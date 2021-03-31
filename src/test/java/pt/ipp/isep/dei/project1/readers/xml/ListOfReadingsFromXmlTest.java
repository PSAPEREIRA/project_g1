package pt.ipp.isep.dei.project1.readers.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListOfReadingsFromXmlTest {

    @Test
    void testToString() {
        //Arrange
        ListOfReadingsFromXml listOfReadingsFromXml = new ListOfReadingsFromXml();
        //Act
        String result = listOfReadingsFromXml.toString();
        //Assert
        assertEquals(listOfReadingsFromXml.toString(),result);
    }


}