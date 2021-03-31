package pt.ipp.isep.dei.project1.model.device.fridge;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FridgeTest {


    @Test
    void testGetName() {
        Fridge fridge = new Fridge("frid",new FridgeSpec(), new FridgeType());
        fridge.setName("f2");
        String expectedResult = "f2";
        String result = fridge.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void testIfIsActiveFalse() {
        Fridge fridge = new Fridge("frid",new FridgeSpec(), new FridgeType());
        fridge.deactivateDevice();
        boolean result = fridge.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        Fridge fridge = new Fridge("frid",new FridgeSpec(), new FridgeType());
        boolean result = fridge.isActive();
        assertTrue(result);
    }



    @Test
    void checkIfDeviceIsProgrammable() {

        Fridge fridge = new Fridge("frid",new FridgeSpec(), new FridgeType());

        boolean result = fridge.checkIfIsProgrammable();

        assertFalse(result);
    }


    @Test
    void testCheckIfIsProgrammable() {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        boolean result = fridge.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsMetered() {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        boolean result = fridge.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetType() {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        String result = fridge.getDeviceType().getType();
        String expectedResult = "Fridge";
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        fridge.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        fridge.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = fridge.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        fridge.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        fridge.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = fridge.getConsumption(date1,date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDataSeries() {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        fridge.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        fridge.setListOfReadings(listOfReading);
        ListOfReadings result = fridge.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testSetReadings() {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        fridge.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = fridge.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }


    @Test
    void testDeactivateFalse() {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        fridge.deactivateDevice();
        boolean result = fridge.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        Fridge fridge = new Fridge("ele1", new FridgeSpec(), new FridgeType());
        boolean result = fridge.deactivateDevice();
        assertTrue(result);
    }



}