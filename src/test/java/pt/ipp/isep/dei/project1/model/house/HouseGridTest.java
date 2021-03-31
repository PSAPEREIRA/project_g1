package pt.ipp.isep.dei.project1.model.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HouseGridTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private RoomHouseGridService roomHouseGridService;
    @BeforeEach
    void initUseCase() throws Exception {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
    }


    @Test
    public void createHouseGridNonValidName() {
        try {
            HouseGrid hg = new HouseGrid("", 100);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid code for this House Grid", e.getMessage());
        }
    }

    @Test
    public void createHouseGridNonValidNameNull() {
        try {
            HouseGrid hg = new HouseGrid(null, 100);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid code for this House Grid", e.getMessage());
        }
    }

    @Test
    public void createHouseGridNonValidPowerLimiter() {
        try {
            HouseGrid hg = new HouseGrid("hg", -5);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Power Limiter for this House Grid", e.getMessage());
        }
    }


    @Test
    public void createHouseGridNonValidPowerLimiterNull() {
        try {
            HouseGrid hg = new HouseGrid("hg", Double.NaN);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Power Limiter for this House Grid", e.getMessage());
        }
    }


    @Test
    public void createHouseGridNonValidCode() {
        try {
            HouseGrid hg = new HouseGrid("");
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid code for this House Grid", e.getMessage());
        }
    }



    @Test
    public void equalsTrueTest() {
        //ARRANGE
        HouseGrid hg = new HouseGrid("xpt1", 100);
        hg.setCode("xpt01");
        Object obj = new HouseGrid("xpt1", 100);
        ((HouseGrid) obj).setCode("xpt01");
        //ACT
        boolean result = hg.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        HouseGrid hg = new HouseGrid("xpt1", 100);
        hg.setCode("xpt01");
        Object obj = hg;
        boolean result = hg.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        HouseGrid hg = new HouseGrid("xpt1", 100);
        hg.setCode("xpt01");
        Object obj = new HouseGrid("xpt1", 100);
        ((HouseGrid) obj).setCode("xpt02");
        //ACT
        boolean result = hg.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        HouseGrid hg = new HouseGrid("xpt1", 100);
        hg.setCode("xpt01");
        Object obj = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2019, 01, 05), "ºC");
        ;
        //ACT
        boolean result = hg.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        //ARRANGE
        HouseGrid hg = new HouseGrid();
        hg.setCode("xpt01");
        //ACT
        int result = hg.getCode().charAt(0);
        //ASSERT
        assertEquals(hg.hashCode(), result);
    }


    @Test
    void getCode() {
        //ARRANGE
        HouseGrid hg = new HouseGrid("xpt1", 100);
        hg.setCode("xpt01");
        String expectedResult = "xpt01";
        //ACT
        String result = hg.getCode();
        //ASSERT
        assertEquals(expectedResult, result);
    }


    @Test
    void getmListOfRooms() {
        HouseGrid HG = new HouseGrid("code1", 100);
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("bedRoom","classroom", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("livingRoom","classroom", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        List<String>listOfRooms = new ArrayList<>();
        listOfRooms.add(room.getName());
        listOfRooms.add(room2.getName());
        HG.setRoomsList(listOfRooms);
        List<String> result = HG.getRoomsList();
        assertEquals(listOfRooms, result);
    }

    @Test
    void addPowerSourceTrue() {
        //ARRANGE
        HouseGrid hg1 = new HouseGrid("xpt1", 100);
        PowerSource ps1 = new PowerSource("eletric", 50);
        //ACT
        boolean result = hg1.addPowerSource(ps1);
        //ASSERT
        assertTrue(result);
    }

    @Test
    void addPowerSourceFalse() {
        //ARRANGE
        HouseGrid hg1 = new HouseGrid("xpt1", 0);
        PowerSource ps1 = new PowerSource("eletric", 50);
        PowerSource ps2 = new PowerSource("eletric", 50);
        //ACT
        hg1.addPowerSource(ps1);
        boolean result = hg1.addPowerSource(ps2);
        //ASSERT
        assertFalse(result);
    }

    @Test
    void teste1() {
        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        DishwasherType dishwasherType = new DishwasherType();
        Dishwasher dishwasher = new Dishwasher("d1", dishwasherSpec, dishwasherType);
        dishwasher.checkIfIsMetered();
    }

    @Test
    void totalNominalPowerSumOfRooms() {
        //Arrange
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        Room room = new Room("r1","classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        String program1 = "prog1";
        Double program2 = 20.0;
        Dishwasher dishwasher1 = new Dishwasher("dish1", new DishwasherSpec(), new DishwasherType());
        dishwasher1.getDeviceSpecs().setAttributeValue("nominal power", 100);
        dishwasher1.getDeviceSpecs().setAttributeValue("capacity", 10);
        dishwasher1.createProgramAndAddTo(program1, program2);
        Dishwasher dishwasher2 = new Dishwasher("dish2", new DishwasherSpec(), new DishwasherType());
        dishwasher2.getDeviceSpecs().setAttributeValue("nominal power", 100);
        dishwasher2.getDeviceSpecs().setAttributeValue("capacity", 10);
        dishwasher2.createProgramAndAddTo(program1, program2);
        //Act
        houseGridRepo.addHouseGrid(houseGrid);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        room.getListOfDevices().addDeviceToList(dishwasher1);
        room.getListOfDevices().addDeviceToList(dishwasher2);
        double result = roomHouseGridService.getNominalPower(houseGrid.getCode());
        double expectedResult = 200;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getConsumption() throws Exception {
        //Arrange
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        Room room = new Room("r1","classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","classroom", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        String program1 = "prog1";
        Double program2 = 20.0;
        Dishwasher dishwasher1 = new Dishwasher("dish1", new DishwasherSpec(), new DishwasherType());
        dishwasher1.getDeviceSpecs().setAttributeValue("nominal power", 100);
        dishwasher1.getDeviceSpecs().setAttributeValue("capacity", 10);
        dishwasher1.createProgramAndAddTo(program1, program2);
        Dishwasher dishwasher2 = new Dishwasher("dish2", new DishwasherSpec(), new DishwasherType());
        dishwasher2.getDeviceSpecs().setAttributeValue("nominal power", 100);
        dishwasher2.getDeviceSpecs().setAttributeValue("capacity", 10);
        dishwasher2.createProgramAndAddTo(program1, program2);

        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 21, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 22, 23, 59);

        ListOfReadings lr1 = new ListOfReadings();

        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);

        Reading r1 = new Reading(date1, 12);
        Reading r2 = new Reading(date2, 13);
        Reading r3 = new Reading(date3, 14);
        Reading r4 = new Reading(date4, 15);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        dishwasher1.setListOfReadings(lr1);
        dishwasher2.setListOfReadings(lr1);

        houseGridRepo.addHouseGrid(houseGrid);

        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);

        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);

        room.getListOfDevices().addDeviceToList(dishwasher1);
        room.getListOfDevices().addDeviceToList(dishwasher2);
        room2.getListOfDevices().addDeviceToList(dishwasher1);
        room2.getListOfDevices().addDeviceToList(dishwasher2);


        double expectedResult = (12 * 2 + 13 * 2 + 14 * 2 + 15 * 2) * 2;
        double result = roomHouseGridService.getConsumption(initialDate, finalDate,houseGrid.getCode());

        assertEquals(expectedResult, result);
    }

    @Test
    void addReadingToGridListOfReadings() throws IOException {

        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 07);
        Reading r1 = new Reading(date1, 7);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 12);
        Reading r2 = new Reading(date2, 9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 45);
        Reading r3 = new Reading(date3, 11);

        lRead1.addReadingToGridListOfReadings(r1);
        lRead1.addReadingToGridListOfReadings(r2);
        lRead1.addReadingToGridListOfReadings(r3);

        houseGrid.setListOfReadings(lRead1);

        assertEquals(11, lRead1.getListOfReading().get(0).getValue());
    }

    @Test
    void checkIfGetListOfReading() throws IOException {

        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 07);
        Reading r1 = new Reading(date1, 7);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 12);
        Reading r2 = new Reading(date2, 9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 45);
        Reading r3 = new Reading(date3, 11);

        lRead1.addReadingToGridListOfReadings(r1);
        lRead1.addReadingToGridListOfReadings(r2);
        lRead1.addReadingToGridListOfReadings(r3);

        houseGrid.setListOfReadings(lRead1);

        ListOfReadings result = houseGrid.getListOfReadings();

        ListOfReadings expectedResult = new ListOfReadings();
        expectedResult.addReadingToGridListOfReadings(r1);
        expectedResult.addReadingToGridListOfReadings(r2);
        expectedResult.addReadingToGridListOfReadings(r3);

        assertEquals(expectedResult.getListOfReading().get(0), result.getListOfReading().get(0));


    }


    @Test
    void getRoomByID() {
        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        houseGridRepo.addHouseGrid(houseGrid);
        Room d =roomHouseGridService.getRoomByName("sala",houseGrid.getCode());
        assertEquals(null,d);
    }

    @Test
    void getRoomByOK() {
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        Room room = new Room("Bed Room","classroom", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        room.setName("Bed Room");
        List<String> lr = new ArrayList<>();
        lr.add(room.getName());
        houseGrid.setRoomsList(lr);
        houseGridRepo.addHouseGrid(houseGrid);
        roomDomainService.addRoom(room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        assertEquals(room.getName(),roomHouseGridService.getRoomByName("Bed Room",houseGrid.getCode()).getName());
    }

    @Test
    void getRoomByKo() {
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        Room room = new Room("Bed Room","classroom", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        room.setName("Room");
        List<String> lr = new ArrayList<>();
        lr.add(room.getName());
        houseGrid.setRoomsList(lr);
        houseGridRepo.addHouseGrid(houseGrid);
        roomDomainService.addRoom(room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        Room result = roomHouseGridService.getRoomByName("Bed Room",houseGrid.getCode());
        assertNull(result);
    }

}