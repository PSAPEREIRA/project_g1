package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetAListOfAllDevicesInARoomToBePossibleToConfigureThemControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    GetAListOfAllDevicesInARoomToBePossibleToConfigureThemController usc201;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        usc201 = new GetAListOfAllDevicesInARoomToBePossibleToConfigureThemController(roomDomainService);
    }

    @Test
    void getListOfRoomsInAHouseEmptyListTest(){
        //ARRANGE

        List<Room> listOfRooms = new ArrayList<>();
        //ACT
        roomDomainService.setListOfRooms(listOfRooms);
        ListOfRoomsDto result = usc201.getListOfRooms();
        ListOfRoomsDto expectedResult = new ListOfRoomsDto();
        //ASSERT
        assertEquals(expectedResult.getRoomDto().isEmpty(), result.getRoomDto().isEmpty());
    }

    @Test
    void getListOfDevicesInARoomFullListTest(){
        //ARRANGE

                ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1","classroom", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<String[]> programs= new ArrayList<>();
        String[] program1 = new String[2];
        program1[0]="prog1";
        program1[1]="20";
        programs.add(program1);
        Dishwasher device = new Dishwasher("dish1", new DishwasherSpec(),new DishwasherType());
        List<Room> listOfRooms = new ArrayList<>();
        //ACT
        listOfRooms.add(room);
        room.getListOfDevices().addDeviceToList(device);
        roomDomainService.setListOfRooms(listOfRooms);
        RoomDto roomDto = MapperRoom.toDto(room);
        ListOfDevicesDto result = usc201.getListOfDevicesInARoom(roomDto);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(device.getName());
        //ASSERT
        assertEquals(expectedResult.get(0), result.getListOfDevices().get(0).getName());
    }

    @Test
    void getListOfDevicesInARoomEmptyListTest(){
        //ARRANGE

                ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1","classroom", 2, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        List<Room> listOfRooms = new ArrayList<>();
        //ACT
        listOfRooms.add(room);
        roomDomainService.setListOfRooms(listOfRooms);
        RoomDto roomDto = MapperRoom.toDto(room);
        ListOfDevicesDto result = usc201.getListOfDevicesInARoom(roomDto);
        ListOfDevicesDto expectedResult = new ListOfDevicesDto();
        //ASSERT
        assertEquals(expectedResult.getListOfDevices().isEmpty(), result.getListOfDevices().isEmpty());
    }
}