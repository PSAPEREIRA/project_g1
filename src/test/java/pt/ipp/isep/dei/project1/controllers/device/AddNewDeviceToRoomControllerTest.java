package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class AddNewDeviceToRoomControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    AddNewDeviceToRoomController ctrl210;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        ctrl210 = new AddNewDeviceToRoomController(roomDomainService);
    }

    @Test
    void addDeviceTrue() {

        Fridge device = new Fridge("FridgeSpec", new FridgeSpec(), new FridgeType());

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        RoomDto roomDto = MapperRoom.toDto(r1);
        //ACT
        boolean result = ctrl210.addNewDeviceToRoom(roomDto, device);
        assertTrue(result);
    }

    @Test
    void addDeviceTrue2() {

        Fridge device = new Fridge("device2", new FridgeSpec(), new FridgeType());
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("room1", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        RoomDto roomDto = MapperRoom.toDto(r1);
        //ACT
        boolean result = ctrl210.addNewDeviceToRoom(roomDto, device);
        assertTrue(result);
    }

    @Test
    void addDeviceFalse() {

        Fridge d1 = new Fridge("FridgeSpec", new FridgeSpec(), new FridgeType());
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        RoomDto roomDto = MapperRoom.toDto(r1);
        //ACT
        ctrl210.addNewDeviceToRoom(roomDto, d1);

        boolean result = ctrl210.addNewDeviceToRoom(roomDto, d1);

        assertFalse(result);
    }


    @Test
    void getListOfRooms() {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        //ACT
        List<String> expected = new ArrayList<>();
        expected.add("Kitchen");

        ListOfRoomsDto result = ctrl210.getListOfRooms();

        assertEquals(expected.get(0), result.getRoomDto().get(0).getName());
    }

    @Test
    void getListOfDeviceTypes() throws Exception {

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        //ACT
        List<DeviceType> expected = roomDomainService.getListOfDeviceTypes();

        List<DeviceType> result = ctrl210.getListOfDevices();

        assertEquals(expected.get(0).getType(), result.get(0).getType());
    }

    @Test
    void createDevice() {

        Fridge d1 = new Fridge("FridgeSpec", new FridgeSpec(), new FridgeType());
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        //ACT

        Device result = ctrl210.createNewDevice(new FridgeType(), "FridgeSpec");

        assertEquals(d1.getName(), result.getName());
    }

    @Test
    void testSetDeviceProgram() {

        Dishwasher d1 = new Dishwasher("Dish", new DishwasherSpec(), new DishwasherType());
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        //ACT
        ctrl210.createNewProgram(d1, "p1", 50);
        assertEquals("p1", d1.getListOfPrograms().get(0)[0]);


    }

    @Test
    void setDeviceProgram2() {

        Dishwasher d1 = new Dishwasher("Dish", new DishwasherSpec(), new DishwasherType());
        d1.createProgramAndAddTo("p1", 200);
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room r1 = new Room("Kitchen", "room", 1, dimension.get(0), dimension.get(1), dimension.get(2));
        roomDomainService.addRoom(r1);
        //ACT
        ctrl210.createNewProgram(d1, "p1", 50);

        int indexResult = d1.getListOfPrograms().size() - 1;

        String[] result = d1.getListOfPrograms().get(indexResult);


        String[] expectedResult = new String[]{"p1", "50.0"};

        assertArrayEquals(expectedResult, result);

    }

}
