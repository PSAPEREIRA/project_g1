package pt.ipp.isep.dei.project1.model.device.lamp;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LampTest {

    @Test
    void testGetName() {
        Lamp lamp = new Lamp("lamp1",new LampSpec(), new LampType());
        lamp.setName("l2");
        String expectedResult = "l2";
        String result = lamp.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void testIfIsActiveFalse() {
        Lamp lamp = new Lamp("lamp1",new LampSpec(), new LampType());
        lamp.deactivateDevice();
        boolean result = lamp.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        Lamp lamp = new Lamp("lamp1",new LampSpec(), new LampType());
        boolean result = lamp.isActive();
        assertTrue(result);
    }

    @Test
    void checkIfDeviceIsProgrammable() {

        Lamp lamp = new Lamp("lamp1",new LampSpec(), new LampType());

        boolean result = lamp.checkIfIsProgrammable();

        assertFalse(result);
    }


    @Test
    void testCheckIfIsMetered() {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        boolean result = lamp.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsProgrammable() {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        boolean result = lamp.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetType() {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        String result = lamp.getDeviceType().getType();
        String expectedResult = "Lamp";
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        lamp.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        lamp.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = lamp.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        lamp.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        lamp.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = lamp.getConsumption(date1,date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetReadings() {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        lamp.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = lamp.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetDataSeries() {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        lamp.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        lamp.setListOfReadings(listOfReading);
        ListOfReadings result = lamp.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testDeactivateFalse() {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        lamp.deactivateDevice();
        boolean result = lamp.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        Lamp lamp = new Lamp("ele1", new LampSpec(), new LampType());
        boolean result = lamp.deactivateDevice();
        assertTrue(result);
    }


}