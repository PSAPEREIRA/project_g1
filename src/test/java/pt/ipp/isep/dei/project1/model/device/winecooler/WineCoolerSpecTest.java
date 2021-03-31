package pt.ipp.isep.dei.project1.model.device.winecooler;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WineCoolerSpecTest {

    @Test
    void getNominalPower() {
        //Arrange
        WineCoolerSpec WineCoolerSpec = new WineCoolerSpec();
        WineCoolerSpec.setAttributeValue("nominal power", 100);
        WineCoolerSpec.setAttributeValue("number of bottles", 50);
        WineCoolerSpec.setAttributeValue("annual energy consumption", 0.9);
        //Act
        double result = WineCoolerSpec.getAttributeValue("nominal power");
        double expectedResult = 100;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getVolumeOfWater() {
        //Arrange
        WineCoolerSpec WineCoolerSpec = new WineCoolerSpec();
        WineCoolerSpec.setAttributeValue("nominal power", 100);
        WineCoolerSpec.setAttributeValue("number of bottles", 50);
        WineCoolerSpec.setAttributeValue("annual energy consumption", 0.9);
        //Act
        double result = WineCoolerSpec.getAttributeValue("number of bottles");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getPerfomanceRatio() {
        //Arrange
        WineCoolerSpec WineCoolerSpec = new WineCoolerSpec();
        WineCoolerSpec.setAttributeValue("nominal power", 100);
        WineCoolerSpec.setAttributeValue("number of bottles", 50);
        WineCoolerSpec.setAttributeValue("annual energy consumption", 500);
        //Act
        double result = WineCoolerSpec.getAttributeValue("annual energy consumption");
        double expectedResult = 500;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getNonExistAttribute() {
        //Arrange
        WineCoolerSpec WineCoolerSpec = new WineCoolerSpec();
        WineCoolerSpec.setAttributeValue("nominal power", 100);
        WineCoolerSpec.setAttributeValue("number of bottles", 50);
        WineCoolerSpec.setAttributeValue("annual energy consumption", 500);
        //Act
        double result = WineCoolerSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNames() {

        DeviceSpecs eletricWaterHeater = new WineCoolerSpec(100, 50, 0.9);
        List<String> listResult = eletricWaterHeater.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("number of bottles");
        expectedResult.add("annual energy consumption");

        assertEquals(listResult, expectedResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues() {
        //Arrange

        DeviceSpecs EHW1 = new WineCoolerSpec(100, 80, 0.9);
        List<String> listResult = EHW1.getAttributeNamesAndValues();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("number of bottles - 80.0");
        expectedResult.add("annual energy consumption - 0.9");

        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfAddAttributeNames() {

        DeviceSpecs eWh = new WineCoolerSpec(100, 50, 0.9);

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("nominal power");
        expectedResult.add("number of bottles");
        expectedResult.add("annual energy consumption");


        assertEquals(eWh.getAttributeNames(), expectedResult);
    }

}