package pt.ipp.isep.dei.project1.model.device.fan;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.stove.StoveSpec;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FanSpecTest {

    @Test
    void getNominalPowerOfFan() {
        //Arrange
        FanSpec fanSpec = new FanSpec();
        fanSpec.setAttributeValue("nominal power", 50);
        //Act
        double result = fanSpec.getAttributeValue("nominal power");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNonExistAttribute() {
        //Arrange
        FanSpec fanSpec = new FanSpec(50);
        //Act
        double result = fanSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getEnergyConsumptionOfProgram() {
        //Arrange
        FanSpec fanSpec = new FanSpec(50);
        fanSpec.setAttributeValue("nominal power of program", 50);
        //Act
        double result = fanSpec.getAttributeValue("nominal power of program");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNames() {
        //Arrange
        FanSpec fanSpec = new FanSpec();
        List<String> listResult = fanSpec.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues() {
        //Arrange
        FanSpec fanSpec = new FanSpec();
        fanSpec.setAttributeValue("nominal power" ,100);
        List<String> listResult = fanSpec.getAttributeNamesAndValues();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power - 100.0");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfAddAttributeNames() {
        //Arrange
        FanSpec fanSpec = new FanSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        assertEquals(expectedResult, fanSpec.getAttributeNames());
    }

    @Test
    void checkIfAddAttributeNames2() {
        //Arrange
        FanSpec fanSpec = new FanSpec(100);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        assertEquals(expectedResult, fanSpec.getAttributeNames());
    }
}
