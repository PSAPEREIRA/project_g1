package pt.ipp.isep.dei.project1.model.device.dishwasher;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import pt.ipp.isep.dei.project1.model.interfaces.Programmable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherSpecTest {

    @Test
    void getNominalPower() {
        //Arrange
        DeviceSpecs dishwasher = new DishwasherSpec();
        dishwasher.setAttributeValue("nominal power", 50);
        //Act
        double result = dishwasher.getAttributeValue("nominal power");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getCapacity() {
        //Arrange

        DeviceSpecs dishwasher = new DishwasherSpec();
        dishwasher.setAttributeValue("capacity", 30);
        //Act
        double result = dishwasher.getAttributeValue("capacity");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getEnergyConsumptionOfProgram() {
        //Arrange
        DeviceSpecs dishwasher = new DishwasherSpec();
        dishwasher.setAttributeValue("energy consumption of program", 40);
        //Act
        double result = dishwasher.getAttributeValue("energy consumption of program");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getNonExistAttribute() {
        //Arrange

        DeviceSpecs dishwasher = new DishwasherSpec();
        dishwasher.setAttributeValue("energy consumption", 40);
        //Act
        double result = dishwasher.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNamesAndValues() {

        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue("nominal power" ,100.0);
        dishwasherSpec.setAttributeValue("capacity" ,80.0);

        Dishwasher d1 = new Dishwasher("dish",dishwasherSpec,new DishwasherType());

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("Nominal Power - 100.0");
        expectedResult.add("Capacity - 80.0");

        List<String> result = d1.getDeviceSpecs().getAttributeNamesAndValues();

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNames() {


        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue("Nominal Power" ,100);
        dishwasherSpec.setAttributeValue("Capacity" ,80);

        List<String> listResult = dishwasherSpec.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("capacity");

        assertEquals(listResult, expectedResult);
    }

    @Test
    void checkIfAddAttributeNames() {

        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        dishwasherSpec.setAttributeValue("Nominal Power" ,100);
        dishwasherSpec.setAttributeValue("Capacity" ,80);



        List<String> expectedResult = new ArrayList<>();


        expectedResult.add("nominal power");
        expectedResult.add("capacity");

        assertEquals(expectedResult, dishwasherSpec.getAttributeNames());
    }

    @Test
    void checkIfAddAttributeNames2() {

        DishwasherSpec dishwasherSpec = new DishwasherSpec(100,20);


        List<String> expectedResult = new ArrayList<>();


        expectedResult.add("nominal power");
        expectedResult.add("capacity");

        assertEquals(expectedResult, dishwasherSpec.getAttributeNames());
    }
}