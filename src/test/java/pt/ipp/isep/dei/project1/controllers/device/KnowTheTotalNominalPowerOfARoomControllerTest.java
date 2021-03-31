package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfDevice;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class KnowTheTotalNominalPowerOfARoomControllerTest {


    @Mock
    private RoomRepository roomRepository;
    private RoomDomainService roomDomainService;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
    }


    @Test
    void testGetListOfRooms() throws Exception {
        //Arrange
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1", "room", 2,dimension.get(0),dimension.get(1),dimension.get(2));
        KnowTheTotalNominalPowerOfARoomController ctrll230 = new KnowTheTotalNominalPowerOfARoomController(roomDomainService);
        //Act
        roomDomainService.addRoom(room);
        ListOfRoomsDto result = ctrll230.getListOfRooms();
        ListOfRoomsDto expectedResult = MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
        //Assert
        assertEquals(expectedResult.getRoomDto().size(), result.getRoomDto().size());

    }

    @Test
    void testGetListOfDevices() throws Exception {
        //Arrange

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1", "room", 2,dimension.get(0),dimension.get(1),dimension.get(2));
        KnowTheTotalNominalPowerOfARoomController ctrll230 = new KnowTheTotalNominalPowerOfARoomController(roomDomainService);

        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        Dishwasher device = new Dishwasher("dishouse", dishwasherSpec, new DishwasherType());
        //Act
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        room.getListOfDevices().addDeviceToList(device);
        ListOfDevicesDto result = ctrll230.getListOfDevicesDtoFromRoom(roomDto);
        ListOfDevicesDto expectedResult = MapperListOfDevice.toDto(room.getListOfDevices().getDeviceList());
        //Assert
        assertEquals(expectedResult.getListOfDevices().size(), result.getListOfDevices().size());

    }


    @Test
    void totalNominalPower() throws Exception {
        //Arrange

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1", "room", 2,dimension.get(0),dimension.get(1),dimension.get(2));
        DishwasherSpec dishwasher = new DishwasherSpec();
        DishwasherSpec dishwasher2 = new DishwasherSpec();

        dishwasher.setAttributeValue("nominal power", 100);
        dishwasher2.setAttributeValue("nominal power", 100);

        ListOfDevices listOfDevices = new ListOfDevices();
        roomDomainService.addRoom(room);
        Dishwasher device = new Dishwasher("dishouse", dishwasher, new DishwasherType());
        Dishwasher device1 = new Dishwasher("dish2", dishwasher2, new DishwasherType());
        KnowTheTotalNominalPowerOfARoomController ctrll230 = new KnowTheTotalNominalPowerOfARoomController(roomDomainService);
        //Act
        room.getListOfDevices().addDeviceToList(device);
        room.getListOfDevices().addDeviceToList(device1);
        RoomDto roomDto = MapperRoom.toDto(room);
        double result = ctrll230.getTotalNominalPowerOfRoom(roomDto);
        double expectedResult = 200;
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void totalNominalPowerNoDevices() throws Exception {
        //Arrange

        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1", "room", 2,dimension.get(0),dimension.get(1),dimension.get(2));
        KnowTheTotalNominalPowerOfARoomController ctrll230 = new KnowTheTotalNominalPowerOfARoomController(roomDomainService);
        //Act
        roomDomainService.addRoom(room);
        RoomDto roomDto = MapperRoom.toDto(room);
        double result = ctrll230.getTotalNominalPowerOfRoom(roomDto);
        double expectedResult = 0;
        //Assert
        assertEquals(expectedResult, result);
    }


}