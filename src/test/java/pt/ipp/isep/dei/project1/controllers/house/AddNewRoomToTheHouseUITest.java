package pt.ipp.isep.dei.project1.controllers.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AddNewRoomToTheHouseUITest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    AddNewRoomToTheHouseController ctr1051;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        ctr1051 = new AddNewRoomToTheHouseController(roomDomainService);
    }


    @Test
    void createNameNewRoom() throws Exception {
        //ARRANGE
        String nameRoom = "dinnerRoom";
        int houseFloor = 1;
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        // ACT
        boolean result = ctr1051.createNewRoom("room", nameRoom, houseFloor, dimension);
        //ASSERT
        assertTrue(result);
    }


    @Test
    void createNameNewRoomFalse() throws Exception {
        //ARRANGE
        List<Room> listOfRooms = new ArrayList<>();
        String nameRoom = "dinnerRoom";
        int houseFloor = 1;
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room(nameRoom,"room", houseFloor, dimension.get(0),dimension.get(1),dimension.get(2));
        listOfRooms.add(room);
        // ACT
        ctr1051.createNewRoom(nameRoom,"room", houseFloor, dimension);
        boolean result = ctr1051.createNewRoom(nameRoom,"room", houseFloor, dimension);
        //ASSERT
        assertFalse(result);
    }

}