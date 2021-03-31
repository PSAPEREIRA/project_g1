package pt.ipp.isep.dei.project1.model.device.fan;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FanTest {


    @Test
    void testGetName() {
        Fan fan = new Fan("Fan", new FanSpec(), new FanType());
        fan.setName("Fan");
        String expectedResult = "Fan";
        String result = fan.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void getListOfProgramsEmptyList() {
        Fan fan1 = new Fan("Fan1", new FanSpec(), new FanType());
        List<String[]> result = fan1.getListOfPrograms();
        List<String[]> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfAddProgramToFan() {

        String[] program1 = new String[2];
        program1[0] = "Summer Breeze";
        program1[1] = "0.3";

        String[] program2 = new String[2];
        program2[0] = "First days Of Autumn ";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Winds of the North";
        program3[1] = "3.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);


        List<String[]> listOfPrograms2 = new ArrayList<>();

        listOfPrograms2.add(program1);
        listOfPrograms2.add(program2);

        Fan fan = new Fan("fan", new FanSpec(), new FanType());

        fan.createProgramAndAddTo("Winds of the North", 3.1);

        List<String[]> result = fan.getListOfPrograms();
        List<String[]> expectedResult = fan.getListOfPrograms();

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfEditSelectedProgramAndField() {

        String[] program1 = new String[2];
        program1[0] = "Summer Breeze";
        program1[1] = "0.3";

        String[] program2 = new String[2];
        program2[0] = "First days Of Autumn ";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Winds of the North";
        program3[1] = "3.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);


        Fan fan = new Fan("fan", new FanSpec(), new FanType());

        fan.setListOfPrograms(listOfPrograms);

        String editedProgramName = "Dragon Breath";

        int progIndex = 1;
        int fieldIndex = 0;

        fan.editSelectedProgramAndField(progIndex, fieldIndex, editedProgramName);

        String result = fan.getListOfPrograms().get(progIndex)[fieldIndex];
        String expectedResult = editedProgramName;

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfDeletedProgramAndField() {

        String[] program1 = new String[2];
        program1[0] = "Summer Breeze";
        program1[1] = "0.3";

        String[] program2 = new String[2];
        program2[0] = "First days Of Autumn ";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Winds of the North";
        program3[1] = "3.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);


        Fan fan = new Fan("fan", new FanSpec(), new FanType());

        fan.setListOfPrograms(listOfPrograms);

        String editedProgramName = "Dragon Breath";

        int progIndex = 2;


        fan.deleteProgram(progIndex);

        List<String[]> result = fan.getListOfPrograms();

        List<String[]> expectedResult = new ArrayList<>();

        expectedResult.add(program1);
        expectedResult.add(program2);

        assertEquals(expectedResult, result);
    }


    @Test
    void getCompleteListOfPrograms() {

        String[] program1 = new String[2];
        program1[0] = "Summer Breeze";
        program1[1] = "0.3";

        String[] program2 = new String[2];
        program2[0] = "First days Of Autumn ";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Winds of the North";
        program3[1] = "3.1";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        fan.setListOfPrograms(listOfPrograms);

        List<String[]> result = fan.getListOfPrograms();

        List<String[]> expectedResult = new ArrayList<>();

        expectedResult.add(program1);
        expectedResult.add(program2);
        expectedResult.add(program3);

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfCreateAndAddProgramToFan() {

        String[] program1 = new String[2];
        program1[0] = "Summer Breeze";
        program1[1] = "0.3";

        String[] program2 = new String[2];
        program2[0] = "First days Of Autumn ";
        program2[1] = "1.3";

        String[] program3 = new String[2];
        program3[0] = "Winds of the North";
        program3[1] = "3.1";

        String[] program4 = new String[2];
        program4[0] = "Dragons Breath";
        program4[1] = "1.9";

        List<String[]> listOfPrograms = new ArrayList<>();

        listOfPrograms.add(program1);
        listOfPrograms.add(program2);
        listOfPrograms.add(program3);

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        fan.setListOfPrograms(listOfPrograms);
        String nameProgram = "Dragons Breath";
        double energyConsumed = 1.9;

        fan.createProgramAndAddTo(nameProgram, energyConsumed);

        List<String[]> result = fan.getListOfPrograms();

        listOfPrograms.add(new String[]{nameProgram, String.valueOf(energyConsumed)});

        List<String[]> expectedResult = listOfPrograms;

        assertArrayEquals(expectedResult.get(4), result.get(4));

    }

    @Test
    void checkIfDeviceIsProgrammableIfTrue() {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        boolean result = fan.checkIfIsProgrammable();
        assertTrue(result);
    }


    @Test
    void testIfFanIsActiveFalse() {
        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        fan.deactivateDevice();
        boolean result = fan.isActive();
        assertFalse(result);
    }

    @Test
    void testIfFanIsActive() {
        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        boolean result = fan.isActive();
        assertTrue(result);
    }

    @Test
    void checkIfFanIsProgrammable() {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        boolean result = fan.checkIfIsProgrammable();
        assertTrue(result);
    }

    @Test
    void testCheckIfFanIsMetered() {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        boolean result = fan.checkIfIsMetered();
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetTypeOfFan() {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        String result = fan.getDeviceType().getType();
        String expectedResult = "Fan";
        assertEquals(expectedResult, result);
    }


    @Test
    void testSetReadingsOfFan() {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 13, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 22, 0);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        fan.setListOfReadings(listOfReading);
        double expectedResult = 12.5;
        double result = fan.getListOfReadings().getListOfReading().get(2).getValue();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNominalPowerOfFan() {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        fan.getDeviceSpecs().setAttributeValue("nominal power", 12.2);

        double expectedResult = 12.2;
        double result = fan.getNominalPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetConsumptionOfFan() throws Exception {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        fan.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();

        double valueR1 = 9;
        double valueR2 = 13;
        double valueR3 = 12;

        listOfReading.addReading(new Reading(date1, valueR1));
        listOfReading.addReading(new Reading(date2, valueR2));
        listOfReading.addReading(new Reading(date3, valueR3));
        fan.setListOfReadings(listOfReading);
        double expectedResult = valueR1 + valueR2 + valueR3;
        double result = fan.getConsumption(date1.minusMinutes(15), date3);
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetDataSeries() {

        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        fan.getDeviceSpecs().setAttributeValue("nominal power", 20);
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 25, 11, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 25, 11, 45);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 25, 12, 00);
        ListOfReadings listOfReading = new ListOfReadings();
        listOfReading.addReading(new Reading(date1, 12.0));
        listOfReading.addReading(new Reading(date2, 13.0));
        listOfReading.addReading(new Reading(date3, 12.5));
        fan.setListOfReadings(listOfReading);

        LocalDateTime periodStart = LocalDateTime.of(2018, 12, 25, 11, 00);
        LocalDateTime periodEnd = LocalDateTime.of(2018, 12, 25, 13, 00);

        double expectedResult = (12 + 13 + 12.5);

        ListOfReadings result = fan.getDataSeries(periodStart, periodEnd);
        double valueResult = 0;
        for (int i = 0; i < result.getListOfReading().size(); i++) {
            valueResult = valueResult + result.getListOfReading().get(i).getValue();
        }
        assertEquals(expectedResult, valueResult);
    }


    @Test
    void testDeactivateFalse() {
        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        fan.deactivateDevice();
        boolean result = fan.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        Fan fan = new Fan("fan", new FanSpec(), new FanType());
        boolean result = fan.deactivateDevice();
        assertTrue(result);
    }

}
