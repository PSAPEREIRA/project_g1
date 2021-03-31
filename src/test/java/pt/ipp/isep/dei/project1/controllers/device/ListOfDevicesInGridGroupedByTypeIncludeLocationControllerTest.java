package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.device.freezer.Freezer;
import pt.ipp.isep.dei.project1.model.device.freezer.FreezerSpec;
import pt.ipp.isep.dei.project1.model.device.freezer.FreezerType;
import pt.ipp.isep.dei.project1.model.device.fridge.Fridge;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeSpec;
import pt.ipp.isep.dei.project1.model.device.fridge.FridgeType;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListOfDevicesInGridGroupedByTypeIncludeLocationControllerTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private RoomHouseGridService roomHouseGridService;
    @Mock
    ListOfDevicesInGridGroupedByTypeIncludeLocationController ctrl;


    @BeforeEach
    void initUseCase() throws Exception {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
        ctrl = new ListOfDevicesInGridGroupedByTypeIncludeLocationController(houseGridRepo,roomHouseGridService);
    }

    @Test
    void getListOfHouseGrid(){
        //ARRANGE
        HouseGrid houseGrid = new HouseGrid("007",100);
        HouseGrid houseGrid2 = new HouseGrid("008",70);
        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);
        Room room = new Room("room","classroom",0,dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        //ACT
        ListOfHouseGridsDto result = ctrl.getListOfHouseGrid();
        //ASSERT
        assertEquals(houseGrid2.getCode(),result.getListOfHouseGridDto().get(1).getCode());
    }

    @Test
    void listOfDeviceType() {

        ArrayList<Double> dimensions1 = new ArrayList<>();
        dimensions1.add(10.0);
        dimensions1.add(6.0);
        dimensions1.add(3.5);

        Room room = new Room("room","classroom",0,dimensions1.get(0),dimensions1.get(1),dimensions1.get(2));
        Fridge device = new Fridge("FridgePlus",new FridgeSpec(),new FridgeType());

        List<String> result = new ArrayList<>();
        result.add("Type: " + device.getDeviceType().getType() + " | Room: " + room.getName() + " | Device: " + device.getName());

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("Type: Fridge | Room: room | Device: FridgePlus");

        assertEquals(expectedResult,result);
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