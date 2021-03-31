package pt.ipp.isep.dei.project1.model.device.tv;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TvSpecTest {

    @Test
    void getNominalPower() {
        //Arrange
        DeviceSpecs tv = new TvSpec(100, 50);
        tv.setAttributeValue("nominal power", 30);
        //Act
        double result = tv.getAttributeValue("nominal power");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getLuminousFlux() {
        //Arrange
        DeviceSpecs tv = new TvSpec(100, 50);
        tv.setAttributeValue("standby power", 30);
        //Act
        double result = tv.getAttributeValue("standby power");
        double expectedResult = 30;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getNonExistAttribute() {
        //Arrange
        DeviceSpecs tv = new TvSpec(100, 50);
        //Act
        double result = tv.getAttributeValue("parserDate");
        double expectedResult = Double.NaN;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetAttributeNames() {

        TvSpec tvSpec1 = new TvSpec(50, 100);
        List<String> listResult = tvSpec1.getAttributeNames();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power");
        expectedResult.add("standby power");


        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfGetAttributeNamesAndValues() {
        //Arrange

        DeviceSpecs tv = new TvSpec(100, 50);
        List<String> listResult = tv.getAttributeNamesAndValues();

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("nominal power - 100.0");
        expectedResult.add("standby power - 50.0");


        assertEquals(expectedResult, listResult);
    }

    @Test
    void checkIfAddAttributeNames() {

        TvSpec tvSpec1 = new TvSpec(50, 100);


        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("nominal power");
        expectedResult.add("standby power");


        assertEquals(expectedResult, tvSpec1.getAttributeNames());
    }
}
