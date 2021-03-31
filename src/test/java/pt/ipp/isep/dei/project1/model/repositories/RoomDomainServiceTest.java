package pt.ipp.isep.dei.project1.model.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.mapper.MapperRoomSensor;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeater;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterSpec;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterType;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.device.lamp.Lamp;
import pt.ipp.isep.dei.project1.model.device.lamp.LampSpec;
import pt.ipp.isep.dei.project1.model.device.lamp.LampType;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachine;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachineSpec;
import pt.ipp.isep.dei.project1.model.device.washingmachine.WashingMachineType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class RoomDomainServiceTest {


    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
    }

    @Test
    void setListOfRoomsOfHouseTest() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room R1 = new Room("bedRoom", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        Room R2 = new Room("livingRoom", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(R1);
        listOfRooms.add(R2);
        roomDomainService.setListOfRooms(listOfRooms);
        List<Room> result = roomDomainService.getListOfRooms();
        assertEquals(listOfRooms, result);
    }


    @Test
    void checkGetListOfAllDevicesInsideHouseV2() {
        //ARRANGE
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);

        Room R1 = new Room("Bed Room", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        Room R2 = new Room("Living Room", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room R3 = new Room("Garage", "room", 0, dimension.get(0), dimension.get(1), dimension.get(2));

        Dishwasher d1 = new Dishwasher("dishwasher", new DishwasherSpec(), new DishwasherType());
        ElectricWaterHeater d2 = new ElectricWaterHeater("electric Water Heater",
                new ElectricWaterHeaterSpec(), new ElectricWaterHeaterType());
        Fridge d3 = new Fridge("Fridge", new FridgeSpec(), new FridgeType());
        Lamp d4 = new Lamp("Lamp", new LampSpec(), new LampType());
        WashingMachine d5 = new WashingMachine("Washing Machine", new WashingMachineSpec(), new WashingMachineType());

        R1.getListOfDevices().addDeviceToList(d1);
        R1.getListOfDevices().addDeviceToList(d2);
        R1.getListOfDevices().addDeviceToList(d3);
        R2.getListOfDevices().addDeviceToList(d4);
        R3.getListOfDevices().addDeviceToList(d5);


        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(R1);
        listOfRooms.add(R2);
        listOfRooms.add(R3);


        roomDomainService.setListOfRooms(listOfRooms);

        ListOfDevices result = roomDomainService.getListOfAllDevicesInsideHouse();

        ListOfDevices expectedResult = new ListOfDevices();
        expectedResult.addDeviceToList(d1);
        expectedResult.addDeviceToList(d2);
        expectedResult.addDeviceToList(d3);
        expectedResult.addDeviceToList(d4);
        expectedResult.addDeviceToList(d5);

        assertEquals(expectedResult.getDeviceList(), result.getDeviceList());
    }

    @Test
    void checkListOfDeviceTypes() throws Exception {

        List<DeviceType> result = roomDomainService.getListOfDeviceTypes();

        List<DeviceType> expectedResult = new ArrayList<>();

        expectedResult.add(new WashingMachineType());
        expectedResult.add(new LampType());
        expectedResult.add(new FridgeType());
        expectedResult.add(new ElectricWaterHeaterType());
        expectedResult.add(new DishwasherType());


        assertEquals(expectedResult.size(), result.size() - 9);
    }


    @Test
    void createNewRoomTest() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);

        //Act
        Room result = new Room("Bed Room", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        Room expectedResult = new Room("Bed Room", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getRoomByID() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        room1.setName("bedroom");
        roomDomainService.addRoom(room1);
        Room expectedResult = room1;
        Room result = roomDomainService.getRoomByName("bedroom");
        assertEquals(expectedResult, result);
    }

    @Test
    void getRoomByNameNull() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        room1.setName("bedroom");
        roomDomainService.addRoom(room1);
        Room result = roomDomainService.getRoomByName("livingRoom");
        assertEquals(null, result);
    }

    @Test
    public void getAllSensorInHouseTest() {
        //Arrange

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("toilet", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);

        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(room1);
        listOfRooms.add(room2);
        roomDomainService.setListOfRooms(listOfRooms);

        RoomSensor s1 = new RoomSensor("1", "sensor Teste", new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        RoomSensor s2 = new RoomSensor("1", "sensor Teste", new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        room1.addSensor(s1);
        room2.addSensor(s2);
        //ACT
        List<RoomSensor> result = roomDomainService.getAllSensors();
        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void addSensorToListFalse() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(room1);
        RoomSensor s1 = new RoomSensor("1", "sensor Teste", new SensorType("rainfall"), LocalDate.of(2018, 12, 10), "l/m2");
        RoomSensor s2 = new RoomSensor("2", "sensor ", new SensorType("Temperature"), LocalDate.of(2018, 12, 11), "l/m2");

        s1.setRoom(room1);
        s2.setRoom(room1);
        roomDomainService.addSensorToList(s1);
        roomDomainService.addSensorToList(s2);
        boolean result = roomDomainService.addSensorToList(s1);
        assertFalse(result);
    }

    @Test
    void getRoomsAttachedInHouseGrid() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("toilet", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        room1.setHouseGrid("grid1");
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        List<Room> result = roomDomainService.getRoomsAttachedToHouseGrid("grid1");
        assertEquals(1, result.size());
    }

    @Test
    void getRoomsAttachedInHouseGrid2() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("toilet", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        room1.setHouseGrid("grid2");
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        List<Room> result = roomDomainService.getRoomsAttachedToHouseGrid("grid1");
        assertEquals(0, result.size());
    }

    @Test
    void getRoomsAttachedInHouseGrid3() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("toilet", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        room1.setHouseGrid("grid2");
        room2.setHouseGrid("grid2");
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        List<Room> result = roomDomainService.getRoomsAttachedToHouseGrid("grid2");
        List<Room> expectedResult = new ArrayList<>();
        expectedResult.add(room1);
        expectedResult.add(room2);
        assertEquals(expectedResult.size(), result.size());
    }

    @Test
    void testGetRoomsDTO() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("toilet", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        //Act
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        ListOfRoomsDto result = roomDomainService.getRoomsDTO();
        ListOfRoomsDto expectedResult = new ListOfRoomsDto();
        expectedResult.getRoomDto().add(MapperRoom.toDto(room1));
        expectedResult.getRoomDto().add(MapperRoom.toDto(room2));
        //Assert
        assertEquals(expectedResult.getRoomDto().get(0).getName(), result.getRoomDto().get(0).getName());
    }

    @Test
    void testGetRoomsDTOEmpty() {
        //Act
        ListOfRoomsDto result = roomDomainService.getRoomsDTO();
        ListOfRoomsDto expectedResult = new ListOfRoomsDto();
        //Assert
        assertEquals(expectedResult.getRoomDto().size(), result.getRoomDto().size());
    }


    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortNonRoomExist() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        List<Double> listHouseAreaTemp = new ArrayList<>();
        RoomDto room1Dto = MapperRoom.toDto(new Room("bedroom2", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2)));
        int cat = 1;
        String option = "Higher";
        LocalDate start = LocalDate.of(2018, 12, 31);
        //Act
        room1.addSensor(roomSensor);
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 0, 0), 24));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 1, 0, 0), 25));
        listHouseAreaTemp.add(18.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        roomDomainService.addRoom(room1);
        List<LocalDateTime> result = roomDomainService.getInstancesWithTemperatureHigherLowerComfort(room1Dto, listHouseAreaTemp, cat, option, start);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortNone() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        List<Double> listHouseAreaTemp = new ArrayList<>();
        RoomDto room1Dto = MapperRoom.toDto(room1);
        int cat = 1;
        String option = "Higher";
        LocalDate start = LocalDate.of(2018, 12, 31);
        //Act
        room1.addSensor(roomSensor);
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 0, 0), 24));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 1, 0, 0), 25));
        listHouseAreaTemp.add(18.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        roomDomainService.addRoom(room1);
        List<LocalDateTime> result = roomDomainService.getInstancesWithTemperatureHigherLowerComfort(room1Dto, listHouseAreaTemp, cat, option, start);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortOneSuperior() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        List<Double> listHouseAreaTemp = new ArrayList<>();
        RoomDto room1Dto = MapperRoom.toDto(room1);
        int cat = 1;
        String option = "Higher";
        LocalDate start = LocalDate.of(2018, 12, 31);
        //Act
        room1.addSensor(roomSensor);
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 0, 0), 28));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 1, 0, 0), 25));
        listHouseAreaTemp.add(18.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        roomDomainService.addRoom(room1);
        List<LocalDateTime> result = roomDomainService.getInstancesWithTemperatureHigherLowerComfort(room1Dto, listHouseAreaTemp, cat, option, start);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(0).getDateTime());
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortOneInferior() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        List<Double> listHouseAreaTemp = new ArrayList<>();
        RoomDto room1Dto = MapperRoom.toDto(room1);
        int cat = 1;
        String option = "Lower";
        LocalDate start = LocalDate.of(2018, 12, 31);
        //Act
        room1.addSensor(roomSensor);
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 0, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 1, 0, 0), 15));
        listHouseAreaTemp.add(18.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        roomDomainService.addRoom(room1);
        List<LocalDateTime> result = roomDomainService.getInstancesWithTemperatureHigherLowerComfort(room1Dto, listHouseAreaTemp, cat, option, start);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(0).getDateTime());
        assertEquals(expectedResult, result);
    }


    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortManyInferior() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        List<Double> listHouseAreaTemp = new ArrayList<>();
        RoomDto room1Dto = MapperRoom.toDto(room1);
        int cat = 1;
        String option = "Lower";
        LocalDate start = LocalDate.of(2018, 12, 31);
        //Act
        room1.addSensor(roomSensor);
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 15));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        listHouseAreaTemp.add(18.0);
        listHouseAreaTemp.add(20.0);
        listHouseAreaTemp.add(25.0);
        roomDomainService.addRoom(room1);
        List<LocalDateTime> result = roomDomainService.getInstancesWithTemperatureHigherLowerComfort(room1Dto, listHouseAreaTemp, cat, option, start);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(0).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(1).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(2).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(3).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(4).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(5).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(6).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(7).getDateTime());

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortManyInferiorCat2() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        List<Double> listHouseAreaTemp = new ArrayList<>();
        RoomDto room1Dto = MapperRoom.toDto(room1);
        int cat = 2;
        String option = "Lower";
        LocalDate start = LocalDate.of(2018, 12, 31);
        //Act
        room1.addSensor(roomSensor);
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 26));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        listHouseAreaTemp.add(18.0);
        listHouseAreaTemp.add(30.0);
        listHouseAreaTemp.add(25.0);
        roomDomainService.addRoom(room1);
        List<LocalDateTime> result = roomDomainService.getInstancesWithTemperatureHigherLowerComfort(room1Dto, listHouseAreaTemp, cat, option, start);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(0).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(1).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(2).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(3).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(4).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(6).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(7).getDateTime());

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInstancesWithTemperatureHigherLowerComfortManyInferiorCat3() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        List<Double> listHouseAreaTemp = new ArrayList<>();
        RoomDto room1Dto = MapperRoom.toDto(room1);
        int cat = 3;
        String option = "Lower";
        LocalDate start = LocalDate.of(2018, 12, 31);
        //Act
        room1.addSensor(roomSensor);
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 25));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        listHouseAreaTemp.add(18.0);
        listHouseAreaTemp.add(30.0);
        listHouseAreaTemp.add(25.0);
        roomDomainService.addRoom(room1);
        List<LocalDateTime> result = roomDomainService.getInstancesWithTemperatureHigherLowerComfort(room1Dto, listHouseAreaTemp, cat, option, start);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(0).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(1).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(2).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(3).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(4).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(6).getDateTime());
        expectedResult.add(roomSensor.getListOfReadings().getListOfReading().get(7).getDateTime());

        assertEquals(expectedResult, result);
    }


    @Test
    void newRoomFalse() {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomDto room = MapperRoom.toDto(room1);
        room.setName("");

        Boolean result = roomDomainService.newRoom(room);

        assertFalse(result);

    }

    @Test
    void newRoomTrue() {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomDto room = MapperRoom.toDto(room1);

        Boolean result = roomDomainService.newRoom(room);

        assertTrue(result);

    }


    @Test
    void getRoomByNameDto() {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("kitchen", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room3 = new Room("room", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        RoomDto result = roomDomainService.getRoomByNameDto("checkFileReader");

        assertEquals(null, result);

    }

    @Test
    void getRoomByNameDto2() {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("kitchen", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room3 = new Room("room", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        RoomDto result = roomDomainService.getRoomByNameDto("kitchen");

        assertEquals("kitchen", result.getName());
    }

    @Test
    void setNewRoomAttributeName() {
        Room room = new Room("room1", "bed", 1, 1, 1, 1);
        Room room2 = new Room("room2", "bed", 1, 1, 1, 1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setDescription("new");
        RoomDto roomDto1 = roomDomainService.editRoom("room1", roomDto);
        assertEquals("new", roomDto1.getDescription());
    }

    @Test
    void setNewRoomAttributeName2() {
        Room room = new Room("room1", "bed", 1, 1, 1, 1);
        Room room2 = new Room("room2", "bed", 1, 1, 1, 1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setHouseFloor(20);
        RoomDto roomDto1 = roomDomainService.editRoom("room1", roomDto);
        assertEquals(20, roomDto1.getHouseFloor());
    }

    @Test
    void setNewRoomAttributeName3() {
        Room room = new Room("room1", "bed", 1, 1, 1, 1);
        Room room2 = new Room("room2", "bed", 1, 1, 1, 1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setHeight(20);
        RoomDto roomDto1 = roomDomainService.editRoom("room1", roomDto);
        assertEquals(20, roomDto1.getHeight());
    }

    @Test
    void setNewRoomAttributeName4() {
        Room room = new Room("room1", "bed", 1, 1, 1, 1);
        Room room2 = new Room("room2", "bed", 1, 1, 1, 1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setLength(20);
        RoomDto roomDto1 = roomDomainService.editRoom("room1", roomDto);
        assertEquals(20, roomDto1.getLength());
    }

    @Test
    void setNewRoomAttributeName5() {
        Room room = new Room("room1", "bed", 1, 1, 1, 1);
        Room room2 = new Room("room2", "bed", 1, 1, 1, 1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setWidth(20);
        RoomDto roomDto1 = roomDomainService.editRoom("room1", roomDto);
        assertEquals(20, roomDto1.getWidth());
    }

    @Test
    void getListOfAllSensorsInARoomDto() {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room2 = new Room("kitchen", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        Room room3 = new Room("room", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        List<RoomSensorDto> result = roomDomainService.getListOfAllSensorsInARoomDto("kitchen");

        assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    void testCreateAndAddNewSensor() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor s1 = new RoomSensor("12", "Sensor1", new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        roomDomainService.addRoom(room1);
        int result = roomDomainService.createAndAddSensor(MapperRoomSensor.toDto(s1), room1.getName());
        assertEquals(1, result);
    }

    @Test
    void testCreateAndAddNewSensorFalse() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor s1 = new RoomSensor("12", "Sensor1", new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        roomDomainService.addRoom(room1);
        roomDomainService.createAndAddSensor(MapperRoomSensor.toDto(s1), room1.getName());
        assertEquals(1, roomDomainService.getRoomByName(room1.getName()).getListOfSensors().size());
    }


    @Test
    void testCreateAndAddNewSensorAlreadyExists() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor s1 = new RoomSensor("12", "Sensor1", new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        roomDomainService.addRoom(room1);
        roomDomainService.createAndAddSensor(MapperRoomSensor.toDto(s1), room1.getName());
        int result = roomDomainService.createAndAddSensor(MapperRoomSensor.toDto(s1), room1.getName());
        assertEquals(-2, result);
    }


    @Test
    void checkListOfSensorsEmpty() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor s1 = new RoomSensor("12", "Sensor1", new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        room1.addSensor(s1);
        roomDomainService.addRoom(room1);
        boolean result = roomDomainService.checkListOfSensorsEmpty(room1.getName());
        assertFalse(result);
    }

    @Test
    void checkListOfSensorsEmpty2() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor s1 = new RoomSensor("12", "Sensor1", new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        roomDomainService.addRoom(room1);
        boolean result = roomDomainService.checkListOfSensorsEmpty(room1.getName());
        assertTrue(result);
    }

    @Test
    void checkTemperatureSensorsOfRoomV2TrueTest() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor s1 = new RoomSensor("12", "Sensor1", new SensorType("temperature"), LocalDate.of(2018, 01, 01), "ºC");
        room1.addSensor(s1);
        roomDomainService.addRoom(room1);
        boolean result = roomDomainService.checkTemperatureSensorsOfRoomV2(room1.getName());
        assertTrue(result);
    }

    @Test
    void checkTemperatureSensorsOfRoomV2FalseTest() {
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor s1 = new RoomSensor("12", "Sensor1", new SensorType("rainfall"), LocalDate.of(2018, 01, 01), "ºC");
        room1.addSensor(s1);
        boolean result = roomDomainService.checkTemperatureSensorsOfRoomV2(room1.getName());
        assertFalse(result);
    }


    @Test
    void getMaxRoomTemperatureHappy() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        RoomDto room1Dto = MapperRoom.toDto(room1);
        LocalDate date = LocalDate.of(2018, 12, 31);
        String[] temp;
        //Act
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 25));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        room1.addSensor(roomSensor);
        roomDomainService.addRoom(room1);
        String[] result = roomDomainService.getMaxRoomTemperature(room1Dto, date);
        String[] expectedResult = new String[2];
        expectedResult[0] = "7.0";
        expectedResult[1] = roomSensor.getUnit();
        //Assert
        assertArrayEquals(expectedResult, result);
    }

    @Test
    void getMaxRoomTemperatureHappyFixFloats() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        RoomDto room1Dto = MapperRoom.toDto(room1);
        LocalDate date = LocalDate.of(2018, 12, 31);
        String[] temp;
        //Act
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 25));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 21));
        room1.addSensor(roomSensor);
        roomDomainService.addRoom(room1);
        String[] result = roomDomainService.getMaxRoomTemperature(room1Dto, date);
        String[] expectedResult = new String[2];
        expectedResult[0] = "21.0";
        expectedResult[1] = roomSensor.getUnit();
        //Assert
        assertArrayEquals(expectedResult, result);
    }

    @Test
    void getMaxRoomTemperatureSadNoTempSensors() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("rainfall"), LocalDate.of(2018, 10, 10), "C");
        RoomDto room1Dto = MapperRoom.toDto(room1);
        LocalDate date = LocalDate.of(2018, 12, 31);
        String[] temp;
        //Act
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 25));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        room1.addSensor(roomSensor);
        roomDomainService.addRoom(room1);
        String[] result = roomDomainService.getMaxRoomTemperature(room1Dto, date);
        String[] expectedResult = new String[2];
        expectedResult[0] = "There are no temperature sensors in this room";
        expectedResult[1] = null;
        //Assert
        assertArrayEquals(expectedResult, result);
    }

    @Test
    void getMaxRoomTemperatureSadWrongDate() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        RoomDto room1Dto = MapperRoom.toDto(room1);
        LocalDate date = LocalDate.of(2013, 12, 31);
        String[] temp;
        //Act
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 25));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        room1.addSensor(roomSensor);
        roomDomainService.addRoom(room1);
        String[] result = roomDomainService.getMaxRoomTemperature(room1Dto, date);
        String[] expectedResult = new String[2];
        expectedResult[0] = "There are no readings on that date";
        expectedResult[1] = null;
        //Assert
        assertArrayEquals(expectedResult, result);
    }

    @Test
    void getMaxRoomTemperatureSadNoReadings() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        RoomDto room1Dto = MapperRoom.toDto(room1);
        LocalDate date = LocalDate.of(2018, 12, 31);
        String[] temp;
        //Act
        room1.addSensor(roomSensor);
        roomDomainService.addRoom(room1);
        String[] result = roomDomainService.getMaxRoomTemperature(room1Dto, date);
        String[] expectedResult = new String[2];
        expectedResult[0] = "There are no readings yet";
        expectedResult[1] = null;
        //Assert
        assertArrayEquals(expectedResult, result);
    }

    @Test
    void getCurrentTemperatureHappy() {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("bedroom", "room", 2, dimension.get(0), dimension.get(1), dimension.get(2));
        RoomSensor roomSensor = new RoomSensor("10", "s1", new SensorType("temperature"), LocalDate.of(2018, 10, 10), "C");
        RoomDto room1Dto = MapperRoom.toDto(room1);
        //Act
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 2, 0), 6));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 3, 0), 4));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2018, 12, 31, 4, 0), 7));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 2, 0), 8));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 3, 0), 10));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 4, 0), 25));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 1, 5, 0), 14));
        roomSensor.getListOfReadings().addReading(new Reading(LocalDateTime.of(2019, 1, 2, 0, 0), 6));
        room1.addSensor(roomSensor);
        roomDomainService.addRoom(room1);
        String[] result = roomDomainService.getCurrentTemperature(room1Dto.getName());
        String[] expectedResult = new String[2];
        expectedResult[0] = "6.0";
        expectedResult[1] = roomSensor.getUnit();
        //Assert
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void editRoom() {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);

        Room room = new Room("bedroom", "room", 1, 1, 1, 1);
        roomDomainService.addRoom(room);

        RoomDto roomDto = new RoomDto();
        //   roomDto.setDescription("newRoom");
        //   roomDto.setHouseFloor(2);
        //   roomDto.setHeight(2);
        roomDto.setLength(2);
        //    roomDto.setWidth(2);

        roomDomainService.editRoom("bedroom", roomDto);

        //assertEquals("newRoom",room.getDescription());
        assertEquals(2, room.getLength());

    }
}




