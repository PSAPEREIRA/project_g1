package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.services.RoomHouseGridService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddANewPowerSourceToAHouseGridControllerTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;
    @Mock
    AddANewPowerSourceToAHouseGridController addANewPowerSourceToAHouseGridController;
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
        addANewPowerSourceToAHouseGridController  = new AddANewPowerSourceToAHouseGridController(houseGridRepo);
    }

    @Test
    void getListOfHouseGrid() throws Exception {
        //ARRANGE
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),new GeographicAreaType("city"));
        HouseGrid houseGrid = new HouseGrid("007",100);
        HouseGrid houseGrid2 = new HouseGrid("008",70);
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("room","room",0,dimension.get(0),dimension.get(1),dimension.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        //ACT
        ListOfHouseGridsDto result = addANewPowerSourceToAHouseGridController.getListOfHouseGrids();
        //ASSERT
        assertEquals(houseGrid2.getCode(),result.getListOfHouseGridDto().get(1).getCode());

    }



    @Test
    void addPowerSourceFalse() throws Exception {
        //ARRANGE
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),new GeographicAreaType("city"));
        HouseGrid houseGrid = new HouseGrid("housegrid1", 100);
        houseGridRepo.addHouseGrid(houseGrid);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        addANewPowerSourceToAHouseGridController.addPowerSourceToHouseGrid(houseGridDto, "Rede Eletrica",50);
        //ACT
        boolean result = addANewPowerSourceToAHouseGridController.addPowerSourceToHouseGrid(houseGridDto, "Rede Eletrica",50);
        //ASSERT
        assertFalse(result);
    }


    @Test
    void addPowerSourceToAGrid() throws Exception {
        //ARRANGE
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),new GeographicAreaType("city"));
        HouseGrid houseGrid = new HouseGrid("xpto", 100);
        houseGridRepo.addHouseGrid(houseGrid);
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        //ACT
        boolean result = addANewPowerSourceToAHouseGridController.addPowerSourceToHouseGrid(houseGridDto, "Rede Eletrica", 50);
        //ASSERT
        assertTrue(result);
    }
}
