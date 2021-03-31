package pt.ipp.isep.dei.project1.model.device.walltowelheater;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterTest {

    @Test
    void testGetType() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(), new WallTowelHeaterType());
        wallTowelHeater.setName("e2");
        String expectedResult = "WallTowelHeater";
        String result = wallTowelHeater.getDeviceType().getType();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetName() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("wallTowelHeater1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        wallTowelHeater.setName("wth2");
        String expectedResult = "wth2";
        String result = wallTowelHeater.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void testIfIsActiveFalse() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("wallTowelHeater1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        wallTowelHeater.deactivateDevice();
        boolean result = wallTowelHeater.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("wallTowelHeater1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        boolean result = wallTowelHeater.isActive();
        assertTrue(result);
    }

    @Test
    void checkIfDeviceIsProgrammable() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("wallTowelHeater1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        boolean result = wallTowelHeater.checkIfIsProgrammable();
        assertFalse(result);
    }

    @Test
    void testCheckIfIsMetered() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        boolean result = wallTowelHeater.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsProgrammable() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        boolean result = wallTowelHeater.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        wallTowelHeater.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        wallTowelHeater.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = wallTowelHeater.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        wallTowelHeater.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        wallTowelHeater.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = wallTowelHeater.getConsumption(date1,date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetReadings(){
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        wallTowelHeater.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = wallTowelHeater.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDataSeries(){
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        wallTowelHeater.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        wallTowelHeater.setListOfReadings(listOfReading);
        ListOfReadings result = wallTowelHeater.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testDeactivateFalse() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        wallTowelHeater.deactivateDevice();
        boolean result = wallTowelHeater.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        WallTowelHeater wallTowelHeater = new WallTowelHeater("ele1", new WallTowelHeaterSpec(),
                new WallTowelHeaterType());
        boolean result = wallTowelHeater.deactivateDevice();
        assertTrue(result);
    }


}