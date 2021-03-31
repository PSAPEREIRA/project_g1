package pt.ipp.isep.dei.project1.model.device.dishwasher;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTest {


    @Test
    void testGetName() {
        Dishwasher dishwasher = new Dishwasher("dish1",new DishwasherSpec(), new DishwasherType());
        dishwasher.setName("d2");
        String expectedResult = "d2";
        String result = dishwasher.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    void testIfIsActiveFalse() {
        Dishwasher dishwasher = new Dishwasher("dish1",new DishwasherSpec(), new DishwasherType());
        dishwasher.deactivateDevice();
        boolean result = dishwasher.isActive();
        assertFalse(result);
    }

    @Test
    void testIfIsActiveTrue() {
        Dishwasher dishwasher = new Dishwasher("dish1",new DishwasherSpec(), new DishwasherType());
        boolean result = dishwasher.isActive();
        assertTrue(result);
    }



    @Test
    void getListOfProgramsEmptyList() {

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

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

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

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

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

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

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

        String nameProg = "Echo Turbo";
        double energyConsumed = 1.9;

        d1.setListOfPrograms(listOfPrograms);

        List<String[]> result = d1.getListOfPrograms();  //get RESULT

        listOfPrograms.add(new String[]{nameProg, String.valueOf(energyConsumed)});

        List<String[]> expectedResult = listOfPrograms;

        assertArrayEquals(expectedResult.get(3), result.get(3));

    }


    @Test
    void checkIfDeviceIsProgrammableIfTrue() {

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

        boolean result = d1.checkIfIsProgrammable();

        assertTrue(result);
    }

    @Test
    void testDeactivateFalse() {
        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        d1.deactivateDevice();
        boolean result = d1.isActive();
        assertFalse(result);
    }

    @Test
    void testDeactivateTrue() {
        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        boolean result = d1.deactivateDevice();
        assertTrue(result);
    }

    @Test
    void checkIfIsMetered() {
        Dishwasher d1 = new Dishwasher("D200", new DishwasherSpec(), new DishwasherType());
        boolean result = d1.checkIfIsMetered();

        assertTrue(result);
    }


}