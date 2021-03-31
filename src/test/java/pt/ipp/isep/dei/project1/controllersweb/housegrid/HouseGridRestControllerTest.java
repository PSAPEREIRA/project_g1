package pt.ipp.isep.dei.project1.controllersweb.housegrid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepo;
import pt.ipp.isep.dei.project1.model.repositories.HouseGridRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HouseGridRestControllerTest {

    @Mock
    private HouseGridRepository houseGridRepository;
    @Mock
    private HouseGridRepo houseGridRepo;

    private HouseGridRestController controller;

    @BeforeEach
    void initUseCase() {
        houseGridRepo = new HouseGridRepo(houseGridRepository);
        controller = new HouseGridRestController(houseGridRepo);
    }

    @Test
    public void testHouseGrid() {
        HouseGrid houseGrid = new HouseGrid("h1");
        houseGridRepo.addHouseGrid(houseGrid);
        ResponseEntity responseEntity = controller.getHouseGrid(houseGrid.getCode());
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    public void testHouseGridWithRooms() {
        HouseGrid houseGrid = new HouseGrid("h1");
        Room room = new Room("r1","bed",1,1,1,1);
        Room room2 = new Room("r2","bed",1,1,1,1);
        List<String> roomList = new ArrayList<>();
        roomList.add(room.getName());
        roomList.add(room2.getName());
        houseGrid.setRoomsList(roomList);
        houseGridRepo.addHouseGrid(houseGrid);
        ResponseEntity responseEntity = controller.getHouseGrid(houseGrid.getCode());
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    public void testHouseGridEmpty() {

        ResponseEntity responseEntity = controller.getHouseGrid("room1");
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.NOT_FOUND;
        assertEquals(expectedResult,result);
    }

    @Test
    public void testHouseGridNotExist() {
        HouseGrid houseGrid = new HouseGrid("h1");
        houseGridRepo.addHouseGrid(houseGrid);
        ResponseEntity responseEntity = controller.getHouseGrid("h2");
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.NOT_FOUND;
        assertEquals(expectedResult,result);
    }

}