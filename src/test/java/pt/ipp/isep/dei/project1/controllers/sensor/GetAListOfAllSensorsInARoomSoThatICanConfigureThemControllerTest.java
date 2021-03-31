package pt.ipp.isep.dei.project1.controllers.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfRoomSensorsDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class GetAListOfAllSensorsInARoomSoThatICanConfigureThemControllerTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    GetAListOfAllSensorsInARoomSoThatICanConfigureThemController usc250;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        usc250 = new GetAListOfAllSensorsInARoomSoThatICanConfigureThemController(roomDomainService);
    }

    @Test
    void getListOfSensorsInARoomFullListTest() throws Exception {
        //ARRANGE

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        RoomSensor RoomSensor = new RoomSensor("1", "s1", new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        List<Room> listOfRooms = new ArrayList<>();
        //ACT
        room.addSensor(RoomSensor);
        listOfRooms.add(room);
        roomDomainService.setListOfRooms(listOfRooms);
        RoomDto roomDto = MapperRoom.toDto(room);
        ListOfRoomSensorsDto result = usc250.getListOfSensorsInARoom(roomDto);
        //ASSERT
        assertEquals(RoomSensor.getName(), result.getListOfRoomSensorDto().get(0).getName());
    }

    @Test
    void getListOfSensorsInARoomEmptyListTest() throws Exception {
        //ARRANGE

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        //ACT
        listOfRooms.add(room);
        roomDomainService.setListOfRooms(listOfRooms);
        RoomDto roomDto = MapperRoom.toDto(room);
        ListOfRoomSensorsDto result = usc250.getListOfSensorsInARoom(roomDto);
        List<String> expectedResult = new ArrayList<>();
        //ASSERT
        assertEquals(expectedResult.isEmpty(), result.getListOfRoomSensorDto().isEmpty());
    }

    @Test
    void getListOfRoomsInAHouseFullListTest() throws Exception {
        //ARRANGE

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        //ACT
        listOfRooms.add(room);
        roomDomainService.setListOfRooms(listOfRooms);
        ListOfRoomsDto result = usc250.getListOfRoomsInAHouse();
        //ASSERT
        assertEquals("r1", result.getRoomDto().get(0).getName());
    }

    @Test
    void getListOfRoomsInAHouseEmptyListTest() throws Exception {
        //ARRANGE

        List<Room> listOfRooms = new ArrayList<>();
        //ACT
        roomDomainService.setListOfRooms(listOfRooms);
        ListOfRoomsDto result = usc250.getListOfRoomsInAHouse();
        List<String> expectedResult = new ArrayList<>();
        //ASSERT
        assertEquals(expectedResult.isEmpty(), result.getRoomDto().isEmpty());
    }

}
