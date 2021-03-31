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
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperDevice;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.freezer.Freezer;
import pt.ipp.isep.dei.project1.model.device.freezer.FreezerSpec;
import pt.ipp.isep.dei.project1.model.device.freezer.FreezerType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.house.*;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class GetNominalPowerOfRoomsOrDevicesControllerTest {


    @Mock
    private HouseGridRepository houseGridRepository;
    private HouseGridRepo houseGridRepo;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private RoomHouseGridService roomHouseGridService;

    GetNominalPowerOfRoomsOrDevicesController ctrl;


    @BeforeEach
    void initUseCase() throws Exception {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
        ctrl = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);

    }


    @Test
    void getListOfHouseGrid() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);
        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        roomHouseGridService.addRoomToHouseGrid(electricPowerGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(electricPowerGrid,room2);
        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(electricPowerGrid.getCode());
        ListOfHouseGridsDto result = mUSC705.getListOfHouseGrids();

        assertEquals(expectedResult.get(0), result.getListOfHouseGridDto().get(0).getCode());
    }


    @Test
    void getListOfnRoomsOnHouseGrid() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);

        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        listOfHouseGrids.add(electricPowerGrid);
        listOfHouseGrids.add(electricPowerGridMax);

        List<String>listOfRooms = new ArrayList<>();
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());

        electricPowerGrid.setRoomsList(listOfRooms);
        HouseGridDto electricPowerGridDto = MapperHouseGrid.toDto(electricPowerGrid);
        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);

        String result = mUSC705.getRoomFromHouseGrid(electricPowerGridDto, 0);

        assertEquals(room1.getName(), result);
    }

    @Test
    void getListOfnRoomsOnHouseGrid2() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);

        List<HouseGrid> listOfHouseGrids = new ArrayList<>();        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        listOfHouseGrids.add(electricPowerGrid);
        listOfHouseGrids.add(electricPowerGridMax);

        List<String>listOfRooms = new ArrayList<>();
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());

        electricPowerGrid.setRoomsList(listOfRooms);
        HouseGridDto electricPowerGridDto = MapperHouseGrid.toDto(electricPowerGrid);
        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);

        String result = mUSC705.getRoomFromHouseGrid(electricPowerGridDto, 1);

        assertEquals(room2.getName(), result);
    }

    @Test
    void getListOfnRoomsOnHouseGrid3() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);

        List<HouseGrid> listOfHouseGrids = new ArrayList<>();        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        listOfHouseGrids.add(electricPowerGrid);
        listOfHouseGrids.add(electricPowerGridMax);


        List<String>listOfRooms = new ArrayList<>();
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room3 = new Room("r2", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        listOfRooms.add(room3.getName());
        electricPowerGrid.setRoomsList(listOfRooms);

        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);
        HouseGridDto electricPowerGridDto = MapperHouseGrid.toDto(electricPowerGrid);
        String result = mUSC705.getRoomFromHouseGrid(electricPowerGridDto, 2);

        assertEquals(room3.getName(), result);
    }


    @Test
    void getListOfDevicesOnRoom() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);

        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        listOfHouseGrids.add(electricPowerGrid);
        listOfHouseGrids.add(electricPowerGridMax);
        List<String[]> programs = new ArrayList<>();
        String[] program1 = new String[2];
        program1[0] = "prog1";
        program1[1] = "20";
        programs.add(program1);
        Dishwasher dish1 = new Dishwasher("dish1", new DishwasherSpec(), new DishwasherType());
        Dishwasher dish2 = new Dishwasher("dish2", new DishwasherSpec(), new DishwasherType());


        List<String>listOfRooms = new ArrayList<>();
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        room1.getListOfDevices().addDeviceToList(dish1);
        room1.getListOfDevices().addDeviceToList(dish2);

        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        houseGridRepo.addHouseGrid(electricPowerGridMax);
        electricPowerGrid.setRoomsList(listOfRooms);

        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);
        HouseGridDto electricPowerGridDto = MapperHouseGrid.toDto(electricPowerGrid);

        DeviceDto result = mUSC705.getDeviceFromRoom(electricPowerGridDto, 0, 0);

        assertEquals(dish1.getName(), result.getName());
    }

    @Test
    void getListOfDevicesOnRoom2() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);

        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        listOfHouseGrids.add(electricPowerGrid);
        listOfHouseGrids.add(electricPowerGridMax);
        List<String[]> programs = new ArrayList<>();
        String[] program1 = new String[2];
        program1[0] = "prog1";
        program1[1] = "20";
        programs.add(program1);
        Dishwasher dish1 = new Dishwasher("dish1", new DishwasherSpec(), new DishwasherType());
        Dishwasher dish2 = new Dishwasher("dish2", new DishwasherSpec(), new DishwasherType());


        List<String>listOfRooms = new ArrayList<>();
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));

        room1.getListOfDevices().addDeviceToList(dish1);
        room1.getListOfDevices().addDeviceToList(dish2);

        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        houseGridRepo.addHouseGrid(electricPowerGridMax);
        electricPowerGrid.setRoomsList(listOfRooms);


        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);
        HouseGridDto electricPowerGridDto = MapperHouseGrid.toDto(electricPowerGrid);
        DeviceDto result = mUSC705.getDeviceFromRoom(electricPowerGridDto, 0, 1);

        assertEquals(dish2.getName(), result.getName());
    }


    @Test
    void getNominalOfTheDevice() throws Exception {
        String houseName = "casa1";
        Location l1 = new Location(40.7486, -73.9864, 0);

        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        houseGridRepo.addHouseGrid(electricPowerGridMax);
        List<String[]> programs = new ArrayList<>();
        String[] program1 = new String[2];
        program1[0] = "prog1";
        program1[1] = "20";
        programs.add(program1);

        Dishwasher dish1 = new Dishwasher("dish1", new DishwasherSpec(), new DishwasherType());
        dish1.getDeviceSpecs().setAttributeValue("nominal power", 10);
        Dishwasher dish2 = new Dishwasher("dish2", new DishwasherSpec(), new DishwasherType());


        List<String>listOfRooms = new ArrayList<>();
                ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        room1.getListOfDevices().addDeviceToList(dish1);
        room1.getListOfDevices().addDeviceToList(dish2);

        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());
        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);

        electricPowerGrid.setRoomsList(listOfRooms);

        DeviceDto deviceDto = MapperDevice.toDto(dish1);
        RoomDto roomDto = MapperRoom.toDto(room1);
        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);
        HouseGridDto electricPowerGridDto = MapperHouseGrid.toDto(electricPowerGrid);
        double result = mUSC705.getNominalPowerOfDevice(electricPowerGridDto, roomDto.getName(), deviceDto);

        assertEquals(10.0, result);
    }


    @Test
    void getNominalOfTheRoom() throws Exception {

        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        houseGridRepo.addHouseGrid(electricPowerGrid);
        houseGridRepo.addHouseGrid(electricPowerGridMax);
        List<String[]> programs = new ArrayList<>();
        String[] program1 = new String[2];
        program1[0] = "prog1";
        program1[1] = "20";
        programs.add(program1);

        Dishwasher dish1 = new Dishwasher("dish1", new DishwasherSpec(), new DishwasherType());
        dish1.getDeviceSpecs().setAttributeValue("nominal power", 20);
        Dishwasher dish2 = new Dishwasher("dish2", new DishwasherSpec(), new DishwasherType());


                ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        room1.getListOfDevices().addDeviceToList(dish1);
        room1.getListOfDevices().addDeviceToList(dish2);

        roomDomainService.addRoom(room1);
        roomDomainService.addRoom(room2);
        roomHouseGridService.addRoomToHouseGrid(electricPowerGrid,room1);
        roomHouseGridService.addRoomToHouseGrid(electricPowerGrid,room2);


        RoomDto roomDto = MapperRoom.toDto(room1);
        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);
        HouseGridDto electricPowerGridDto = MapperHouseGrid.toDto(electricPowerGrid);
        double result = mUSC705.getNominalPowerOfRoom(electricPowerGridDto, roomDto.getName());

        assertEquals(20, result);
    }


    @Test
    void totalNominalPowerSelected() throws Exception {

        List<HouseGrid> listOfHouseGrids = new ArrayList<>();
        HouseGrid electricPowerGrid = new HouseGrid("Grid1", 100);
        HouseGrid electricPowerGridMax = new HouseGrid("Grid1Max", 100);
        listOfHouseGrids.add(electricPowerGrid);
        listOfHouseGrids.add(electricPowerGridMax);
        houseGridRepo.setListOfHouseGrids(listOfHouseGrids);
        List<String[]> programs = new ArrayList<>();
        String[] program1 = new String[2];
        program1[0] = "prog1";
        program1[1] = "20";
        programs.add(program1);

        Dishwasher dish1 = new Dishwasher("dish1", new DishwasherSpec(), new DishwasherType());
        Dishwasher dish2 = new Dishwasher("dish2", new DishwasherSpec(), new DishwasherType());


        List<String>listOfRooms = new ArrayList<>();
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room1 = new Room("xpto1", "room", 1, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r1", "room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        room1.getListOfDevices().addDeviceToList(dish1);
        room1.getListOfDevices().addDeviceToList(dish2);

        listOfRooms.add(room1.getName());
        listOfRooms.add(room2.getName());

        electricPowerGrid.setRoomsList(listOfRooms);

        List<Double> expected = new ArrayList<>();
        expected.add(10.0);
        expected.add(15.5);
        GetNominalPowerOfRoomsOrDevicesController mUSC705 = new GetNominalPowerOfRoomsOrDevicesController(houseGridRepo,roomHouseGridService);

        double result = mUSC705.totalNominalPower(expected);

        assertEquals(25.5, result);
    }

    @Test
    void checkIfGetListOfDevicesDto(){

        Freezer freezer = new Freezer("Cold2k",new FreezerSpec(),new FreezerType());

       // List<Device> deviceList = new ArrayList<>();
       // deviceList.add(freezer);

        ListOfDevices listOfDevices = new ListOfDevices();
        listOfDevices.addDeviceToList(freezer);

        Room room1 = new Room("Kitchen", "Kitchen", 1, 3,3,2.5);
        Room room2 = new Room("Bedroom", "Room", 2, 2,3,2);

        room1.setListOfDevices(listOfDevices);

        HouseGrid houseGrid = new HouseGrid("TheGrid", 300);

        roomDomainService.addRoom(room1);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room1);

        ListOfDevicesDto result = ctrl.getListOfDevicesDto("Kitchen");


        String nameResult = result.getListOfDevices().get(0).getName();
        String typeResult = result.getListOfDevices().get(0).getDeviceType().getType();

        String expectedName = "Cold2k";
        String expectedType = "Freezer";

        assertTrue(expectedName.equals(nameResult) && typeResult.equals(expectedType));

    }


}

