package pt.ipp.isep.dei.project1.model.device.freezer;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FreezerTest {


    @Test
    void testGetName() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        freezer.setName("f2");
        String expectedResult = "f2";
        String result = freezer.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void testDeactivateFalse() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        freezer.deactivateDevice();
        boolean result = freezer.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        boolean result = freezer.deactivateDevice();
        assertTrue(result);
    }


    @Test
    void testIfIsActiveTrue() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        boolean result = freezer.isActive();
        assertTrue(result);
    }



    @Test
    void checkIfDeviceIsProgrammable() {

        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());

        boolean result = freezer.checkIfIsProgrammable();

        assertFalse(result);
    }


    @Test
    void testCheckIfIsProgrammable() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        boolean result = freezer.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsMetered() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        boolean result = freezer.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetType() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        String result = freezer.getDeviceType().getType();
        String expectedResult = "Freezer";
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        freezer.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        freezer.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = freezer.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        freezer.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        freezer.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = freezer.getConsumption(date1.minusMinutes(1),date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDataSeries() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        freezer.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        freezer.setListOfReadings(listOfReading);
        ListOfReadings result = freezer.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testSetReadings() {
        Freezer freezer = new Freezer("freezer",new FreezerSpec(), new FreezerType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        freezer.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = freezer.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }




}