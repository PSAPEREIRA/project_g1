package pt.ipp.isep.dei.project1.model.device.eletricwaterheater;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ElectricWaterHeaterTest {


    @Test
    void testGetName() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        electricWaterHeater.setName("e2");
        String expectedResult = "e2";
        String result = electricWaterHeater.getName();
        assertEquals(expectedResult, result);
    }


    @Test
    void testIfIsActiveFalse() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        electricWaterHeater.deactivateDevice();
        boolean result = electricWaterHeater.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        boolean result = electricWaterHeater.isActive();
        assertTrue(result);
    }


    @Test
    void checkIfDeviceIsProgrammable() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        boolean result = electricWaterHeater.checkIfIsProgrammable();
        assertFalse(result);
    }


    @Test
    void testSetReadings() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        electricWaterHeater.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = electricWaterHeater.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        electricWaterHeater.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        electricWaterHeater.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = electricWaterHeater.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
    ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        electricWaterHeater.getDeviceSpecs().setAttributeValue("nominal power", 20);
    LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
    LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
    LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
    ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        electricWaterHeater.setListOfReadings(listOfReading);
    double expectedResult = 25.5;
    double result = electricWaterHeater.getConsumption(date1,date3);
    assertEquals(expectedResult, result);
}

    @Test
    void testGetDataSeries() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        electricWaterHeater.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        electricWaterHeater.setListOfReadings(listOfReading);
        ListOfReadings result = electricWaterHeater.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testCheckIfIsProgrammable() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        boolean result = electricWaterHeater.checkIfIsProgrammable();
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsMetered() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        boolean result = electricWaterHeater.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testDeactivateFalse() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        electricWaterHeater.deactivateDevice();
        boolean result = electricWaterHeater.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater("ele1", new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        boolean result = electricWaterHeater.deactivateDevice();
        assertTrue(result);
    }




}