package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeactivateRemoveDeviceFromRoomControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    DeactivateRemoveDeviceFromRoomController deactivateRemoveDeviceFromRoomController;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        deactivateRemoveDeviceFromRoomController  = new DeactivateRemoveDeviceFromRoomController(roomDomainService);
    }

    @Test
    void testGetListOfRooms() throws Exception {
        //ARRANGE

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Room room2 = new Room("r2", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        //ACT
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        ListOfRoomsDto expectedResult = MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
        //ASSERT
        ListOfRoomsDto result = deactivateRemoveDeviceFromRoomController.getListOfRoomsDto();
        assertEquals(expectedResult.getRoomDto().size(), result.getRoomDto().size());
    }

    @Test
    void testGetListOfDevices() throws Exception {
        //ARRANGE

        Fridge device = new Fridge("fridge1", new FridgeSpec(100,
        50, 50, 10),new FridgeType());
        ArrayList < Double > dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        //ACT
        roomDomainService.addRoom(room);
        room.getListOfDevices().addDeviceToList(device);
        ListOfDevicesDto expectedResult = MapperListOfDevice.toDto(room.getListOfDevices().getDeviceList());
        RoomDto roomDto = MapperRoom.toDto(room);
        //ASSERT
        ListOfDevicesDto result = deactivateRemoveDeviceFromRoomController.getListOfDevicesDtoFromRoom(roomDto);
        assertEquals(expectedResult.getListOfDevices().size(), result.getListOfDevices().size());
    }


    @Test
    void testRemoveDeviceFromRoomTrue() throws Exception {
        //ARRANGE
        Fridge device = new Fridge("fridge1", new FridgeSpec(100,
        50, 50, 10),new FridgeType());
        ArrayList < Double > dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        //ACT
        roomDomainService.addRoom(room);
        room.getListOfDevices().addDeviceToList(device);
        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device);
        //ASSERT
        boolean result = deactivateRemoveDeviceFromRoomController.removeDeviceFromRoom(roomDto, deviceDto);
        assertTrue(result);
    }

    @Test
    void testRemoveDeviceFromRoomFalse() throws Exception {
        //ARRANGE
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50, 50, 10), new FridgeType());
        Fridge device2 = new Fridge("fridge2", new FridgeSpec(100,

        50, 50, 10),new FridgeType());
        ArrayList < Double > dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        //ACT
        roomDomainService.addRoom(room);
        room.getListOfDevices().addDeviceToList(device);
        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device);
        //ASSERT
        deactivateRemoveDeviceFromRoomController.removeDeviceFromRoom(roomDto, deviceDto);
        boolean result = deactivateRemoveDeviceFromRoomController.removeDeviceFromRoom(roomDto, deviceDto);

        assertFalse(result);
    }


    @Test
    void testDeactivateDeviceIsTrue() throws Exception {
        //ARRANGE
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50,
                50, 10), new FridgeType());
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        roomDomainService.addRoom(room);
        room.getListOfDevices().addDeviceToList(device);
        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device);
        //ACT
        boolean result = deactivateRemoveDeviceFromRoomController.deactivateDevice(roomDto, deviceDto);
        //ASSERT
        assertTrue(result);
    }


    @Test
    void testCheckIfIsActiveFalse2() throws Exception {
        //ARRANGE
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50,
                50, 10), new FridgeType());
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("r1", "room", 1, dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        roomDomainService.addRoom(room);
        room.getListOfDevices().addDeviceToList(device);
        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device);
        //ACT
        deactivateRemoveDeviceFromRoomController.deactivateDevice(roomDto, deviceDto);
        boolean result = deactivateRemoveDeviceFromRoomController.deactivateDevice(roomDto, deviceDto);
        //ASSERT
        assertFalse(result);
    }

}


