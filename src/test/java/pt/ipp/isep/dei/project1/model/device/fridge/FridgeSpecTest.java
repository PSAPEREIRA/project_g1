package pt.ipp.isep.dei.project1.model.device.fridge;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FridgeSpecTest {


    @Test
    void getNominalPower() {
        //Arrange
        DeviceSpecs fridge = new FridgeSpec(100,50,50,730);
        fridge.setAttributeValue("nominal power",30);
        //Act
        double result = fridge.getAttributeValue("nominal power");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getFreezerCapacity() {
        //Arrange
        DeviceSpecs fridge = new FridgeSpec(100,50,50,730);
        fridge.setAttributeValue("freezer capacity",30);
        //Act
        double result = fridge.getAttributeValue("freezer capacity");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getRefrigeratorCapacity() {
        //Arrange
        DeviceSpecs fridge = new FridgeSpec(100,50,50,730);
        fridge.setAttributeValue("refrigerator capacity",40);
        //Act
        double result = fridge.getAttributeValue("refrigerator capacity");
        double expectedResult = 40;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getAnualEnergyConsumption() {
        //Arrange
        DeviceSpecs fridge = new FridgeSpec(100,50,50,730);
        fridge.setAttributeValue("annual energy consumption",40);
        //Act
        double result = fridge.getAttributeValue("annual energy consumption");
        double expectedResult = 40;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getNonExistAttribute() {
        //Arrange
        DeviceSpecs fridge = new FridgeSpec(100,50,50,10);
        fridge.setAttributeValue("energy consumption",40);
        //Act
        double result = fridge.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfGetAttributeNames(){

        FridgeSpec f1 = new FridgeSpec(100, 2, 3,4);
        List<String> listResult = f1.getAttributeNames();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("freezer capacity");
        expectedResult.add("refrigerator capacity");
        expectedResult.add("annual energy consumption");

        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues(){
        //Arrange

        DeviceSpecs f1 = new FridgeSpec(100,50,50,10);
        List<String> listResult = f1.getAttributeNamesAndValues();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("freezer capacity - 50.0");
        expectedResult.add("refrigerator capacity - 50.0");
        expectedResult.add("annual energy consumption - 10.0");

        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfAddAttributeNames(){

        FridgeSpec f1 = new FridgeSpec(100, 2, 3,4);

        List<String> expectedResult = new ArrayList<>();


        expectedResult.add("nominal power");
        expectedResult.add("freezer capacity");
        expectedResult.add("refrigerator capacity");
        expectedResult.add("annual energy consumption");

        assertEquals(f1.getAttributeNames(),expectedResult);
    }
}