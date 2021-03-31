package pt.ipp.isep.dei.project1.model.device.electricoven;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElectricOvenTest {



    @Test
    void testGetName() {
        ElectricOven ElectricHoven = new ElectricOven("Hoven2",new ElectricOvenSpec(), new ElectricOvenType());
        ElectricHoven.setName("EH2");
        String expectedResult = "EH2";
        String result = ElectricHoven.getName();
        assertEquals(expectedResult,result);
    }


    @Test
    void getListOfProgramsEmptyList() {

        ElectricOven d1 = new ElectricOven("d1", new ElectricOvenSpec(), new ElectricOvenType());

        List<String[]> result = d1.getListOfPrograms();

        List<String[]> expectedResult = new ArrayList<>();

        assertEquals(expectedResult, result);

    }

    @Test
    void getListOfProgramsFullList() {

        String[] program1 = new String[2];
        program1[0] = "Grill";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Roast";
        program3[1] = "2.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        ElectricOven d1 = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
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
        program1[0] = "Grill";
        program1[1] = "0.9";

        String[] program2 = new String[3];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[3];
        program3[0] = "Roast";
        program3[1] = "2.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);


        List<String[]> listOfPrograms2 = new ArrayList<>();

        listOfPrograms2.add(program1);
        listOfPrograms2.add(program2);

        ElectricOven d1 = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());

        d1.createProgramAndAddTo("EH1", 2.1);

        List<String[]> result = d1.getListOfPrograms();
        List<String[]> expectedResult = d1.getListOfPrograms();

        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfCreateAndAddProgramToDW() {

        String[] program1 = new String[2];
        program1[0] = "Grill";
        program1[1] = "0.9";

        String[] program2 = new String[2];
        program2[0] = "Echo";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Roast";
        program3[1] = "2.1";

        String[] program4 = new String[2];
        program4[0] = "Echo Turbo";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        ElectricOven d1 = new ElectricOven("d1", new ElectricOvenSpec(), new ElectricOvenType());
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

        ElectricOven d1 = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());

        boolean result = d1.checkIfIsProgrammable();

        assertTrue(result);
    }

    @Test
    void checkIfDeviceIsProgrammableIfFalse() {

        ElectricOven l1 = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());

        boolean result = l1.checkIfIsProgrammable();

        assertTrue(result);
    }

    @Test
    void testIfIsActiveFalse() {
        ElectricOven l1 = new ElectricOven("Eh1", new ElectricOvenSpec(), new ElectricOvenType());
        l1.deactivateDevice();
        boolean result = l1.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        ElectricOven l1 = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        boolean result = l1.isActive();
        assertTrue(result);
    }

    @Test
    void checkIfDeviceIsProgrammable() {

        ElectricOven l1 = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());

        boolean result = l1.checkIfIsProgrammable();

        assertTrue(result);
    }


    @Test
    void testSetReadings() {
        ElectricOven ElectricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        ElectricHoven.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = ElectricHoven.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPower() {

        ElectricOven ElectricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        ElectricHoven.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        ElectricHoven.setListOfReadings(listOfReading);
        double expectedResult = 20;
        double result = ElectricHoven.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumption() throws Exception {
        ElectricOven ElectricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        ElectricHoven.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        ElectricHoven.setListOfReadings(listOfReading);
        double expectedResult = 25.5;
        double result = ElectricHoven.getConsumption(date1,date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDataSeries() {
        ElectricOven ElectricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        ElectricHoven.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        ElectricHoven.setListOfReadings(listOfReading);
        ListOfReadings result = ElectricHoven.getDataSeries(date1,date3);
        assertEquals(12.5, result.getListOfReading().get(1).getValue());
    }

    @Test
    void testCheckIfIsProgrammable() {
        ElectricOven ElectricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        boolean result = ElectricHoven.checkIfIsProgrammable();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testCheckIfIsMetered() {
        ElectricOven ElectricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        boolean result = ElectricHoven.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetType() {
        ElectricOven ElectricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        String result = ElectricHoven.getDeviceType().getType();
        String expectedResult = "ElectricOven";
        assertEquals(expectedResult, result);
    }


    @Test
    void testDeactivateFalse() {
        ElectricOven electricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        electricHoven.deactivateDevice();
        boolean result = electricHoven.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        ElectricOven electricHoven = new ElectricOven("EH1", new ElectricOvenSpec(), new ElectricOvenType());
        boolean result = electricHoven.deactivateDevice();
        assertTrue(result);
    }

}