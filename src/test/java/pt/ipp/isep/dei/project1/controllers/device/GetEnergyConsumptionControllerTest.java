package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.*;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.device.lamp.Lamp;
import pt.ipp.isep.dei.project1.model.device.lamp.LampSpec;
import pt.ipp.isep.dei.project1.model.device.lamp.LampType;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetEnergyConsumptionControllerTest {


    @Mock
    private RoomRepository roomRepository;
    private RoomDomainService roomDomainService;
    @Mock
    private HouseGridRepository houseGridRepository;
    private HouseGridRepo houseGridRepo;
    private RoomHouseGridService roomHouseGridService;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
    }


    @Test
    void testGetListOfRooms() throws Exception {
        //ARRANGE
                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50, 50, 10), new FridgeType());
        Room room = new Room("r1","room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);
        //ACT
        room.getListOfDevices().addDeviceToList(device);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        ListOfRoomsDto expectedResult = MapperListOfRooms.toDto(roomDomainService.getListOfRooms());
        ListOfRoomsDto result = getEnergyConsumptionController.getListOfRooms();
        //ASSERT
        assertEquals(expectedResult.getRoomDto().size(), result.getRoomDto().size());
    }

    @Test
    void testGetListOfDevices() throws Exception {
        //ARRANGE
                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50, 50, 10), new FridgeType());
        Room room = new Room("r1","room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);
        //ACT
        RoomDto roomDto = MapperRoom.toDto(room);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        room.getListOfDevices().addDeviceToList(device);
        ListOfDevicesDto expectedResult = MapperListOfDevice.toDto(room.getListOfDevices().getDeviceList());
        //ASSERT
        ListOfDevicesDto result = getEnergyConsumptionController.getListOfDevicesFromRoom(roomDto);
        assertEquals(expectedResult.getListOfDevices().size(), result.getListOfDevices().size());
    }


    @Test
    void testGetListOfHG() throws Exception {
        //ARRANGE
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Fridge device = new Fridge("fridge1", new FridgeSpec(100, 50, 50, 10), new FridgeType());
        Room room = new Room("r1","room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);
        HouseGrid houseGrid = new HouseGrid("hg1",20);
        List<HouseGrid> list = new ArrayList<>();
        list.add(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid);
        //ACT
        RoomDto roomDto = MapperRoom.toDto(room);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        room.getListOfDevices().addDeviceToList(device);
        ListOfHouseGridsDto expectedResult = MapperListOfHouseGrids.toDto(list);
        //ASSERT
        ListOfHouseGridsDto result = getEnergyConsumptionController.getListOfHouseGrid();
        assertEquals(expectedResult.getListOfHouseGridDto().size(), result.getListOfHouseGridDto().size());
    }


    @Test
    void getEnergyConsumptionOnOneRoom() throws Exception {
                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 21, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 22, 23, 59);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());

        Fridge device2 = new Fridge("fridge", new FridgeSpec(), new FridgeType());

        roomDomainService.addRoom(room);
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        device1.setListOfReadings(lr1);
        device2.setListOfReadings(lr1);

        room.getListOfDevices().addDeviceToList(device1);
        room.getListOfDevices().addDeviceToList(device2);


        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);
        RoomDto roomDto = MapperRoom.toDto(room);
        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneRoom(roomDto, initialDate, finalDate);

        assertEquals(108, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneRoomNoreadingsOnDate() throws Exception {
                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 24, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 25, 23, 59);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());

        Fridge device2 = new Fridge("fridge", new FridgeSpec(), new FridgeType());

        roomDomainService.addRoom(room);
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        device1.setListOfReadings(lr1);
        device2.setListOfReadings(lr1);

        room.getListOfDevices().addDeviceToList(device1);
        room.getListOfDevices().addDeviceToList(device2);


        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        RoomDto roomDto = MapperRoom.toDto(room);
        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneRoom(roomDto, initialDate, finalDate);

        assertEquals(0, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneRoomNoReadings() throws Exception {

                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 24, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 25, 23, 59);

        roomDomainService.addRoom(room);
        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());

        Fridge device2 = new Fridge("fridge", new FridgeSpec(), new FridgeType());

        room.getListOfDevices().addDeviceToList(device1);
        room.getListOfDevices().addDeviceToList(device2);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        RoomDto roomDto = MapperRoom.toDto(room);
        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneRoom(roomDto, initialDate, finalDate);

        assertEquals(0, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneRoomNoDevices() throws Exception {

                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 24, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 25, 23, 59);
        roomDomainService.addRoom(room);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        RoomDto roomDto = MapperRoom.toDto(room);
        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneRoom(roomDto, initialDate, finalDate);

        assertEquals(0, result, 0.0001);
    }


    @Test
    void getEnergyConsumptionOnOneDevice() throws Exception {

                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 21, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 22, 23, 59);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        room.getListOfDevices().addDeviceToList(device1);
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        device1.setListOfReadings(lr1);

        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device1);


        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneDevice(roomDto, deviceDto, initialDate, finalDate);

        assertEquals(54, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneDevice2() throws Exception {

                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 21, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 22, 23, 59);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        room.getListOfDevices().addDeviceToList(device1);
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        device1.setListOfReadings(lr1);

        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device1);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneDevice(roomDto, deviceDto, initialDate, finalDate);

        assertEquals(60, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneDeviceNoReadingsOnDate() throws Exception {
                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 24, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 25, 23, 59);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        room.getListOfDevices().addDeviceToList(device1);
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        device1.setListOfReadings(lr1);

        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device1);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneDevice(roomDto, deviceDto, initialDate, finalDate);

        assertEquals(0, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneDeviceNoInterval() throws Exception {
                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 21, 12, 30);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 21, 12, 40);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        room.getListOfDevices().addDeviceToList(device1);
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 45);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        device1.setListOfReadings(lr1);

        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device1);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneDevice(roomDto, deviceDto, initialDate, finalDate);

        assertEquals(0, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneDeviceDatesInvert() throws Exception {

                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 21, 12, 40);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 21, 12, 30);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        room.getListOfDevices().addDeviceToList(device1);
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 45);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        device1.setListOfReadings(lr1);

        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device1);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneDevice(roomDto, deviceDto, initialDate, finalDate);

        assertEquals(0, result, 0.0001);
    }


    @Test
    void getEnergyConsumptionOnOneDeviceNoReadings() throws Exception {

                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        roomDomainService.addRoom(room);
        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 24, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 25, 23, 59);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        room.getListOfDevices().addDeviceToList(device1);
        ListOfReadings lr1 = new ListOfReadings();

        device1.setListOfReadings(lr1);

        RoomDto roomDto = MapperRoom.toDto(room);
        DeviceDto deviceDto = MapperDevice.toDto(device1);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionOnOneDevice(roomDto, deviceDto, initialDate, finalDate);

        assertEquals(0, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneHouseGrid() throws Exception {
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        HouseGrid houseGrid = new HouseGrid("xpt1", 100);
        Room room1 = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("xpto2","room", 3, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room3 = new Room("xpto3","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        houseGridRepo.addHouseGrid(houseGrid);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room3);

        ListOfReadings lr1 = new ListOfReadings();
        ListOfReadings lr2 = new ListOfReadings();
        ListOfReadings lr3 = new ListOfReadings();

        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);

        Reading r1 = new Reading(date1, 12);
        Reading r2 = new Reading(date2, 13);
        Reading r3 = new Reading(date3, 14);
        Reading r4 = new Reading(date4, 15);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);


        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        Fridge device2 = new Fridge("fridge", new FridgeSpec(), new FridgeType());

        device1.setListOfReadings(lr1);
        device2.setListOfReadings(lr1);


        room1.getListOfDevices().addDeviceToList(device1);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device1);
        room2.getListOfDevices().addDeviceToList(device2);
        room3.getListOfDevices().addDeviceToList(device1);
        room3.getListOfDevices().addDeviceToList(device2);


        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 21, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 22, 23, 59);

        double expectedResult = (12 * 3 + 13 * 3 + 14 * 3 + 15 * 3) * 2;

        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);


        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionInOneHouseGrid(houseGridDto, initialDate, finalDate);

        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void getEnergyConsumptionOnOneHouseGridNoreadingsOnDate() throws Exception {

                ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        HouseGrid houseGrid = new HouseGrid("xpt1", 100);
        Room room1 = new Room("xpto1","room", 1,dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("xpto2","room", 3, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room3 = new Room("xpto3","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        houseGridRepo.addHouseGrid(houseGrid);
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomDomainService.addRoom(room3);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room3);

        ListOfReadings lr1 = new ListOfReadings();

        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 22, 12, 32);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 22, 12, 32);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 23, 12, 32);

        Reading r1 = new Reading(date1, 12);
        Reading r2 = new Reading(date2, 13);
        Reading r3 = new Reading(date3, 14);
        Reading r4 = new Reading(date4, 15);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);

        Lamp device1 = new Lamp("lamp", new LampSpec(), new LampType());
        Fridge device2 = new Fridge("fridge", new FridgeSpec(), new FridgeType());
        device1.setListOfReadings(lr1);
        device2.setListOfReadings(lr1);


        room1.getListOfDevices().addDeviceToList(device1);
        room1.getListOfDevices().addDeviceToList(device2);
        room2.getListOfDevices().addDeviceToList(device1);
        room2.getListOfDevices().addDeviceToList(device2);
        room3.getListOfDevices().addDeviceToList(device1);
        room3.getListOfDevices().addDeviceToList(device2);


        LocalDateTime initialDate = LocalDateTime.of(2018, 12, 25, 00, 00);
        LocalDateTime finalDate = LocalDateTime.of(2018, 12, 27, 23, 59);

        double expectedResult = 0;
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);

        GetEnergyConsumptionController getEnergyConsumptionController = new GetEnergyConsumptionController(roomDomainService, houseGridRepo,roomHouseGridService);

        double result = getEnergyConsumptionController.getEnergyConsumptionInOneHouseGrid(houseGridDto, initialDate, finalDate);

        assertEquals(expectedResult, result, 0.0001);
    }
}