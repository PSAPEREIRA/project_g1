package pt.ipp.isep.dei.project1.model.device.walltowelheater;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.winecooler.WineCoolerSpec;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterSpecTest {

    @Test
    void checkIfGetAttributeNames() {

        DeviceSpecs wallTowelHeaterSpec = new WallTowelHeaterSpec(100, 50);
        List<String> listResult = wallTowelHeaterSpec.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("heater flux");

        assertEquals(listResult, expectedResult);
    }

    @Test
    void getNominalPower() {
        //Arrange
        DeviceSpecs wallTowelHeaterSpec = new WallTowelHeaterSpec(100, 50);
        wallTowelHeaterSpec.setAttributeValue("nominal power", 30);
        //Act
        double result = wallTowelHeaterSpec.getAttributeValue("nominal power");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getHeaterFlux() {
        //Arrange
        DeviceSpecs wallTowelHeaterSpec = new WallTowelHeaterSpec(100, 50);
        wallTowelHeaterSpec.setAttributeValue("heater flux", 30);
        //Act
        double result = wallTowelHeaterSpec.getAttributeValue("heater flux");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNonExistAttribute() {
        //Arrange
        DeviceSpecs wallTowelHeaterSpec = new WallTowelHeaterSpec(100, 50);
        //Act
        double result = wallTowelHeaterSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNamesAndValues() {
        //Arrange

        DeviceSpecs wallTowelHeater = new WallTowelHeaterSpec(100, 50);
        List<String> listResult = wallTowelHeater.getAttributeNamesAndValues();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("heater flux - 50.0");

        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfAddAttributeNames() {
        //Arrange

        DeviceSpecs wallTowelHeaterSpec1 = new WallTowelHeaterSpec(50,100);


        List<String> expectedResult= new ArrayList<>();

        expectedResult.add("nominal power");
        expectedResult.add("heater flux");

        assertEquals(expectedResult,wallTowelHeaterSpec1.getAttributeNames());
    }
}