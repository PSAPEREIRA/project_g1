package pt.ipp.isep.dei.project1.model.device.freezer;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FreezerSpecTest {

    @Test
    void getNominalPower() {
        //Arrange
        DeviceSpecs fridge = new FreezerSpec(100,50,730);
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
        DeviceSpecs fridge = new FreezerSpec(100,50,730);
        fridge.setAttributeValue("freezer capacity",30);
        //Act
        double result = fridge.getAttributeValue("freezer capacity");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getAnualEnergyConsumption() {
        //Arrange
        DeviceSpecs fridge = new FreezerSpec(100,50,730);
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
        DeviceSpecs fridge = new FreezerSpec(100,50,730);
        fridge.setAttributeValue("energy consumption",40);
        //Act
        double result = fridge.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfGetAttributeNames(){

        FreezerSpec f1 = new FreezerSpec(100, 2,4);
        List<String> listResult = f1.getAttributeNames();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("freezer capacity");
        expectedResult.add("annual energy consumption");

        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues(){
        //Arrange

        FreezerSpec f1 = new FreezerSpec(100, 2,4);
        List<String> listResult = f1.getAttributeNamesAndValues();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("freezer capacity - 2.0");
        expectedResult.add("annual energy consumption - 4.0");

        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfAddAttributeNames(){

        FreezerSpec f1 = new FreezerSpec(100, 2,4);


        List<String> expectedResult = new ArrayList<>();


        expectedResult.add("nominal power");
        expectedResult.add("freezer capacity");
        expectedResult.add("annual energy consumption");

        assertEquals(f1.getAttributeNames(),expectedResult);
    }

}