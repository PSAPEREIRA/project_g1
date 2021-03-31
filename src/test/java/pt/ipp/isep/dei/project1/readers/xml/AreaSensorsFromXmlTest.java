package pt.ipp.isep.dei.project1.readers.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaSensorsFromXmlTest {

    @Test
    void testToString() {
        //Arrange
        AreaSensorsFromXml areaSensorsFromXml = new AreaSensorsFromXml();
        //Act
        String result = areaSensorsFromXml.toString();
        //Assert
        assertEquals(areaSensorsFromXml.toString(),result);
    }


}