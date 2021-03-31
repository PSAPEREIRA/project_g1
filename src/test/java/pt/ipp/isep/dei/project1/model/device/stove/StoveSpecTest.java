package pt.ipp.isep.dei.project1.model.device.stove;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoveSpecTest {

    @Test
    void getNominalPowerOfStove() {
        //Arrange
        StoveSpec stove = new StoveSpec();
        stove.setAttributeValue("nominal power", 50);
        //Act
        double result = stove.getAttributeValue("nominal power");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNonExistAttribute() {
        //Arrange
        StoveSpec stoveSpec = new StoveSpec(50);
        //Act
        double result = stoveSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getEnergyConsumptionOfProgram() {
        //Arrange
        StoveSpec stoveSpec = new StoveSpec(50);
        stoveSpec.setAttributeValue("nominal power of program", 50);
        //Act
        double result = stoveSpec.getAttributeValue("nominal power of program");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNames() {
        //Arrange
        StoveSpec stoveSpec = new StoveSpec();
        List<String> listResult = stoveSpec.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues() {
        //Arrange
        StoveSpec stoveSpec = new StoveSpec();
        stoveSpec.setAttributeValue("nominal power" ,100);
        List<String> listResult = stoveSpec.getAttributeNamesAndValues();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power - 100.0");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfAddAttributeNames() {
        //Arrange
        StoveSpec stoveSpec = new StoveSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        assertEquals(expectedResult, stoveSpec.getAttributeNames());
    }

    @Test
    void checkIfAddAttributeNames2() {
        //Arrange
        StoveSpec stoveSpec = new StoveSpec(100);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        assertEquals(expectedResult, stoveSpec.getAttributeNames());
    }

    @Test
    void checkIfAddAttributeNames3() {
        //Arrange
        StoveSpec stoveSpec = new StoveSpec(100);
        stoveSpec.addAttributeNames();
        double expectedResult = 100;
        assertEquals(expectedResult, stoveSpec.getAttributeValue("nominal power"));
    }


}