package pt.ipp.isep.dei.project1.model.device.lamp;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LampSpecTest {

    @Test
    void getNominalPower() {
        //Arrange
        DeviceSpecs lamp = new LampSpec(100,50);
        lamp.setAttributeValue("nominal power",30);
        //Act
        double result = lamp.getAttributeValue("nominal power");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getLuminousFlux() {
        //Arrange
        DeviceSpecs lamp = new LampSpec(100,50);
        lamp.setAttributeValue("luminous flux",30);
        //Act
        double result = lamp.getAttributeValue("luminous flux");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getNonExistAttribute() {
        //Arrange
        DeviceSpecs lamp = new LampSpec(100,50);
        //Act
        double result = lamp.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfGetAttributeNames(){

        LampSpec lampSpec1 = new LampSpec(50, 100);
        List<String> listResult = lampSpec1.getAttributeNames();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("luminous flux");


        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues(){
        //Arrange

        DeviceSpecs lamp = new LampSpec(100,50);
        List<String> listResult = lamp.getAttributeNamesAndValues();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("luminous flux - 50.0");


        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfAddAttributeNames(){

        LampSpec lampSpec1 = new LampSpec(50, 100);


        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("nominal power");
        expectedResult.add("luminous flux");

        assertEquals(expectedResult,lampSpec1.getAttributeNames());
    }

}