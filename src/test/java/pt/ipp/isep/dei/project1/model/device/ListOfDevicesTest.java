package pt.ipp.isep.dei.project1.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.controllers.device.EditConfigurationOfDeviceController;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListOfDevicesTest {

    @Test
    void testInsertDeviceTrue() {
        Dishwasher device = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Dishwasher device1 = new Dishwasher("Stove", new DishwasherSpec(), new DishwasherType());
        ListOfDevices listOfDevices = new ListOfDevices();
        listOfDevices.addDeviceToList(device);
        listOfDevices.addDeviceToList(device1);
        boolean result = listOfDevices.addDeviceToList(device1);
        assertTrue(result);
    }


    @Test
    void gettingDeviceFromList() {
        //ARRANGE
        Dishwasher dishwasher = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Fridge fridge = new Fridge("Fridge", new FridgeSpec(), new FridgeType());
        Dishwasher expectedResult = dishwasher;
        ListOfDevices listOfDevices = new ListOfDevices();
        //ACT
        listOfDevices.addDeviceToList(dishwasher);
        listOfDevices.addDeviceToList(fridge);
        Dishwasher result = (Dishwasher) listOfDevices.gettingDeviceFromList(0);
        //ASSERT
        assertEquals(expectedResult, result);

    }

    @Test
    void gettingDeviceFromListEmpty() {
        //ARRANGE
        Device expectedResult = null;
        ListOfDevices listOfDevices = new ListOfDevices();
        //ACT
        Device result = listOfDevices.gettingDeviceFromList(0);
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetNamesOfDevices() {
        Dishwasher dishwasher = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Dishwasher dishwasher1 = new Dishwasher("Dishwasher1", new DishwasherSpec(), new DishwasherType());
        Dishwasher dishwasher2 = new Dishwasher("Dishwasher2", new DishwasherSpec(), new DishwasherType());

        ListOfDevices lDev1 = new ListOfDevices();

        lDev1.addDeviceToList(dishwasher);
        lDev1.addDeviceToList(dishwasher1);
        lDev1.addDeviceToList(dishwasher2);

        List<String> result = lDev1.getNamesOfListOfDevicesForEach();
        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("Dishwasher");
        expectedResult.add("Dishwasher1");
        expectedResult.add("Dishwasher2");

        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfGetNamesOfDevicesInList() {
        Dishwasher dishwasher = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Dishwasher dishwasher1 = new Dishwasher("Dishwasher1", new DishwasherSpec(), new DishwasherType());
        Dishwasher dishwasher2 = new Dishwasher("Dishwasher2", new DishwasherSpec(), new DishwasherType());

        ListOfDevices lDev1 = new ListOfDevices();

        lDev1.addDeviceToList(dishwasher);
        lDev1.addDeviceToList(dishwasher1);
        lDev1.addDeviceToList(dishwasher2);

        List<String> result = lDev1.getNamesOfDevicesInListMenu();
        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("1 - Dishwasher");
        expectedResult.add("2 - Dishwasher1");
        expectedResult.add("3 - Dishwasher2");
        expectedResult.add("0 - Exit");

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfGetListOfReadingsOfAllDevicesInListOfReadings() {

        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r7 = new Reading(date7, 3.5);

        ListOfReadings lRead1 = new ListOfReadings();
        ListOfReadings lRead2 = new ListOfReadings();
        ListOfReadings lRead3 = new ListOfReadings();

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead2.addReading(r3);
        lRead2.addReading(r4);
        lRead3.addReading(r5);
        lRead3.addReading(r6);
        lRead3.addReading(r7);

        Dishwasher dishwasher = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Dishwasher dishwasher1 = new Dishwasher("Dishwasher1", new DishwasherSpec(), new DishwasherType());
        Dishwasher dishwasher2 = new Dishwasher("Dishwasher2", new DishwasherSpec(), new DishwasherType());

        dishwasher.setListOfReadings(lRead1);
        dishwasher1.setListOfReadings(lRead2);
        dishwasher2.setListOfReadings(lRead3);

        ListOfDevices lDev1 = new ListOfDevices();

        lDev1.addDeviceToList(dishwasher);
        lDev1.addDeviceToList(dishwasher1);
        lDev1.addDeviceToList(dishwasher2);


        ListOfReadings result = lDev1.getListOfReadingsOfAllDevicesInListOfDev();
        ListOfReadings expectedResult = new ListOfReadings();

        expectedResult.addReading(r1);
        expectedResult.addReading(r2);
        expectedResult.addReading(r3);
        expectedResult.addReading(r4);
        expectedResult.addReading(r5);
        expectedResult.addReading(r6);
        expectedResult.addReading(r7);


/*
        for (int i=0;i< result.getListOfReadings().size();i++){
            System.out.println("Result - "+result.getListOfReadings().get(i).getDateTime()+" - Expected - "+expectedResult.getListOfReadings().get(i).getDateTime());
            System.out.println("Result - "+result.getListOfReadings().get(i).getValue()+" - Expected - "+expectedResult.getListOfReadings().get(i).getValue());

        }
        */
        assertEquals(expectedResult.getListOfReading().get(0), result.getListOfReading().get(0));
    }
    @Test
    void testGetSizeOfDeviceList() {
        //Arrange
        Dishwasher device = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Dishwasher device1 = new Dishwasher("Stove", new DishwasherSpec(), new DishwasherType());
        ListOfDevices listOfDevices = new ListOfDevices();
        listOfDevices.addDeviceToList(device);
        listOfDevices.addDeviceToList(device1);
        //Act
        int result = listOfDevices.getSizeOfDeviceList();
        //Assert
        assertEquals(2,result);
    }

    @Test
    void getDeviceByName() {
        Dishwasher expectedResult = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Dishwasher device1 = new Dishwasher("Stove", new DishwasherSpec(), new DishwasherType());
        ListOfDevices listOfDevices = new ListOfDevices();
        listOfDevices.addDeviceToList(expectedResult);
        listOfDevices.addDeviceToList(device1);

        Device result = listOfDevices.getDeviceByName("Dishwasher");

        assertEquals(expectedResult,result);
    }

    @Test
    void getDeviceByNameNull() {
        Dishwasher device = new Dishwasher("Dishwasher", new DishwasherSpec(), new DishwasherType());
        Dishwasher device1 = new Dishwasher("Stove", new DishwasherSpec(), new DishwasherType());
        ListOfDevices listOfDevices = new ListOfDevices();
        listOfDevices.addDeviceToList(device);
        listOfDevices.addDeviceToList(device1);

        Device result = listOfDevices.getDeviceByName("Dishwasher1");

        assertEquals(null,result);
    }





/*
    public int getSizeOfDeviceList() {
        return listOfDevices.size();
    }

    @Test
    void checkIfGoToDeviceToEditProgram() {

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
        listOfPrograms.add(program4);


        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());

        Fridge f1 = new Fridge("FR2000", new FridgeSpec(), new FridgeType());
        Fridge f2 = new Fridge("FR2000", new FridgeSpec(), new FridgeType());

        d1.setListOfPrograms(listOfPrograms);
        d2.setListOfPrograms(listOfPrograms);


        ListOfDevices listOfDevices = new ListOfDevices();

        listOfDevices.addDeviceToList(d1);
        listOfDevices.addDeviceToList(d2);
        listOfDevices.addDeviceToList(f1);
        listOfDevices.addDeviceToList(f2);

       // listOfDevices.getDeviceToEditProgram(0,0,0,"newName");

        listOfDevices.getDeviceToEditProgram(3,0,0,"newName");

       // String result = f1.getListOfPrograms().get(0)[0];
        String expectedResult = null;

        //assertEquals(expectedResult,result);

    }
    */
}
