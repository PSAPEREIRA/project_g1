package pt.ipp.isep.dei.project1.model.device.kettler;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KettlerSpecTest {

    @Test
    void getNominalPower() {
        //Arrange
        KettleSpec kettlerSpec = new KettleSpec();
        kettlerSpec.setAttributeValue("nominal power",100);
        kettlerSpec.setAttributeValue("number of bottles", 50);
        kettlerSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = kettlerSpec.getAttributeValue("nominal power");
        double expectedResult = 100;
        //Assert
        assertEquals(expectedResult,result);
    }


    @Test
    void getVolumeOfWater() {
        //Arrange
        KettleSpec kettlerSpec = new KettleSpec();
        kettlerSpec.setAttributeValue("nominal power",100);
        kettlerSpec.setAttributeValue("volume of water", 50);
        kettlerSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = kettlerSpec.getAttributeValue("volume of water");
        double expectedResult = 50;
        //Assert
        assertEquals(expectedResult,result);
    }




    @Test
    void getPerfomanceRatio() {
        //Arrange
        KettleSpec kettlerSpec = new KettleSpec();
        kettlerSpec.setAttributeValue("nominal power",100);
        kettlerSpec.setAttributeValue("volume of water", 50);
        kettlerSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = kettlerSpec.getAttributeValue("performance ratio");
        double expectedResult = 0.9;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void getNonExistAttribute() {
        //Arrange
        KettleSpec kettlerSpec = new KettleSpec();
        kettlerSpec.setAttributeValue("nominal power",100);
        kettlerSpec.setAttributeValue("volume of water", 50);
        kettlerSpec.setAttributeValue("performance ratio", 0.9);
        //Act
        double result = kettlerSpec.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult,result);
    }

    @Test
    void checkIfGetAttributeNames(){

        DeviceSpecs eletricWaterHeater = new KettleSpec(100,50,0.9);
        List<String> listResult = eletricWaterHeater.getAttributeNames();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("volume of water");
        expectedResult.add("performance ratio");

        assertEquals(listResult,expectedResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues(){
        //Arrange

        DeviceSpecs EHW1 = new KettleSpec(100, 80,0.9);
        List<String> listResult = EHW1.getAttributeNamesAndValues();

        List<String> expectedResult= new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("volume of water - 80.0");
        expectedResult.add("performance ratio - 0.9");

        assertEquals(expectedResult,listResult);
    }

    @Test
    void checkIfAddAttributeNames(){

        DeviceSpecs eWh = new KettleSpec(100,50,0.9);


        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("nominal power");
        expectedResult.add("volume of water");
        expectedResult.add("performance ratio");

        assertEquals(eWh.getAttributeNames(),expectedResult);
    }

}