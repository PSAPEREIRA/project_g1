package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.*;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetDataSeriesToDesignGraphControllerTest {

    @Mock
    private RoomRepository roomRepository;
    private RoomDomainService roomDomainService;
    @Mock
    private HouseGridRepository houseGridRepository;
    private HouseGridRepo houseGridRepo;
    private RoomHouseGridService roomHouseGridService;
    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
    }


    @Test
    void getListOfHouseGrid() throws Exception {
        //ARRANGE
        GeographicArea ga1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));

        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "room", 0, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        GetDataSeriesToDesignGraphController getDataSeries = new GetDataSeriesToDesignGraphController(roomDomainService, houseGridRepo,roomHouseGridService);
        //ACT
        ListOfHouseGridsDto result = getDataSeries.getListOfHouseGrid();
        ListOfHouseGridsDto expectedResult = MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());
        //ASSERT
        assertEquals(expectedResult.getListOfHouseGridDto().size(), result.getListOfHouseGridDto().size());

    }

    @Test
    void getListOfRooms() throws Exception {
        //ARRANGE
        GeographicArea ga1 = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "room", 0, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        roomDomainService.addRoom(room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        GetDataSeriesToDesignGraphController getDataSeries = new GetDataSeriesToDesignGraphController(roomDomainService, houseGridRepo,roomHouseGridService);
        //ACT
        ListOfRoomsDto result = getDataSeries.getListOfRooms();
        ListOfRoomsDto expectedResult = MapperListOfRooms.toDto(roomDomainService.getListOfRooms());

        //ASSERT
        assertEquals(expectedResult.getRoomDto().size(), result.getRoomDto().size());

    }


    @Test
    void CheckIfGetReadingsOfDeviceOnPeriodAsString() throws Exception {


        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "room", 0, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        roomDomainService.addRoom(room);
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
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r8 = new Reading(date8, 1.9);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r9 = new Reading(date9, 1.3);
        LocalDateTime date10 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r10 = new Reading(date10, 2);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r11 = new Reading(date11, 0);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r12 = new Reading(date12, 4.1);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r13 = new Reading(date13, 2.4);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r14 = new Reading(date14, 3.5);

        ListOfReadings lRead1 = new ListOfReadings();
        ListOfReadings lRead2 = new ListOfReadings();
        ListOfReadings lRead3 = new ListOfReadings();

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);

        lRead1.addReading(r8);
        lRead1.addReading(r9);
        lRead1.addReading(r10);

        lRead2.addReading(r11);
        lRead2.addReading(r12);

        lRead3.addReading(r13);
        lRead3.addReading(r14);

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d2", new DishwasherSpec(), new DishwasherType());
        Dishwasher d3 = new Dishwasher("d3", new DishwasherSpec(), new DishwasherType());

        d1.setListOfReadings(lRead1);
        d2.setListOfReadings(lRead2);
        d3.setListOfReadings(lRead3);

        room.getListOfDevices().addDeviceToList(d1);
        room.getListOfDevices().addDeviceToList(d2);
        room.getListOfDevices().addDeviceToList(d3);

        GetDataSeriesToDesignGraphController getDataSeries = new GetDataSeriesToDesignGraphController(roomDomainService, houseGridRepo,roomHouseGridService);

        LocalDateTime initiateDateTest = LocalDateTime.of(2019, 1, 21, 12, 21);
        LocalDateTime endDateTest = LocalDateTime.of(2019, 1, 21, 12, 31);


        List<String[]> result = getDataSeries.getReadingsOfDeviceOnPeriodAsString(0, initiateDateTest, endDateTest);

        List<String[]> expectedResult = new ArrayList<>();

        LocalDateTime expectedResultDateTest1 = LocalDateTime.of(2019, 1, 21, 12, 25);
        String expectedStringResultDateTest1 = expectedResultDateTest1.toString();

        LocalDateTime expectedResultTest2 = LocalDateTime.of(2019, 1, 21, 12, 30);
        String expectedStringResultDateTest2 = expectedResultTest2.toString();

        double expectedResultValueTest1 = 4.1;
        double expectedResultValueTest2 = 6.4;

        String[] expectedStringArray1 = new String[]{expectedStringResultDateTest1, String.valueOf(expectedResultValueTest1)};
        String[] expectedStringArray2 = new String[]{expectedStringResultDateTest2, String.valueOf(expectedResultValueTest2)};

        expectedResult.add(expectedStringArray1);
        expectedResult.add(expectedStringArray2);

/*
        for (int i=0;i< result.size();i++)
           System.out.println("Result - "+result.get(i)[0]+" - Expected - "+expectedResult.get(i)[0]);
            System.out.println("Result - "+result.get(i)[1]+" - Expected - "+expectedResult.get(i)[1]);

        }
*/
        assertArrayEquals(expectedResult.get(0), result.get(0));
        assertArrayEquals(expectedResult.get(1), result.get(1));
    }


    @Test
    void checkIfGetReadingsOfDevicesInRoomOnPeriodAsString() throws Exception {


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
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r8 = new Reading(date8, 1.9);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r9 = new Reading(date9, 1.3);
        LocalDateTime date10 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r10 = new Reading(date10, 2);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r11 = new Reading(date11, 0);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r12 = new Reading(date12, 4.1);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r13 = new Reading(date13, 2.4);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r14 = new Reading(date14, 3.5);

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

        lRead1.addReading(r8);
        lRead1.addReading(r9);
        lRead1.addReading(r10);

        lRead2.addReading(r11);
        lRead2.addReading(r12);

        lRead3.addReading(r13);
        lRead3.addReading(r14);

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d2", new DishwasherSpec(), new DishwasherType());
        Dishwasher d3 = new Dishwasher("d3", new DishwasherSpec(), new DishwasherType());

        d1.setListOfReadings(lRead1);
        d2.setListOfReadings(lRead2);
        d3.setListOfReadings(lRead3);


        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("Main Room", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        roomDomainService.addRoom(room);
        room.getListOfDevices().addDeviceToList(d1);
        room.getListOfDevices().addDeviceToList(d2);
        room.getListOfDevices().addDeviceToList(d3);
        room.getListOfDevices().addDeviceToList(d1);
        room.getListOfDevices().addDeviceToList(d2);
        room.getListOfDevices().addDeviceToList(d3);

        GetDataSeriesToDesignGraphController getDataSeries = new GetDataSeriesToDesignGraphController(roomDomainService, houseGridRepo,roomHouseGridService);

        LocalDateTime initiateDateTest = LocalDateTime.of(2019, 1, 21, 12, 21);
        LocalDateTime endDateTest = LocalDateTime.of(2019, 1, 21, 12, 31);

        RoomDto roomDto = MapperRoom.toDto(room);
        List<String[]> result = getDataSeries.getReadingsOfRoomOnPeriodAsString(roomDto, initiateDateTest, endDateTest);

        List<String[]> expectedResult = new ArrayList<>();

        LocalDateTime expectedResultDateTest1 = LocalDateTime.of(2019, 1, 21, 12, 25);
        String expectedStringResultDateTest1 = expectedResultDateTest1.toString();

        LocalDateTime expectedResultTest2 = LocalDateTime.of(2019, 1, 21, 12, 30);
        String expectedStringResultDateTest2 = expectedResultTest2.toString();

        double expectedResultValueTest1 = 20.5;
        double expectedResultValueTest2 = 24.4;

        String[] expectedStringArray1 = new String[]{expectedStringResultDateTest1, String.valueOf(expectedResultValueTest1)};
        String[] expectedStringArray2 = new String[]{expectedStringResultDateTest2, String.valueOf(expectedResultValueTest2)};

        expectedResult.add(expectedStringArray1);
        expectedResult.add(expectedStringArray2);


        for (int i = 0; i < result.size(); i++)/*{
            System.out.println("Result - "+result.get(i)[0]+" - Expected - "+expectedResult.get(i)[0]);
            System.out.println("Result - "+result.get(i)[1]+" - Expected - "+expectedResult.get(i)[1]);

        }
*/
            assertArrayEquals(expectedResult.get(0), result.get(0));
        assertArrayEquals(expectedResult.get(1), result.get(1));
    }

    @Test
    void CheckIfGetReadingsOfGridsOnPeriodAsString() throws Exception {

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
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r8 = new Reading(date8, 1.9);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r9 = new Reading(date9, 1.3);
        LocalDateTime date10 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r10 = new Reading(date10, 2);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r11 = new Reading(date11, 0);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r12 = new Reading(date12, 4.1);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r13 = new Reading(date13, 2.4);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r14 = new Reading(date14, 3.5);
        LocalDateTime date15 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r15 = new Reading(date15, 3.5);

        ListOfReadings lRead1 = new ListOfReadings();
        ListOfReadings lRead2 = new ListOfReadings();
        ListOfReadings lRead3 = new ListOfReadings();
        ListOfReadings lRead4 = new ListOfReadings();
        ListOfReadings lRead5 = new ListOfReadings();
        ListOfReadings lRead6 = new ListOfReadings();
        ListOfReadings lRead7 = new ListOfReadings();
        ListOfReadings lRead8 = new ListOfReadings();

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead2.addReading(r3);
        lRead2.addReading(r4);
        lRead3.addReading(r5);
        lRead3.addReading(r6);
        lRead3.addReading(r7);
        lRead4.addReading(r8);
        lRead5.addReading(r9);
        lRead6.addReading(r10);
        lRead7.addReading(r11);
        lRead7.addReading(r12);
        lRead8.addReading(r13);
        lRead8.addReading(r14);
        lRead8.addReading(r15);

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d2", new DishwasherSpec(), new DishwasherType());
        Dishwasher d3 = new Dishwasher("d3", new DishwasherSpec(), new DishwasherType());


        d1.setListOfReadings(lRead1);
        d2.setListOfReadings(lRead2);
        d3.setListOfReadings(lRead3);


        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("Main Room", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("Bed Room", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room3 = new Room("Kitchen", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room4 = new Room("Garage", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));


        room1.getListOfDevices().addDeviceToList(d1);
        room1.getListOfDevices().addDeviceToList(d2);
        room1.getListOfDevices().addDeviceToList(d3);





        HouseGrid hGrid = new HouseGrid("xpt1", 100);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        roomDomainService.addRoom(room4);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room2);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room3);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room4);

        houseGridRepo.addHouseGrid(hGrid);

        GetDataSeriesToDesignGraphController getDataSeries = new GetDataSeriesToDesignGraphController(roomDomainService, houseGridRepo,roomHouseGridService);

        LocalDateTime initiateDateTest = LocalDateTime.of(2019, 1, 21, 12, 21);
        LocalDateTime endDateTest = LocalDateTime.of(2019, 1, 21, 12, 31);

        HouseGridDto houseGridDto = MapperHouseGrid.toDto(hGrid);
        List<String[]> result = getDataSeries.getReadingsOfHouseGridOnPeriodAsString(houseGridDto, initiateDateTest, endDateTest);

        List<String[]> expectedResult = new ArrayList<>();

        LocalDateTime expectedResultDateTest1 = LocalDateTime.of(2019, 1, 21, 12, 25);
        String expectedStringResultDateTest1 = expectedResultDateTest1.toString();

        LocalDateTime expectedResultTest2 = LocalDateTime.of(2019, 1, 21, 12, 30);
        String expectedStringResultDateTest2 = expectedResultTest2.toString();

        double expectedResultValueTest1 = 4.1;
        double expectedResultValueTest2 = 4.4;

        String[] expectedStringArray1 = new String[]{expectedStringResultDateTest1, String.valueOf(expectedResultValueTest1)};
        String[] expectedStringArray2 = new String[]{expectedStringResultDateTest2, String.valueOf(expectedResultValueTest2)};

        expectedResult.add(expectedStringArray1);
        expectedResult.add(expectedStringArray2);


        for (int i = 0; i < result.size(); i++)/*{
            System.out.println("Result - "+result.get(i)[0]+" - Expected - "+expectedResult.get(i)[0]);
            System.out.println("Result - "+result.get(i)[1]+" - Expected - "+expectedResult.get(i)[1]);

        }
*/
            assertArrayEquals(expectedResult.get(0), result.get(0));
        assertArrayEquals(expectedResult.get(1), result.get(1));
    }

    @Test
    void CheckIfGetReadingsOfMeteredOnPeriodAsString() throws Exception {

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
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r8 = new Reading(date8, 1.9);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r9 = new Reading(date9, 1.3);
        LocalDateTime date10 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r10 = new Reading(date10, 2);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r11 = new Reading(date11, 0);
        LocalDateTime date12 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r12 = new Reading(date12, 4.1);
        LocalDateTime date13 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r13 = new Reading(date13, 2.4);
        LocalDateTime date14 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r14 = new Reading(date14, 3.5);
        LocalDateTime date15 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r15 = new Reading(date15, 3.5);

        ListOfReadings lRead1 = new ListOfReadings();
        ListOfReadings lRead2 = new ListOfReadings();
        ListOfReadings lRead3 = new ListOfReadings();
        ListOfReadings lRead4 = new ListOfReadings();
        ListOfReadings lRead5 = new ListOfReadings();
        ListOfReadings lRead6 = new ListOfReadings();
        ListOfReadings lRead7 = new ListOfReadings();
        ListOfReadings lRead8 = new ListOfReadings();

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead2.addReading(r3);
        lRead2.addReading(r4);
        lRead3.addReading(r5);
        lRead3.addReading(r6);
        lRead3.addReading(r7);
        lRead4.addReading(r8);
        lRead5.addReading(r9);
        lRead6.addReading(r10);
        lRead7.addReading(r11);
        lRead7.addReading(r12);
        lRead8.addReading(r13);
        lRead8.addReading(r14);
        lRead8.addReading(r15);

        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d2", new DishwasherSpec(), new DishwasherType());
        Dishwasher d3 = new Dishwasher("d3", new DishwasherSpec(), new DishwasherType());

        d1.setListOfReadings(lRead1);
        d2.setListOfReadings(lRead2);
        d3.setListOfReadings(lRead3);


        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("Main Room", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("Bed Room", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room3 = new Room("Kitchen", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room4 = new Room("Garage", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));


        room1.getListOfDevices().addDeviceToList(d1);
        room1.getListOfDevices().addDeviceToList(d2);
        room1.getListOfDevices().addDeviceToList(d3);




        HouseGrid hGrid = new HouseGrid("xpt1", 100);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        roomDomainService.addRoom(room4);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room2);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room3);
        roomHouseGridService.addRoomToHouseGrid(hGrid,room4);
        houseGridRepo.addHouseGrid(hGrid);

        GetDataSeriesToDesignGraphController getDataSeries = new GetDataSeriesToDesignGraphController(roomDomainService, houseGridRepo,roomHouseGridService);

        LocalDateTime initiateDateTest = LocalDateTime.of(2019, 1, 21, 12, 21);
        LocalDateTime endDateTest = LocalDateTime.of(2019, 1, 21, 12, 31);

        HouseGridDto houseGridDto = MapperHouseGrid.toDto(hGrid);
        List<String[]> result = getDataSeries.getReadingsOfHouseGridOnPeriodAsString(houseGridDto, initiateDateTest, endDateTest);

        List<String[]> expectedResult = new ArrayList<>();

        LocalDateTime expectedResultDateTest1 = LocalDateTime.of(2019, 1, 21, 12, 25);
        String expectedStringResultDateTest1 = expectedResultDateTest1.toString();


        double expectedResultValueTest1 = 4.1;

        String[] expectedStringArray1 = new String[]{expectedStringResultDateTest1, String.valueOf(expectedResultValueTest1)};

        expectedResult.add(expectedStringArray1);

        assertArrayEquals(expectedResult.get(0), result.get(0));
    }


    @Test
    void getallDevicesAsString() throws Exception {


        Dishwasher d1 = new Dishwasher("d1", new DishwasherSpec(), new DishwasherType());
        Dishwasher d2 = new Dishwasher("d2", new DishwasherSpec(), new DishwasherType());
        Dishwasher d3 = new Dishwasher("d3", new DishwasherSpec(), new DishwasherType());


        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("Main Room", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));

        room1.getListOfDevices().addDeviceToList(d1);
        room1.getListOfDevices().addDeviceToList(d2);
        room1.getListOfDevices().addDeviceToList(d3);
        roomDomainService.addRoom(room1);
        GetDataSeriesToDesignGraphController getDataSeries = new GetDataSeriesToDesignGraphController(roomDomainService, houseGridRepo,roomHouseGridService);

        ListOfDevicesDto result = getDataSeries.getListOfAllDevicesInsideHouse();
        ListOfDevicesDto expectedResult = MapperListOfDevice.toDto(room1.getListOfDevices().getDeviceList());

        assertEquals(expectedResult.getListOfDevices().size(), result.getListOfDevices().size());
    }

}