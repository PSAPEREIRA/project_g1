package pt.ipp.isep.dei.project1.model.device.tv;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TvTest {

    @Test
    void testGetName() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        tv.setName("l2");
        String expectedResult = "l2";
        String result = tv.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void testIfIsActiveFalse() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        tv.deactivateDevice();
        boolean result = tv.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        boolean result = tv.isActive();
        assertTrue(result);
    }

    @Test
    void testIfIsActiveFalse2() {
        Tv tv = new Tv("tv1",new TvSpec(), new TvType());
        boolean result = tv.deactivateDevice();
        assertTrue(result);
    }

    @Test
    void checkIfDeviceIsProgrammable() {

        Tv tv = new Tv("tv1", new TvSpec(), new TvType());

        boolean result = tv.checkIfIsProgrammable();

        assertFalse(result);
    }


    @Test
    void testCheckIfIsMetered() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        boolean result = tv.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsProgrammable() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        boolean result = tv.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetType() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        String result = tv.getDeviceType().getType();
        String expectedResult = "Tv";
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        tv.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        tv.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = tv.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        tv.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        tv.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = tv.getConsumption(date1, date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetReadings() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        tv.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = tv.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetListOfReadings() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        tv.setListOfReadings(listOfReading);
        ListOfReadings expectedResult = listOfReading;
        ListOfReadings result = tv.getListOfReadings();
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetDataSeries() {
        Tv tv = new Tv("tv1", new TvSpec(), new TvType());
        tv.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        tv.setListOfReadings(listOfReading);
        ListOfReadings result = tv.getDataSeries(date1, date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

}
