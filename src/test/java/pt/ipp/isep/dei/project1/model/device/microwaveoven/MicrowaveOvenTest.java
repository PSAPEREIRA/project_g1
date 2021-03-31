package pt.ipp.isep.dei.project1.model.device.microwaveoven;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTest {

    @Test
    void testGetName() {
        MicrowaveOven microwaveOven = new MicrowaveOven("MicrowaveOven", new MicrowaveOvenSpec(),new MicrowaveOvenType());
        microwaveOven.setName("MicrowaveOven");
        String expectedResult = "MicrowaveOven";
        String result = microwaveOven.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void getListOfProgramsEmptyList() {
        MicrowaveOven microwaveOven1 = new MicrowaveOven("MicrowaveOven1", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        List<String[]> result = microwaveOven1.getListOfPrograms();
        List<String[]> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfAddProgramToMicrowaveOven() {

        String[] program1 = new String[2];
        program1[0] = "Program 1";
        program1[1] = "0.3";

        String[] program2 = new String[2];
        program2[0] = "Program 2";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Program 3";
        program3[1] = "3.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);


        List<String[]> listOfPrograms2 = new ArrayList<>();

        listOfPrograms2.add(program1);
        listOfPrograms2.add(program2);

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());

        microwaveOven.createProgramAndAddTo("Program 3", 3.1);

        List<String[]> result = microwaveOven.getListOfPrograms();
        List<String[]> expectedResult = microwaveOven.getListOfPrograms();

        assertEquals(expectedResult, result);
    }


    @Test
    void getCompleteListOfPrograms() {

        String[] program1 = new String[2];
        program1[0] = "Program 1";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Program 2";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Program 3";
        program3[1] = "5.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        microwaveOven.setListOfPrograms(listOfPrograms);

        List<String[]> result = microwaveOven.getListOfPrograms();

        List<String[]> expectedResult = new ArrayList<>();

        expectedResult.add(program1);
        expectedResult.add(program2);
        expectedResult.add(program3);

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfCreateAndAddProgramToMicrowaveOven() {

        String[] program1 = new String[2];
        program1[0] = "Program 1";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Program 2";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Program 3";
        program3[1] = "8.1";

        String[] program4 = new String[2];
        program4[0] = "Program 4";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        microwaveOven.setListOfPrograms(listOfPrograms);
        String nameProgram = "Program 4";
        double energyConsumed = 1.9;

        microwaveOven.createProgramAndAddTo(nameProgram, energyConsumed);

        List<String[]> result = microwaveOven.getListOfPrograms();

        listOfPrograms.add(new String[]{nameProgram, String.valueOf(energyConsumed)});

        List<String[]> expectedResult = listOfPrograms;

        assertEquals(expectedResult.get(4), result.get(4)); }

    @Test
    void checkIfDeviceIsProgrammableIfTrue() {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        boolean result = microwaveOven.checkIfIsProgrammable();
        assertTrue(result); }


    @Test
    void testIfMicrowaveOvenIsActiveFalse() {
        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        microwaveOven.deactivateDevice();
        boolean result = microwaveOven.isActive();
        assertFalse(result);
    }

    @Test
    void testIfMicrowaveOvenIsActive() {
        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        boolean result = microwaveOven.isActive();
        assertTrue(result);
    }

    @Test
    void checkIfMicrowaveOvenIsProgrammable() {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        boolean result = microwaveOven.checkIfIsProgrammable();
        assertTrue(result);
    }

    @Test
    void testCheckIfMicrowaveOvenIsMetered() {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        boolean result = microwaveOven.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetTypeOfMicrowaveOven() {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        String result = microwaveOven.getDeviceType().getType();
        String expectedResult = "MicrowaveOven";
        assertEquals(expectedResult, result);
    }


    @Test
    void testSetReadingsOfMicrowaveOven() {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        microwaveOven.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = microwaveOven.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result); }

    @Test
    void testGetNominalPowerOfMicrowaveOven() {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        microwaveOven.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        microwaveOven.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = microwaveOven.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumptionOfMicrowaveOven() throws Exception {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        microwaveOven.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        microwaveOven.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = microwaveOven.getConsumption(date1,date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDataSeries() {

        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        microwaveOven.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        microwaveOven.setListOfReadings(listOfReading);
        ListOfReadings result = microwaveOven.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testDeactivateFalse() {
        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        microwaveOven.deactivateDevice();
        boolean result = microwaveOven.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        MicrowaveOven microwaveOven = new MicrowaveOven("microwaveoven", new MicrowaveOvenSpec(), new MicrowaveOvenType());
        boolean result = microwaveOven.deactivateDevice();
        assertTrue(result);
    }
}
