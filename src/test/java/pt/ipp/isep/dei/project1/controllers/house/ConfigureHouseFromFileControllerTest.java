package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.house.*;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.readers.readerjson.JsonReader;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.readers.xml.XmlReader;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ConfigureHouseFromFileControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private HouseGridRepo houseGridRepo;
    @Mock
    private HouseDomainService houseDomainService;
    @Mock
    private HouseRepository houseRepository;


    private ConfigureHouseFromFileController controller;
    @Mock
    private RoomHouseGridService roomHouseGridService;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        houseDomainService = new HouseDomainService(houseRepository);
        controller = new ConfigureHouseFromFileController(roomDomainService, houseGridRepo, houseDomainService, roomHouseGridService);
    }

    private House mockHouse() {

        mockHouse().setNameOfHouse("Casa Grupo 1");

        /** creating dimensions of imported rooms**/
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        ArrayList<Double> dimensions3 = new ArrayList<>();
        dimensions1.add(13.0);
        dimensions1.add(7.0);
        dimensions1.add(3.5);

        ArrayList<Double> dimensions5 = new ArrayList<>();
        dimensions1.add(5.0);
        dimensions1.add(5.5);
        dimensions1.add(3.5);


        ArrayList<Double> dimensions6 = new ArrayList<>();
        dimensions1.add(7.0);
        dimensions1.add(21.0);
        dimensions1.add(3.5);

        ArrayList<Double> dimensions7 = new ArrayList<>();
        dimensions1.add(6.0);
        dimensions1.add(3.0);
        dimensions1.add(3.5);

        /** creating imported rooms**/

        Room room1 = new Room("B107", "Classroom", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));

        Room room2 = new Room("B109", "Classroom", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));

        Room room3 = new Room("B106", "Classroom", 1, dimensions3.get(0),dimensions3.get(1),dimensions3.get(2));

        Room room4 = new Room("B209", "Classroom", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));

        Room room5 = new Room("B210", "Meeting room", 2, dimensions5.get(0),dimensions5.get(1),dimensions5.get(2));

        Room room6 = new Room("B102", "Reprographics Centre", 1, dimensions6.get(0),dimensions6.get(1),dimensions6.get(2));

        Room room7 = new Room("B405A", "DEI Datacenter", 4, dimensions7.get(0),dimensions7.get(1),dimensions7.get(2));

        /** adding imported rooms to a list**/

        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        roomList.add(room5);
        roomList.add(room6);
        roomList.add(room7);

        /** creating string room names lists of imported grids**/

        List<String> roomNames2 = new ArrayList<>();
        roomNames2.add("B102");
        roomNames2.add("B405A");

        /** creating imported house grids**/

        HouseGrid hg1 = new HouseGrid("B building");
        HouseGrid hg2 = new HouseGrid("B building technical");


        /** adding imported house grids to a list**/

        houseGridRepo.addHouseGrid(hg1);
        houseGridRepo.addHouseGrid(hg2);
        List<HouseGrid> houseGridList = new ArrayList<>();
        houseGridList.add(hg1);
        houseGridList.add(hg2);

        /** creating address**/

        Address address = new Address("R. Dr. António Bernardino de Almeida",
                "4200-072", "Porto", "431", "Portugal");

        /** adding address to mock house**/

        mockHouse().setAddress(address);

        /** adding list of rooms to mock house**/

        roomDomainService.setListOfRooms(roomList);

        /** adding list of grids to mock house**/

        houseGridRepo.setListOfHouseGrids(houseGridList);

        /** adding list of rooms to grid "B building"**/

        roomHouseGridService.addRoomToHouseGrid(hg1,room3);
        roomHouseGridService.addRoomToHouseGrid(hg1,room1);
        roomHouseGridService.addRoomToHouseGrid(hg1,room2);
        roomHouseGridService.addRoomToHouseGrid(hg1,room4);
        roomHouseGridService.addRoomToHouseGrid(hg1,room5);

        /** adding list of rooms to grid "B building technical"**/

        roomHouseGridService.addRoomToHouseGrid(hg2,room6);
        roomHouseGridService.addRoomToHouseGrid(hg2,room7);

        return mockHouse();
    }

    @Test
    void getAvailableReaderTypesObjectsJson() throws Exception {

        List<FileReader> expectedResult = new ArrayList<>();
        JsonReader jsonReader = new JsonReader();
        XmlReader xmlReader = new XmlReader();
        expectedResult.add(jsonReader);
        expectedResult.add(xmlReader);
        List<FileReader> result = controller.getAvailableReaderTypesObjects();
        assertEquals(expectedResult.get(0).getReaderType(), result.get(0).getReaderType());
    }

    @Test
    void getAvailableReaderTypesObjectsXml() throws Exception {

        List<FileReader> expectedResult = new ArrayList<>();
        JsonReader jsonReader = new JsonReader();
        XmlReader xmlReader = new XmlReader();
        expectedResult.add(jsonReader);
        expectedResult.add(xmlReader);
        List<FileReader> result = controller.getAvailableReaderTypesObjects();
        assertEquals(expectedResult.get(1).getReaderType(), result.get(1).getReaderType());
    }


    @Test
    void importFromFileJson() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_House.json").getFile());
        String path = file.getAbsolutePath();

        List<String> result = controller.importHouseFromFile(URLDecoder.decode(path, "UTF-8"));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("JSON file read with success!");
        expectedResult.add("-------------------------------------------------");
        expectedResult.add("Information read by imported file:");
        expectedResult.add("-------------------------------------------------");
        expectedResult.add("Address:");
        expectedResult.add("Street: R. Dr. António Bernardino de Almeida");
        expectedResult.add("Number: 431");
        expectedResult.add("Zip: 4200-072");
        expectedResult.add("Town: Porto");
        expectedResult.add("Country: Portugal");
        expectedResult.add("-------------------------------------------------");
        expectedResult.add("-------------------------------------------------");
        expectedResult.add("Rooms:");
        expectedResult.add("B107");
        expectedResult.add("B109");
        expectedResult.add("B106");
        expectedResult.add("B209");
        expectedResult.add("B210");
        expectedResult.add("B102");
        expectedResult.add("B405A");
        expectedResult.add("-------------------------------------------------");
        expectedResult.add("Power Grids:");
        expectedResult.add("B building");
        expectedResult.add("B building technical");
        expectedResult.add("-------------------------------------------------");


        assertEquals(expectedResult, result);
    }

    @Test
    void importFromFileJsonTestingHouseName() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_House.json").getFile());
        String path = file.getAbsolutePath();

        controller.importHouseFromFile(URLDecoder.decode(path, "UTF-8"));

        String result = houseDomainService.getHouse().getNameOfHouse();
        String expectedResult = "The House";

        assertEquals(expectedResult, result);
    }

    @Test
    void importFromWRONGFileJson() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_GAData.json").getFile());
        String path = file.getAbsolutePath();

        List<String> result = controller.importHouseFromFile(URLDecoder.decode(path, "UTF-8"));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("The data from the file you are importing is not compatible with this function");

        assertEquals(expectedResult, result);
    }

    @Test
    void CheckIfImportFromInvalidPathJsonParseException() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint05_SensorData_alt01.xml").getFile());
        String path = file.getAbsolutePath();

        List<String> result = controller.importHouseFromFile(URLDecoder.decode(path, "UTF-8"));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("-------------------------------------------------");
        expectedResult.add("This functionality only can read Json files!");
        expectedResult.add("Please insert a valid file name and path.");
        expectedResult.add("-------------------------------------------------");



        assertEquals(expectedResult, result);
    }

    @Test
    void CheckIfImportFromInvalidPathFileNotFound() throws Exception {


        List<String> result = controller.importHouseFromFile(URLDecoder.decode("xxx", "UTF-8"));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("-------------------------------------------------");
        expectedResult.add("Invalid path/file!");
        expectedResult.add("Please insert a valid file name and path.");
        expectedResult.add("-------------------------------------------------");


        assertEquals(expectedResult, result);
    }


    @Test
    void importFromFileJsonCheckCreateHouse() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_House.json").getFile());
        String path = file.getAbsolutePath();

        controller.importHouseFromFile(URLDecoder.decode(path, "UTF-8"));

        assertEquals("B107", roomDomainService.getListOfRooms().get(0).getName());
    }

    @Test
    void importFromFileJsonCheckCreateHouse2() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("DataSet_sprint06_House.json").getFile());
        String path = file.getAbsolutePath();
        controller.importHouseFromFile(URLDecoder.decode(path, "UTF-8"));
        assertEquals("B building", roomDomainService.getListOfRooms().get(0).getHouseGrid());
    }

}


