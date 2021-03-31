package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.HouseGridDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfHouseGridsDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.mapper.MapperHouseGrid;
import pt.ipp.isep.dei.project1.mapper.MapperListOfHouseGrids;
import pt.ipp.isep.dei.project1.mapper.MapperListOfRooms;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HaveListOfExistingRoomsAttachedToHouseGridControllerTest {


        @Mock
        private HouseGridRepository houseGridRepository;
        @Mock
        private HouseGridRepo houseGridRepo;
        @Mock
        HaveListOfExistingRoomsAttachedToHouseGridController haveListOfExistingRoomsAttachedToHouseGridController;
        @Mock
        private RoomHouseGridService roomHouseGridService;
        @Mock
        private RoomRepository roomRepository;
        @Mock
        private RoomDomainService roomDomainService;

        @BeforeEach
        void initUseCase() throws Exception {
            houseGridRepo = new HouseGridRepo(houseGridRepository);
            roomDomainService = new RoomDomainService(roomRepository);
            roomHouseGridService = new RoomHouseGridService(houseGridRepo, roomDomainService);
            haveListOfExistingRoomsAttachedToHouseGridController  = new HaveListOfExistingRoomsAttachedToHouseGridController(houseGridRepo,roomHouseGridService);
        }

    @Test
    void getListOfHouseGrid() throws Exception {
        //ARRANGE
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),new GeographicAreaType("city"));
        HouseGrid houseGrid = new HouseGrid("007",100);
        HouseGrid houseGrid2 = new HouseGrid("008",70);
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("room","kitchen room",0,dimension.get(0),dimension.get(1),dimension.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        //ACT
        ListOfHouseGridsDto result = haveListOfExistingRoomsAttachedToHouseGridController.getListOfHouseGrids();
        ListOfHouseGridsDto expectedResult = MapperListOfHouseGrids.toDto(houseGridRepo.getListOfHouseGrids());

        //ASSERT
        assertEquals(expectedResult.getListOfHouseGridDto().size(),result.getListOfHouseGridDto().size());
    }

    @Test
    void getListOfRoomsAttachedHouseGrid() throws Exception {
        //ARRANGE
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),new GeographicAreaType("city"));
        HouseGrid houseGrid = new HouseGrid("007",100);
        HouseGrid houseGrid2 = new HouseGrid("008",70);
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room("room","kitchen room",0,dimension.get(0),dimension.get(1),dimension.get(2));
        roomHouseGridService.addRoomToHouseGrid(houseGrid,room);
        houseGridRepo.addHouseGrid(houseGrid);
        houseGridRepo.addHouseGrid(houseGrid2);
        roomDomainService.addRoom(room);
        //ACT
        HouseGridDto houseGridDto = MapperHouseGrid.toDto(houseGrid);
        ListOfRoomsDto result = haveListOfExistingRoomsAttachedToHouseGridController.getListOfRoomsAttachedToHouseGrid(houseGridDto);
        ListOfRoomsDto expectedResult = MapperListOfRooms.toDto(roomHouseGridService.getRoomsConnectedToHouseGrid(houseGrid.getRoomsList()));

        //ASSERT
        assertEquals(expectedResult.getRoomDto().size(),result.getRoomDto().size());

    }


}