package pt.ipp.isep.dei.project1.model.device.eletricwaterheater;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterSpec;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElectricWaterHeaterSpecTest {


    @Test
    void getNominalPower() {
        //Arrange
        ElectricWaterHeaterSpec electricWaterHeaterSpec = new ElectricWaterHeaterSpec();
        electricWaterHeaterSpec.setAttributeValue("nominal power",100);
        electricWaterHeaterSpec.setAttributeValue("volume of water", 50);
        electricWaterHeaterSpec.setAttributeValue("hot water temperature", 50);
        electricWaterHeaterSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = electricWaterHeaterSpec.getAttributeValue("nominal power");
        double expectedResult = 100;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getVolumeOfWater() {
        //Arrange
        ElectricWaterHeaterSpec electricWaterHeaterSpec = new ElectricWaterHeaterSpec();
        electricWaterHeaterSpec.setAttributeValue("nominal power",100);
        electricWaterHeaterSpec.setAttributeValue("volume of water", 50);
        electricWaterHeaterSpec.setAttributeValue("hot water temperature", 50);
        electricWaterHeaterSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = electricWaterHeaterSpec.getAttributeValue("volume of water");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getHotWaterTemperature() {
        //Arrange
        ElectricWaterHeaterSpec electricWaterHeaterSpec = new ElectricWaterHeaterSpec();
        electricWaterHeaterSpec.setAttributeValue("nominal power",100);
        electricWaterHeaterSpec.setAttributeValue("volume of water", 50);
        electricWaterHeaterSpec.setAttributeValue("hot water temperature", 50);
        electricWaterHeaterSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = electricWaterHeaterSpec.getAttributeValue("hot water temperature");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult,result);
    }




    @Test
    void getPerfomanceRatio() {
        //Arrange
        ElectricWaterHeaterSpec electricWaterHeaterSpec = new ElectricWaterHeaterSpec();
        electricWaterHeaterSpec.setAttributeValue("nominal power",100);
        electricWaterHeaterSpec.setAttributeValue("volume of water", 50);
        electricWaterHeaterSpec.setAttributeValue("hot water temperature", 50);
        electricWaterHeaterSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = electricWaterHeaterSpec.getAttributeValue("performance ratio");
        double expectedResult = 0.9;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getNonExistAttribute() {
        //Arrange
        ElectricWaterHeaterSpec electricWaterHeaterSpec = new ElectricWaterHeaterSpec();
        electricWaterHeaterSpec.setAttributeValue("nominal power",100);
        electricWaterHeaterSpec.setAttributeValue("volume of water", 50);
        electricWaterHeaterSpec.setAttributeValue("hot water temperature", 50);
        electricWaterHeaterSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = electricWaterHeaterSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfGetAttributeNames(){

        DeviceSpecs eletricWaterHeater = new ElectricWaterHeaterSpec(100,50,50,0.9);
        List<String> listResult = eletricWaterHeater.getAttributeNames();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("volume of water");
        expectedResult.add("hot water temperature");
        expectedResult.add("performance ratio");

        assertEquals(listResult,expectedResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues(){
        //Arrange

        DeviceSpecs EHW1 = new ElectricWaterHeaterSpec(100, 80,90,0.9);
        List<String> listResult = EHW1.getAttributeNamesAndValues();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("volume of water - 80.0");
        expectedResult.add("hot water temperature - 90.0");
        expectedResult.add("performance ratio - 0.9");

        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfAddAttributeNames(){

        DeviceSpecs eWh = new ElectricWaterHeaterSpec(100,50,50,0.9);


        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("nominal power");
        expectedResult.add("volume of water");
        expectedResult.add("hot water temperature");
        expectedResult.add("performance ratio");


        assertEquals(expectedResult,eWh.getAttributeNames());
    }
}