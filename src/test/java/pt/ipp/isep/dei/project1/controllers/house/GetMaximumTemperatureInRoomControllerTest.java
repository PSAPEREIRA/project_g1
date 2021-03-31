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
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetMaximumTemperatureInRoomControllerTest {



    @Mock
    private RoomRepository roomRepository;
    private RoomDomainService roomDomainService;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
    }

    @Test
    void testGetListOfRooms() throws Exception {
        //ARRANGE
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50, 50, 10), new FridgeType());
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        GetMaximumTemperatureInRoomController getMaximumTemperatureInRoomController = new GetMaximumTemperatureInRoomController(roomDomainService);
        //ACT
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(room.getName());
        expectedResult.add(room2.getName());
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        room.getListOfDevices().addDeviceToList(device);
        //ASSERT
        ListOfRoomsDto result = getMaximumTemperatureInRoomController.getListOfRooms();
        assertEquals(expectedResult.get(1), result.getRoomDto().get(1).getName());
    }

    @Test
    void testGetMaximumRoomTemperature() throws Exception {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Living Room", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Kitchen", "room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        RoomSensor roomSensor = new RoomSensor("1", "roomSensor",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 03, 9, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 9, 31);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 14.0));
        lisOfReading.addReading(new Reading(date2, 12.0));

        roomSensor.setListOfReadings(lisOfReading);

        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);

        r1.addSensor(roomSensor);

        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        GetMaximumTemperatureInRoomController us610c = new GetMaximumTemperatureInRoomController(roomDomainService);
        String[] result = us610c.getMaximumRoomTemperature(roomDto, LocalDate.of(2019, 1, 03));
        String expectedResult = String.valueOf(14.0);
        assertEquals(expectedResult, result[0]);
    }

    @Test
    void testGetMaximumRoomTemperature2Values() throws Exception {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Living Room", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));

        Room r2 = new Room("Kitchen", "room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();

        RoomSensor s1 = new RoomSensor("1", "sensor1",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 03, 9, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 9, 31);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 14.0));
        lisOfReading.addReading(new Reading(date2, 12.0));
        lisOfReading.addReading(new Reading(date2, 18.0));

        s1.setListOfReadings(lisOfReading);

        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);
        r1.addSensor(s1);
        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        GetMaximumTemperatureInRoomController us610c = new GetMaximumTemperatureInRoomController(roomDomainService);
        String[] result = us610c.getMaximumRoomTemperature(roomDto, LocalDate.of(2019, 1, 03));
        String expectedResult = String.valueOf(18.0);
        assertEquals(expectedResult, result[0]);
    }

    @Test
    void testGetSensorsInRoom() throws Exception {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Living Room", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));

        Room r2 = new Room("Kitchen", "room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();

        RoomSensor s1 = new RoomSensor("1", "sensor1",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 03, 9, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 9, 31);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 14.0));
        lisOfReading.addReading(new Reading(date2, 12.0));
        lisOfReading.addReading(new Reading(date2, 18.0));

        s1.setListOfReadings(lisOfReading);
        r1.addSensor(s1);

        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);
        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        GetMaximumTemperatureInRoomController us610c = new GetMaximumTemperatureInRoomController(roomDomainService);
        boolean result = us610c.checkIfListOfSensorsInRoomIsEmpty(roomDto);
        assertFalse(result);
    }

    @Test
    void testGetSensorsInRoomEmpty() throws Exception {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Living Room", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room r2 = new Room("Kitchen", "room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        List<Room> listOfRooms = new ArrayList<>();

        RoomSensor s1 = new RoomSensor("1", "sensor1",
                new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");

        LocalDateTime date1 = LocalDateTime.of(2019, 01, 03, 9, 30);
        LocalDateTime date2 = LocalDateTime.of(2019, 01, 03, 9, 31);

        ListOfReadings lisOfReading = new ListOfReadings();
        lisOfReading.addReading(new Reading(date1, 14.0));
        lisOfReading.addReading(new Reading(date2, 12.0));
        lisOfReading.addReading(new Reading(date2, 18.0));

        s1.setListOfReadings(lisOfReading);
        roomDomainService.addRoom(r1);
        roomDomainService.addRoom(r2);
        listOfRooms.add(r1);
        listOfRooms.add(r2);
        RoomDto roomDto = MapperRoom.toDto(r1);
        GetMaximumTemperatureInRoomController us610c = new GetMaximumTemperatureInRoomController(roomDomainService);
        boolean result = us610c.checkIfListOfSensorsInRoomIsEmpty(roomDto);
        assertTrue(result);
    }

}
