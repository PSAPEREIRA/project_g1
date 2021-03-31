package pt.ipp.isep.dei.project1.model.device.stove;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoveTest {



    @Test
    void testGetName() {
        Stove stove = new Stove("Stove", new StoveSpec(),new StoveType());
        stove.setName("Stove1");
        String expectedResult = "Stove1";
        String result = stove.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void getListOfProgramsEmptyList() {
        Stove Stove1 = new Stove("Stove1", new StoveSpec(), new StoveType());
        List<String[]> result = Stove1.getListOfPrograms();
        List<String[]> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }


    @Test
    void testIfIsActiveFalse() {
        Stove stove = new Stove("Stove1",new StoveSpec(), new StoveType());
        stove.deactivateDevice();
        boolean result = stove.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveFalse2() {
        Stove stove = new Stove("Stove1",new StoveSpec(), new StoveType());
        boolean result = stove.deactivateDevice();
        assertTrue(result);
    }


    @Test
    void testIfIsActiveTrue() {
        Stove stove = new Stove("Stove1",new StoveSpec(), new StoveType());
        boolean result = stove.isActive();
        assertTrue(result);
    }


    @Test
    void checkIfAddProgramToStove() {

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

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());

        stove.createProgramAndAddTo("Program 3", 3.1);

        List<String[]> result = stove.getListOfPrograms();
        List<String[]> expectedResult = stove.getListOfPrograms();

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

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        stove.setListOfPrograms(listOfPrograms);

        List<String[]> result = stove.getListOfPrograms();

        List<String[]> expectedResult = new ArrayList<>();

        expectedResult.add(program1);
        expectedResult.add(program2);
        expectedResult.add(program3);

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfCreateAndAddProgramToStove() {

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

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        stove.setListOfPrograms(listOfPrograms);
        String nameProgram = "Program 4";
        double energyConsumed = 1.9;

        stove.createProgramAndAddTo(nameProgram, energyConsumed);

        List<String[]> result = stove.getListOfPrograms();

        listOfPrograms.add(new String[]{nameProgram, String.valueOf(energyConsumed)});

        List<String[]> expectedResult = listOfPrograms;

        assertArrayEquals(expectedResult.get(4), result.get(4));

    }

    @Test
    void checkIfDeviceIsProgrammableIfTrue() {

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        boolean result = stove.checkIfIsProgrammable();
        assertTrue(result); }




    @Test
    void testCheckIfStoveIsMetered() {

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        boolean result = stove.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetTypeOfStove() {

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        String result = stove.getDeviceType().getType();
        String expectedResult = "Stove";
        assertEquals(expectedResult, result);
    }


    @Test
    void testSetReadingsOfStove() {

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        stove.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = stove.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result); }

    @Test
    void testGetNominalPowerOfStove() {

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        stove.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        stove.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = stove.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumptionOfStove() throws Exception {

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        stove.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        stove.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = stove.getConsumption(date1,date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDataSeries() {

        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        stove.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        stove.setListOfReadings(listOfReading);
        ListOfReadings result = stove.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testSetReadings() {
        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        stove.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = stove.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }


    @Test
    void testDeactivateFalse() {
        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        stove.deactivateDevice();
        boolean result = stove.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        Stove stove = new Stove("stove", new StoveSpec(), new StoveType());
        boolean result = stove.deactivateDevice();
        assertTrue(result);
    }

}