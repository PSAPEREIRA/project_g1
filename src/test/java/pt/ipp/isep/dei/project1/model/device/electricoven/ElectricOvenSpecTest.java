package pt.ipp.isep.dei.project1.model.device.electricoven;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElectricOvenSpecTest {

    @Test
    void getNominalPower() {
        //Arrange
        DeviceSpecs ElectricOven = new ElectricOvenSpec();
        ElectricOven.setAttributeValue("nominal power",30);
        //Act
        double result = ElectricOven.getAttributeValue("nominal power");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getLuminousFlux() {
        //Arrange
        DeviceSpecs ElectricOven = new ElectricOvenSpec();
        ElectricOven.setAttributeValue("nominal power",30);
        //Act
        double result = ElectricOven.getAttributeValue("nominal power");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getNonExistAttribute() {
        //Arrange
        DeviceSpecs ElectricOven = new ElectricOvenSpec(50);
        //Act
        double result = ElectricOven.getAttributeValue("powertest");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfGetAttributeNames(){

        ElectricOvenSpec ElectricOvenSpec1 = new ElectricOvenSpec( 100);
        List<String> listResult = ElectricOvenSpec1.getAttributeNames();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power");


        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues(){
        //Arrange

        DeviceSpecs ElectricOven = new ElectricOvenSpec(100);
        List<String> listResult = ElectricOven.getAttributeNamesAndValues();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power - 100.0");


        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfAddAttributeNames(){

        ElectricOvenSpec ElectricOvenSpec1 = new ElectricOvenSpec(100);


        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("nominal power");

        assertEquals(expectedResult,ElectricOvenSpec1.getAttributeNames());
    }

    @Test
    void setAddAttributeNames(){

        ElectricOvenSpec ElectricOvenSpec1 = new ElectricOvenSpec(100);

        ElectricOvenSpec1.setAttributeValue("nominal power", 20);

        assertEquals(20,ElectricOvenSpec1.getAttributeValue("nominal power"));
    }

    @Test
    void setAddAttributeNamesEmpty(){

        ElectricOvenSpec ElectricOvenSpec1 = new ElectricOvenSpec(100);

        ElectricOvenSpec1.setAttributeValue("nominal", 20);

        assertEquals(100,ElectricOvenSpec1.getAttributeValue("nominal power"));
    }

}