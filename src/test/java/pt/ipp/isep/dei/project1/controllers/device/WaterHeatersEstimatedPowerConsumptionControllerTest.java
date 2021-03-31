package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WaterHeatersEstimatedPowerConsumptionControllerTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    private HouseGridRepo houseGridRepo;

    @Mock
    private RoomRepository roomRepository;
    private RoomDomainService roomDomainService;
    private RoomHouseGridService roomHouseGridService;

    @BeforeEach
    void initUseCase() throws Exception {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomDomainService = new RoomDomainService(roomRepository);
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
        roomDomainService.addRoom(room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        ListOfHouseGridsDto result = ctrl752.getListOfHouseGrid();
        ListOfHouseGridsDto expectedResult = MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());

        //ASSERT
        assertEquals(expectedResult.getListOfHouseGridDto().size(), result.getListOfHouseGridDto().size());

    }

    @Test
    void getRoomFromHouseGrid() throws Exception {
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
        roomDomainService.addRoom(room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        ListOfRoomsDto result = ctrl752.getListOfRooms(houseGridDto);
        ListOfRoomsDto expectedResult = MapperListOfRooms.toDto(roomHouseGridService.getRoomsConnectedToHouseGrid(houseGrid.getRoomsList()));

        //ASSERT
        assertEquals(expectedResult.getRoomDto().size(), result.getRoomDto().size());

    }


    @Test
    void testWaterHeatersEstimatedPowerConsumption2Rooms2WaterHeater() throws Exception {
        //Arrange
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();
        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));


        List<String[]> programs = new ArrayList<>();
        String[] program1 = new String[2];
        program1[0] = "prog1";
        program1[1] = "20";
        programs.add(program1);

        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele2");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);


        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        ElectricWaterHeater device2 = electricWaterHeaterType.createNewDevice("ele1");
        device2.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device2.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device2.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device2.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);

        Dishwasher device3 = dishwasherType.createNewDevice("dish2");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish3");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        room1.getListOfDevices().addDeviceToList(device);
        room1.getListOfDevices().addDeviceToList(device1);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device3);
        room2.getListOfDevices().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);
        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(35.0);
        specificAttributeValues.add(15.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        double result = ctrl752.electricHeatingWaterDevicesDayConsumptionEstimation(specificAttributeValues);
        //ASSERT
        assertEquals(10048, Math.round(result));
    }

    @Test
    void testWaterHeatersEstimatedConsumptionWaterHeaters2Rooms1WaterHeater() throws Exception {
        //Arrange
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);

        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));

        Dishwasher device = dishwasherType.createNewDevice("di23");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("capacity", 50);

        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        ElectricWaterHeater device2 = electricWaterHeaterType.createNewDevice("ele1");
        device2.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device2.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device2.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device2.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);

        Dishwasher device3 = dishwasherType.createNewDevice("dish2");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish3");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        room1.getListOfDevices().addDeviceToList(device);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device3);
        room2.getListOfDevices().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);
        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(35.0);
        specificAttributeValues.add(15.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        ctrl752.checkElectricWaterHeatersListCreation();
        double result = ctrl752.electricHeatingWaterDevicesDayConsumptionEstimation(specificAttributeValues);
        //ASSERT
        assertEquals(5024, Math.round(result));
    }

    @Test
    void checkElectricWaterHeatersListCreationFalse()  {
        //Arrange
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();
        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));


        Dishwasher device = dishwasherType.createNewDevice("di23");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("capacity", 50);

        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        ElectricWaterHeater device2 = electricWaterHeaterType.createNewDevice("ele1");
        device2.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device2.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device2.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device2.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);

        Dishwasher device3 = dishwasherType.createNewDevice("dish2");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish3");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        room1.getListOfDevices().addDeviceToList(device);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device3);
        room2.getListOfDevices().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);

        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(35.0);
        specificAttributeValues.add(15.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        ctrl752.checkElectricWaterHeatersListCreation();
        boolean result = ctrl752.checkElectricWaterHeatersListCreation();
        //ASSERT
        assertFalse(result);
    }

    @Test
    void checkElectricWaterHeatersListCreationTrue() throws Exception {
        //Arrange
        DishwasherType dishwasherType = new DishwasherType();

        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        ListOfDevices listOfDevices1 = new ListOfDevices();
        ListOfDevices listOfDevices2 = new ListOfDevices();

        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        Dishwasher device3 = dishwasherType.createNewDevice("dish2");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish3");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        listOfHouseGrids.add(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        room1.getListOfDevices().addDeviceToList(device1);
        room2.getListOfDevices().addDeviceToList(device3);
        room2.getListOfDevices().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);

        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(35.0);
        specificAttributeValues.add(15.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        ctrl752.checkElectricWaterHeatersListCreation();
        boolean result = ctrl752.checkElectricWaterHeatersListCreation();
        //ASSERT
        assertTrue(result);
    }


    @Test
    void testWaterHeatersEstimatedConsumptionWaterHeatersHotColdWaterInvertedValues() throws Exception {
        //Arrange
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();
        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        ListOfDevices listOfDevices1 = new ListOfDevices();
        ListOfDevices listOfDevices2 = new ListOfDevices();

        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele1");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 20);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);


        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        Dishwasher device2 = dishwasherType.createNewDevice("dish2");
        device2.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device2.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device3 = dishwasherType.createNewDevice("dish3");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish4");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        room1.getListOfDevices().addDeviceToList(device);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device3);
        room2.getListOfDevices().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);

        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(15.0);
        specificAttributeValues.add(35.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        double result = ctrl752.electricHeatingWaterDevicesDayConsumptionEstimation(specificAttributeValues);
        //ASSERT
        assertEquals(5024, Math.round(result));
    }

    @Test
    void testWaterHeatersEstimatedConsumptionWaterHeatersHotColdWaterInvertedValues2() throws Exception {
        //Arrange
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();
        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        ListOfDevices listOfDevices1 = new ListOfDevices();
        ListOfDevices listOfDevices2 = new ListOfDevices();

        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele1");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 20);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);


        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        Dishwasher device2 = dishwasherType.createNewDevice("dish2");
        device2.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device2.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device3 = dishwasherType.createNewDevice("dish3");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish4");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        room1.getListOfDevices().addDeviceToList(device);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device3);
        room2.getListOfDevices().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);
        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(15.0);
        specificAttributeValues.add(15.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        double result = ctrl752.electricHeatingWaterDevicesDayConsumptionEstimation(specificAttributeValues);
        //ASSERT
        assertEquals(0, Math.round(result));
    }

    @Test
        //parserDate empty collections
    void testWaterHeatersEstimatedConsumptionWaterHeatersHotColdWaterInvertedValuesEmptyList() throws Exception {
        //Arrange
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        ListOfDevices listOfDevices1 = new ListOfDevices();
        ListOfDevices listOfDevices2 = new ListOfDevices();

        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele1");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 20);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);


        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        Dishwasher device2 = dishwasherType.createNewDevice("dish2");
        device2.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device2.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device3 = dishwasherType.createNewDevice("dish3");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish4");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        listOfHouseGrids.add(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        //listOfDevices1.getDeviceList().addDeviceToList(device);
        //listOfDevices1.getDeviceList().addDeviceToList(device2);
        //listOfDevices2.getDeviceList().addDeviceToList(device3);
        //listOfDevices2.getDeviceList().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);

        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(15.0);
        specificAttributeValues.add(35.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        double result = ctrl752.electricHeatingWaterDevicesDayConsumptionEstimation(specificAttributeValues);
        //ASSERT
        assertEquals(-1, Math.round(result));
    }



    @Test
    void checkIfElectricWaterHeatersListCreationIsEmpty() throws Exception {
        //Arrange
        ElectricWaterHeaterType electricWaterHeaterType = new ElectricWaterHeaterType();
        DishwasherType dishwasherType = new DishwasherType();
        HouseGrid electricPowerGrid = new HouseGrid("xpt1", 100);
        List<String> listOfRooms = new ArrayList<>();
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room1 = new Room("xpto1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));


        List<String[]> programs = new ArrayList<>();
        String[] program1 = new String[2];
        program1[0] = "prog1";
        program1[1] = "20";
        programs.add(program1);

        ElectricWaterHeater device = electricWaterHeaterType.createNewDevice("ele2");
        device.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);


        Dishwasher device1 = dishwasherType.createNewDevice("dish1");
        device1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device1.getDeviceSpecs().setAttributeValue("capacity", 50);

        ElectricWaterHeater device2 = electricWaterHeaterType.createNewDevice("ele1");
        device2.getDeviceSpecs().setAttributeValue("nominal power", 10);
        device2.getDeviceSpecs().setAttributeValue("volume of water", 50);
        device2.getDeviceSpecs().setAttributeValue("hot water temperature", 60);
        device2.getDeviceSpecs().setAttributeValue("performance ratio", 0.9);

        Dishwasher device3 = dishwasherType.createNewDevice("dish2");
        device3.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device3.getDeviceSpecs().setAttributeValue("capacity", 10);

        Dishwasher device4 = dishwasherType.createNewDevice("dish3");
        device4.getDeviceSpecs().setAttributeValue("nominal power", 100);
        device4.getDeviceSpecs().setAttributeValue("capacity", 10);

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        room1.getListOfDevices().addDeviceToList(device);
        room1.getListOfDevices().addDeviceToList(device1);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device3);
        room2.getListOfDevices().addDeviceToList(device4);
        electricPowerGrid.setRoomsList(listOfRooms);
        List<Double> specificAttributeValues = new ArrayList<>();
        specificAttributeValues.add(10.0);
        specificAttributeValues.add(35.0);
        specificAttributeValues.add(15.0);
        WaterHeatersEstimatedPowerConsumptionController ctrl752 = new WaterHeatersEstimatedPowerConsumptionController(houseGridRepo,roomHouseGridService);
        //ACT
        boolean result = ctrl752.checkElectricWaterHeatersListCreation();
        //ASSERT

        assertFalse(result);
    }



}