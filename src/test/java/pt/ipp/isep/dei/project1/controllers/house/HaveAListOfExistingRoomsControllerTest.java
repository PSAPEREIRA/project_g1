package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class HaveAListOfExistingRoomsControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    HaveAListOfExistingRoomsController haveAListOfExistingRoomsController;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        haveAListOfExistingRoomsController = new HaveAListOfExistingRoomsController(roomDomainService);
    }

    @Test
    void testGetListOfRooms() throws Exception {
        //ARRANGE
        List<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        //ACT

        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        //ASSERT
        ListOfRoomsDto result = haveAListOfExistingRoomsController.getListOfRooms();
        assertEquals(room2.getName(), result.getRoomDto().get(1).getName());
    }

    @Test
    void testAddNewRoomAttributeName() throws Exception {
        //ARRANGE

        String roomName = "bed room";
        int roomFloor = 1;
        List<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room(roomName, "room", roomFloor, dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        //ACT
        haveAListOfExistingRoomsController.setNewRoomAttributeName(roomDto, "kitchen");
        String result = room.getName();

        //ASSERT
        assertEquals("kitchen", result);
    }

    @Test
    void testAddNewRoomAttributeFloor() throws Exception {
        //ARRANGE

        String roomName = "bed room";
        int roomFloor = 1;
        List<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room(roomName, "room", roomFloor, dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        //ACT
        haveAListOfExistingRoomsController.setNewRoomAttributeFloor(roomDto, 5);
        double result = room.getHouseFloor();

        //ASSERT
        assertEquals(5, result);
    }


    @Test
    void testAddNewRoomAttributeDimensions() {
        //ARRANGE

        String roomName = "bed room";
        int roomFloor = 1;
        List<Double> dimension = new ArrayList<>();
        dimension.add(20.0);
        dimension.add(20.0);
        dimension.add(20.0);
        Room room = new Room(roomName, "room", roomFloor, dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        //ACT
        haveAListOfExistingRoomsController.setNewRoomAttributeDimensions(roomDto, dimension);
        Double result = room.getLength();
        List<Double> expectedResult = new ArrayList<>();
        expectedResult.add(20.0);
        expectedResult.add(20.0);
        expectedResult.add(20.0);

        //ASSERT
        assertEquals(expectedResult.get(0), result);
    }

    @Test
    void testAddNewRoomAttributeGetLenght() {
        //ARRANGE

        String roomName = "bed room";
        int roomFloor = 1;
        List<Double> dimension1 = new ArrayList<>();
        dimension1.add(20.0);
        dimension1.add(20.0);
        dimension1.add(20.0);
        Room room = new Room(roomName, "room", roomFloor, dimension1.get(0),dimension1.get(1),dimension1.get(2));
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        //ACT
        List<Double> dimension = new ArrayList<>();
        dimension.add(10.0);
        dimension.add(10.0);
        dimension.add(10.0);
        haveAListOfExistingRoomsController.setNewRoomAttributeDimensions(roomDto, dimension);

        //ASSERT
        assertEquals(10, roomDomainService.getRoomByName("bed room").getLength());
    }


    @Test
    void testAddNewRoomAttributeGetWidth() {
        //ARRANGE

        String roomName = "bed room";
        int roomFloor = 1;
        List<Double> dimension1 = new ArrayList<>();
        dimension1.add(20.0);
        dimension1.add(20.0);
        dimension1.add(20.0);
        Room room = new Room(roomName, "room", roomFloor, dimension1.get(0),dimension1.get(1),dimension1.get(2));
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        //ACT
        List<Double> dimension = new ArrayList<>();
        dimension.add(10.0);
        dimension.add(10.0);
        dimension.add(10.0);
        haveAListOfExistingRoomsController.setNewRoomAttributeDimensions(roomDto, dimension);
        //ASSERT
        assertEquals(10, roomDomainService.getRoomByName("bed room").getWidth());
    }


    @Test
    void testAddNewRoomAttributeGetHeight() {
        //ARRANGE

        String roomName = "bed room";
        int roomFloor = 1;
        List<Double> dimension1 = new ArrayList<>();
        dimension1.add(20.0);
        dimension1.add(20.0);
        dimension1.add(20.0);
        Room room = new Room(roomName, "room", roomFloor, dimension1.get(0),dimension1.get(1),dimension1.get(2));
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        //ACT
        List<Double> dimension = new ArrayList<>();
        dimension.add(10.0);
        dimension.add(10.0);
        dimension.add(10.0);
        haveAListOfExistingRoomsController.setNewRoomAttributeDimensions(roomDto, dimension);

        //ASSERT
        assertEquals(10, roomDomainService.getRoomByName("bed room").getHeight());
    }

}
