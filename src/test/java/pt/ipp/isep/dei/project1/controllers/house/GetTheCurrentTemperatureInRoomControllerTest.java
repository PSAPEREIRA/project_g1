package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class GetTheCurrentTemperatureInRoomControllerTest {

    @Mock
    private RoomRepository roomRepository;

    private RoomDomainService roomDomainService;
    private GetTheCurrentTemperatureInRoomController getTheCurrentTemperatureInRoomController;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        getTheCurrentTemperatureInRoomController = new GetTheCurrentTemperatureInRoomController(roomDomainService);
    }


    @Test
    void testGetListOfRooms() throws Exception {
        //ARRANGE
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50, 50, 10), new FridgeType());
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r2", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        //ACT
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(room.getName());
        expectedResult.add(room2.getName());
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        room.getListOfDevices().addDeviceToList(device);
        //ASSERT
        ListOfRoomsDto result = getTheCurrentTemperatureInRoomController.getListOfRooms();
        assertEquals(expectedResult.get(1), result.getRoomDto().get(1).getName());
    }


    @Test
    void testCheckIfListOfSensorIsEmpty() throws Exception {
        //ARRANGE

        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50, 50, 10), new FridgeType());
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r2", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        //ACT;
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        room.getListOfDevices().addDeviceToList(device);
        RoomDto roomDto = MapperRoom.toDto(room);
        //ASSERT
        boolean result = getTheCurrentTemperatureInRoomController.checkIfListOfSensorsInRoomIsEmpty(roomDto);
        assertTrue(result);
    }


    @Test
    void getCurrentRoomTemperature() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room r1 = new Room("Living Room", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room r2 = new Room("Kitchen", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        RoomSensor s1 = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "??C");
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        //Act
        lisOfReading.addReading(new Reading(date1, 12.0));
        lisOfReading.addReading(new Reading(date2, 12.5));
        s1.setListOfReadings(lisOfReading);
        r1.addSensor(s1);
        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);
        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        String[] result = getTheCurrentTemperatureInRoomController.getCurrentRoomTemperature(roomDto);
        String expectedResult = String.valueOf(12.5);
        //Assert
        assertEquals(expectedResult, result[0]);
    }

    @Test
    void getCurrentRoomTemperatureNegative() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room r1 = new Room("Living Room", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room r2 = new Room("Kitchen", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        RoomSensor s1 = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "??C");
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        ListOfReadings lisOfReading = new ListOfReadings();
        //Act
        lisOfReading.addReading(new Reading(date1, -5));
        lisOfReading.addReading(new Reading(date2, -3));
        s1.setListOfReadings(lisOfReading);
        r1.addSensor(s1);
        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);
        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        String[] result = getTheCurrentTemperatureInRoomController.getCurrentRoomTemperature(roomDto);
        String expectedResult = String.valueOf(-3.0);
        //Assert
        assertEquals(expectedResult, result[0]);
    }

    @Test
    void getCurrentRoomTemperatureWithoutReadings() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room r1 = new Room("Living Room", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room r2 = new Room("Kitchen", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        RoomSensor s1 = new RoomSensor("1", "sensor1", new SensorType("temperature"), LocalDate.of(2018, 12, 10), "??C");
        ListOfReadings lisOfReading = new ListOfReadings();
        s1.setListOfReadings(lisOfReading);
        //Act
        r1.addSensor(s1);
        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);
        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        String[] result = getTheCurrentTemperatureInRoomController.getCurrentRoomTemperature(roomDto);
        String expectedResult = "There are no readings yet";
        //Assert
        assertEquals(result[0], expectedResult);
    }

    @Test
    void getCurrentRoomTemperatureWithoutSensorsOfTemperature() throws Exception {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("Porto", "city", new OccupationArea(new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room r1 = new Room("Living Room", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room r2 = new Room("Kitchen", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        RoomSensor s1 = new RoomSensor("1", "sensor1", new SensorType("Rainfall"), LocalDate.of(2018, 12, 10), "??C");
        ListOfReadings lisOfReading = new ListOfReadings();
        //Act
        s1.setListOfReadings(lisOfReading);
        r1.addSensor(s1);
        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);
        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        String[] result = getTheCurrentTemperatureInRoomController.getCurrentRoomTemperature(roomDto);
        String expectedResult = "There are no temperature sensors in this room";
        //Assert
        assertEquals(expectedResult, result[0]);
    }

}