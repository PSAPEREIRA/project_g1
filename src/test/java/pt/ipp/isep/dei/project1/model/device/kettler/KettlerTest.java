package pt.ipp.isep.dei.project1.model.device.kettler;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KettlerTest {


    @Test
    void testGetName() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        Kettler.setName("e2");
        String expectedResult = "e2";
        String result = Kettler.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetType() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        Kettler.setName("e2");
        String expectedResult = "Kettler";
        String result = Kettler.getDeviceType().getType();
        assertEquals(expectedResult, result);
    }


    @Test
    void testIfIsActiveFalse() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        Kettler.deactivateDevice();
        boolean result = Kettler.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        boolean result = Kettler.isActive();
        assertTrue(result);
    }


    @Test
    void checkIfDeviceIsProgrammable() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        boolean result = Kettler.checkIfIsProgrammable();
        assertFalse(result);
    }


    @Test
    void testSetReadings() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        Kettler.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = Kettler.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        Kettler.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        Kettler.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = Kettler.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetComsumption() throws Exception {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25,11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        Kettler.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = Kettler.getConsumption(date1, date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testEstimatedComsumption()  {
        Kettler kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        kettler.getDeviceSpecs().setAttributeValue("performance ratio",0.9 );
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        kettler.setListOfReadings(listOfReading);
        double expectedResult = 1675.0;
        List<Double> list = new ArrayList<>();
        list.add(20.0);
        list.add(20.0);
        list.add(20.0);
        double result = kettler.getEstimatedConsumption(list);
        assertEquals(expectedResult, Math.round(result));

    }

    @Test
    void testGetDataSeries() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        Kettler.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        Kettler.setListOfReadings(listOfReading);
        ListOfReadings result = Kettler.getDataSeries(date1, date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testCheckIfIsProgrammable() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        boolean result = Kettler.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsMetered() {
        Kettler Kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        boolean result = Kettler.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testDeactivateFalse() {
        Kettler kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        kettler.deactivateDevice();
        boolean result = kettler.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        Kettler kettler = new Kettler("ele1", new KettleSpec(), new KettlerType());
        boolean result = kettler.deactivateDevice();
        assertTrue(result);
    }

}

