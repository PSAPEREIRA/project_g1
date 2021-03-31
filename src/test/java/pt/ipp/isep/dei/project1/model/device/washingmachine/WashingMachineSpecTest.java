package pt.ipp.isep.dei.project1.model.device.washingmachine;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachineSpec;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineSpecTest {

    @Test
    void getNominalPower() {
        //Arrange

        DeviceSpecs washingMachine = new WashingMachineSpec();
        washingMachine.setAttributeValue("nominal power", 50);
        //Act
        double result = washingMachine.getAttributeValue("nominal power");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getCapacity() {
        //Arrange
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("capacity", 30);
        //Act
        double result = washingMachineSpec.getAttributeValue("capacity");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getCapacityNominalPower2() {
        //Arrange
        WashingMachineSpec washingMachineSpec = new WashingMachineSpec(30,30);
        washingMachineSpec.setAttributeValue("capacity", 30);
        //Act
        double result = washingMachineSpec.getAttributeValue("capacity");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getNonExistAttribute() {
        //Arrange

        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        //Act
        double result = washingMachineSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNames() {
        //Arrange

        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        List<String> listResult = washingMachineSpec.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("capacity");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues() {
        //Arrange

        WashingMachineSpec washingMachineSpec = new WashingMachineSpec();
        washingMachineSpec.setAttributeValue("nominal power" ,100);
        washingMachineSpec.setAttributeValue("capacity" ,80);
        List<String> listResult = washingMachineSpec.getAttributeNamesAndValues();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("capacity - 80.0");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfAddAttributeNames() {
        //Arrange

        WashingMachineSpec wash = new WashingMachineSpec();


        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("capacity");

        assertEquals(expectedResult, wash.getAttributeNames());
    }

}