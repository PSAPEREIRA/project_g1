package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.house.*;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AttachDetachRoomToHouseGridControllerTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    AttachDetachRoomToHouseGridController attachRoomToHouseGridController;
    @Mock
    private RoomHouseGridService roomHouseGridService;


    @BeforeEach
    void initUseCase() throws Exception {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
        attachRoomToHouseGridController = new AttachDetachRoomToHouseGridController(houseGridRepo, roomDomainService,roomHouseGridService);
    }


    @Test
    void getListOfHouseGrid(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        //ACT
        ListOfHouseGridsDto result = attachRoomToHouseGridController.getListOfHouseGrids();
        ListOfHouseGridsDto expectedResult = new ListOfHouseGridsDto();
        List<HouseGrid> list = new ArrayList<>();
        list.add(houseGrid);
        list.add(houseGrid2);
        expectedResult.setListOfHouseGridDto(list);
        //ASSERT
        assertEquals(expectedResult.getListOfHouseGridDto().get(1).getCode(), result.getListOfHouseGridDto().get(1).getCode());

    }

    @Test
    void getListOfRooms(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        room2.setHouseGrid("TheGrid");  //Inserted this room with this house grid to cover condition of room without house grid
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        //ACT
        ListOfRoomsDto result = attachRoomToHouseGridController.getListOfRooms();
        RoomDto expectedResult = MapperRoom.toDto(room);
        //ASSERT
        assertEquals(expectedResult.getName(), result.getRoomDto().get(0).getName());
        assertEquals(expectedResult.getName(), result.getRoomDto().get(0).getName());
    }

    @Test
    void getListOfRoomsSize(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("Room 1", "Bedroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("Room 2", "Kitchen", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room3 = new Room("Room 3", "Lounge", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        room2.setHouseGrid("TheGrid");  //Inserted this room with this house grid to cover condition of room without house grid
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        //ACT
        ListOfRoomsDto result = attachRoomToHouseGridController.getListOfRooms();
        int expectedResult = 2;
        //ASSERT
        assertEquals(expectedResult, result.getRoomDto().size());
    }



    @Test
    void addHouseGrid(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("livingRoom", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(houseGrid);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.attachRoomToHouseGrid(houseGridDto, roomDto);
        //ASSERT
        assertTrue(result);
    }

    @Test
    void addHouseGridPos2(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        HouseGrid houseGrid2 = new HouseGrid("008", 70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("livingRoom", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid2,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid2,room1);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid2);
        RoomDto roomDto = MapperRoom.toDto(room2);
        //ACT
        boolean result = attachRoomToHouseGridController.attachRoomToHouseGrid(houseGridDto, roomDto);
        //ASSERT
        assertTrue(result);
    }

    @Test
    void addHouseGridRepeated(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        houseGridRepo.addHouseGrid(houseGrid);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.attachRoomToHouseGrid(houseGridDto, roomDto);
        //ASSERT
        assertFalse(result);
    }

    @Test
    void addHouseGridFalse2(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        houseGridRepo.setListOfHouseGrids(listOfHouseGrids);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.attachRoomToHouseGrid(houseGridDto, roomDto);
        //ASSERT
        assertFalse(result);
    }

    @Test
    void detachHouseGridFalse2(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        houseGridRepo.setListOfHouseGrids(listOfHouseGrids);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.detachRoomFromHouseGrid(houseGridDto, roomDto.getName());
        //ASSERT
        assertFalse(result);
    }

    @Test
    void addHouseGridListHouseGridsEmpty(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        houseGridRepo.addHouseGrid(houseGrid);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.attachRoomToHouseGrid(houseGridDto, roomDto);
        //ASSERT
        assertFalse(result);
    }

    @Test
    void detachHouseGridFalse(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(houseGrid);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("livingRoom", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.detachRoomFromHouseGrid(houseGridDto, roomDto.getName());
        //ASSERT
        assertFalse(result);
    }

    @Test
    void detachHouseGridFalseRoomListEmpty(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(houseGrid);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("livingRoom", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(houseGrid);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.detachRoomFromHouseGrid(houseGridDto, roomDto.getName());
        //ASSERT
        assertFalse(result);
    }

    @Test
    void detachHouseGridFalseHouseGridListEmpty(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("livingRoom", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(houseGrid);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.detachRoomFromHouseGrid(houseGridDto, roomDto.getName());
        //ASSERT
        assertFalse(result);
    }

    @Test
    void detachHouseGrid(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(houseGrid);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("livingRoom", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(room1);
        listOfRooms.add(room2);
        List<String> listOfRooms2 = new ArrayList<>();
        listOfRooms2.add(room.getName());
        listOfRooms2.add(room1.getName());
        houseGridRepo.addHouseGrid(houseGrid);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        houseGrid.setRoomsList(listOfRooms2);
        roomDomainService.setListOfRooms(listOfRooms);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        RoomDto roomDto = MapperRoom.toDto(room1);
        //ACT
        boolean result = attachRoomToHouseGridController.detachRoomFromHouseGrid(houseGridDto, roomDto.getName());
        //ASSERT
        assertTrue(result);
    }

    @Test
    void getListOfRoomsAttachedToHouseGridTest() {

        HouseGrid houseGrid = new HouseGrid("007", 100);
        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        listOfHouseGrids.add(houseGrid);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room1 = new Room("kitchen", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        Room room2 = new Room("livingRoom", "classroom", 0, dimensions1.get(0), dimensions1.get(1), dimensions1.get(2));
        List<String> listOfRooms = new ArrayList<>();
        listOfRooms.add(room.getName());
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        houseGridRepo.addHouseGrid(houseGrid);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        houseGrid.setRoomsList(listOfRooms);
        roomDomainService.setListOfRooms(roomHouseGridService.getRoomsConnectedToHouseGrid(houseGrid.getRoomsList()));
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        //ACT
        List<String> expectedResult = new ArrayList<>();
        for (String r1 : houseGrid.getRoomsList())
            expectedResult.add(r1);
        List<String> result = attachRoomToHouseGridController.getListOfRoomsAttachedToHouseGrid(houseGridDto);
        //ASSERT
        assertEquals(expectedResult,result);
    }

}