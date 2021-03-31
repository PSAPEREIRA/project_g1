package pt.ipp.isep.dei.project1.model.device.winecooler;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WineCoolerTest {


    @Test
    void testGetName() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        WineCooler.setName("e2");
        String expectedResult = "e2";
        String result = WineCooler.getName();
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetType() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        WineCooler.setName("e2");
        String expectedResult = "Wine cooler";
        String result = WineCooler.getDeviceType().getType();
        assertEquals(expectedResult, result);
    }


    @Test
    void testIfIsActiveFalse() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        WineCooler.deactivateDevice();
        boolean result = WineCooler.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        boolean result = WineCooler.isActive();
        assertTrue(result);
    }


    @Test
    void checkIfDeviceIsProgrammable() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        boolean result = WineCooler.checkIfIsProgrammable();
        assertFalse(result);
    }


    @Test
    void testSetReadings() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        WineCooler.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = WineCooler.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        WineCooler.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        WineCooler.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = WineCooler.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetComsumption() throws Exception {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25,11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        WineCooler.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = WineCooler.getConsumption(date1, date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testEstimatedComsumption()  {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        WineCooler.getDeviceSpecs().setAttributeValue("annual energy consumption",365 );
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        WineCooler.setListOfReadings(listOfReading);
        double expectedResult = 1.0;
        List<Double> list = new ArrayList<>();
        list.add(20.0);
        list.add(20.0);
        list.add(20.0);
        double result = WineCooler.getEstimatedConsumption();
        assertEquals(expectedResult, Math.round(result));

    }

    @Test
    void testGetDataSeries() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        WineCooler.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        WineCooler.setListOfReadings(listOfReading);
        ListOfReadings result = WineCooler.getDataSeries(date1, date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testCheckIfIsProgrammable() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        boolean result = WineCooler.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsMetered() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        boolean result = WineCooler.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }


    @Test
    void testDeactivateFalse() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        WineCooler.deactivateDevice();
        boolean result = WineCooler.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        WineCooler WineCooler = new WineCooler("ele1", new WineCoolerSpec(), new WineCoolerType());
        boolean result = WineCooler.deactivateDevice();
        assertTrue(result);
    }


}