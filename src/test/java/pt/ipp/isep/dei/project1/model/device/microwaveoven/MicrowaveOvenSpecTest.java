package pt.ipp.isep.dei.project1.model.device.microwaveoven;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.stove.StoveSpec;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenSpecTest {

    @Test
    void getNominalPowerOfMicrowaveOven() {
        //Arrange
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        microwaveOvenSpec.setAttributeValue("nominal power", 50);
        //Act
        double result = microwaveOvenSpec.getAttributeValue("nominal power");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNonExistAttribute() {
        //Arrange
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec(50);
        //Act
        double result = microwaveOvenSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNames() {
        //Arrange
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        List<String> listResult = microwaveOvenSpec.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void getEnergyConsumptionOfProgram() {
        //Arrange
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        microwaveOvenSpec.setAttributeValue("nominal power of program", 50);
        //Act
        double result = microwaveOvenSpec.getAttributeValue("nominal power of program");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }



    @Test
    void checkIfGetAttributeNamesAndValues() {
        //Arrange
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        microwaveOvenSpec.setAttributeValue("nominal power" ,100);
        List<String> listResult = microwaveOvenSpec.getAttributeNamesAndValues();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power - 100.0");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfAddAttributeNames() {
        //Arrange
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        assertEquals(expectedResult, microwaveOvenSpec.getAttributeNames());
    }

    @Test
    void checkIfAddAttributeNames2() {
        //Arrange
        MicrowaveOvenSpec microwaveOvenSpec = new MicrowaveOvenSpec(100);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        assertEquals(expectedResult, microwaveOvenSpec.getAttributeNames());
    }
}
