package pt.ipp.isep.dei.project1.controllers.device;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.model.device.dishwasher.Dishwasher;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
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
class TotalNominalPowerConnectedToHouseGridControllerTest {


    @Mock
    private HouseGridRepository houseGridRepository;
    private HouseGridRepo houseGridRepo;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private RoomHouseGridService roomHouseGridService;



    @BeforeEach
    void initUseCase() throws Exception {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        roomDomainService = new RoomDomainService(roomRepository);
        roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
    }


    @Test
    void totalNominalPowerOfHouseGrid() throws Exception {
        //Arrange

        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
                ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        DishwasherSpec dishwasher = new DishwasherSpec();
        dishwasher.setAttributeValue("nominal power", 100);
        DishwasherSpec dishwasher2 = new DishwasherSpec();
        dishwasher2.setAttributeValue("nominal power", 100);
        Dishwasher device = new Dishwasher("dish1", dishwasher, new DishwasherType());
        Dishwasher device1 = new Dishwasher("dish2", dishwasher2, new DishwasherType());
        TotalNominalPowerConnectedToHouseGridController ctrll172 = new TotalNominalPowerConnectedToHouseGridController(houseGridRepo,roomHouseGridService);
        //Act
        houseGridRepo.addHouseGrid(houseGrid);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        room.getListOfDevices().addDeviceToList(device);
        room.getListOfDevices().addDeviceToList(device1);

        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        double result = ctrll172.getTotalNominalPowerOfHouseGrid(houseGridDto);
        double expectedResult = 200;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void totalNominalPowerOfHouseGridWithoutDevices() throws Exception {
        //Arrange

        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
                ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        TotalNominalPowerConnectedToHouseGridController ctrll172 = new TotalNominalPowerConnectedToHouseGridController(houseGridRepo,roomHouseGridService);
        //Act
        houseGridRepo.addHouseGrid(houseGrid);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        double result = ctrll172.getTotalNominalPowerOfHouseGrid(houseGridDto);
        double expectedResult = 0;
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getListOfHouseGrids() throws Exception {

        HouseGrid houseGrid = new HouseGrid("Grid 1", 700);
        HouseGrid houseGrid2 = new HouseGrid("Grid 2", 700);
                ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("r1","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        Room room2 = new Room("r2","room", 2, dimension.get(0),dimension.get(1),dimension.get(2));
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room2);
        TotalNominalPowerConnectedToHouseGridController ctrll172 = new TotalNominalPowerConnectedToHouseGridController(houseGridRepo,roomHouseGridService);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(houseGrid.getCode());
        expectedResult.add(houseGrid2.getCode());
        ListOfHouseGridsDto result = ctrll172.getListOfHouseGrids();
        assertEquals(expectedResult.get(1), result.getListOfHouseGridDto().get(1).getCode());
    }

}
