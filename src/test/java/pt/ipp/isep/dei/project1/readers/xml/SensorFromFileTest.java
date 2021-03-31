package pt.ipp.isep.dei.project1.readers.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorFromFileTest {

    @Test
    void testToString() {
        //Arrange
        SensorFromFile sensorFromFile = new SensorFromFile();
        //Act
        String expectedResult = sensorFromFile.getId() +"\n"+sensorFromFile.getName() +"\n"+ sensorFromFile.getStartDate() +"\n"+sensorFromFile.getType()+"\n"+sensorFromFile.getUnits()+"\n" + sensorFromFile.getLocation()+"\n";
        String result = sensorFromFile.toString();
        //Assert
        assertEquals(expectedResult,result);
    }


}