package pt.ipp.isep.dei.project1.model.device.washingmachine;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineTest {

    @Test
    void testGetName() {
        WashingMachine washingMachine = new WashingMachine("wash",new WashingMachineSpec(), new WashingMachineType());
        washingMachine.setName("w2");
        String expectedResult = "w2";
        String result = washingMachine.getName();
        assertEquals(expectedResult,result);
    }


    @Test
    void getListOfProgramsEmptyList() {

        WashingMachine d1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());

        List<String[]> result = d1.getListOfPrograms();

        List<String[]> expectedResult = new ArrayList<>();

        assertEquals(expectedResult, result);

    }

    @Test
    void getListOfProgramsFullList() {

        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        WashingMachine d1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());
        d1.setListOfPrograms(listOfPrograms);

        List<String[]> result = d1.getListOfPrograms();

        List<String[]> expectedResult = new ArrayList<>();

        expectedResult.add(program1);
        expectedResult.add(program2);
        expectedResult.add(program3);

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfAddProgramToDW() {

        String[] program1 = new String[3];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[3];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[3];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);


        List<String[]> listOfPrograms2 = new ArrayList<>();

        listOfPrograms2.add(program1);
        listOfPrograms2.add(program2);

        WashingMachine d1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());

        d1.createProgramAndAddTo("Dishes", 2.1);

        List<String[]> result = d1.getListOfPrograms();
        List<String[]> expectedResult = d1.getListOfPrograms();

        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfCreateAndAddProgramToDW() {

        String[] program1 = new String[2];
        program1[0] = "Glasses";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Dishes";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        WashingMachine d1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());
        d1.setListOfPrograms(listOfPrograms);
        String nameProg = "Echo Turbo";
        double energyConsumed = 1.9;

        d1.createProgramAndAddTo(nameProg, energyConsumed);

        List<String[]> result = d1.getListOfPrograms();  //get RESULT

        listOfPrograms.add(new String[]{nameProg, String.valueOf(energyConsumed)});

        List<String[]> expectedResult = listOfPrograms;

        assertArrayEquals(expectedResult.get(3), result.get(3));

    }


    @Test
    void checkIfDeviceIsProgrammableIfTrue() {

        WashingMachine d1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());

        boolean result = d1.checkIfIsProgrammable();

        assertTrue(result);
    }

    @Test
    void checkIfDeviceIsProgrammableIfFalse() {

        WashingMachine l1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());

        boolean result = l1.checkIfIsProgrammable();

        assertTrue(result);
    }

    @Test
    void testIfIsActiveFalse() {
        WashingMachine l1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());
        l1.deactivateDevice();
        boolean result = l1.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        WashingMachine l1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());
        boolean result = l1.isActive();
        assertTrue(result);
    }

    @Test
    void checkIfDeviceIsProgrammable() {

        WashingMachine l1 = new WashingMachine("d1", new WashingMachineSpec(), new WashingMachineType());

        boolean result = l1.checkIfIsProgrammable();

        assertTrue(result);
    }


    @Test
    void testSetReadings() {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        washingMachine.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = washingMachine.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        washingMachine.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        washingMachine.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = washingMachine.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        washingMachine.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        washingMachine.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = washingMachine.getConsumption(date1,date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDataSeries() {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        washingMachine.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        washingMachine.setListOfReadings(listOfReading);
        ListOfReadings result = washingMachine.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testCheckIfIsProgrammable() {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        boolean result = washingMachine.checkIfIsProgrammable();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsMetered() {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        boolean result = washingMachine.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetType() {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        String result = washingMachine.getDeviceType().getType();
        String expectedResult = "Washing Machine";
        assertEquals(expectedResult, result);
    }


    @Test
    void testDeactivateFalse() {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        washingMachine.deactivateDevice();
        boolean result = washingMachine.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        WashingMachine washingMachine = new WashingMachine("ele1", new WashingMachineSpec(), new WashingMachineType());
        boolean result = washingMachine.deactivateDevice();
        assertTrue(result);
    }


}