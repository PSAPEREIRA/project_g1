package pt.ipp.isep.dei.project1.controllersweb.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddNewRoomToTheHouseControllerRestTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    AddNewRoomToTheHouseControllerRest ctr1051;


    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        ctr1051 = new AddNewRoomToTheHouseControllerRest(roomDomainService);
    }


    @Test
    void createNameNewRoom()  {
        //ARRANGE
        String nameRoom = "dinnerRoom";
        int houseFloor = 1;
        ArrayList<Double> dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        // ACT
        Room room = new Room(nameRoom,"room", houseFloor, dimension.get(0),dimension.get(1),dimension.get(2));
        RoomDto roomDto = MapperRoom.toDto(room);
        ResponseEntity<Object> result = ctr1051.createNewRoom(roomDto);
        //ASSERT
        assertEquals(HttpStatus.CREATED,result.getStatusCode());
    }


    @Test
    void createNameNewRoomFalse() {
        //ARRANGE
        List<Room> listOfRooms = new ArrayList<>();
        String nameRoom = "Sale";
        int houseFloor = 1;
        ArrayList<Double>dimension = new ArrayList<>();
        dimension.add(3.0);
        dimension.add(2.0);
        dimension.add(3.0);
        Room room = new Room(nameRoom,"room", houseFloor, dimension.get(0),dimension.get(1),dimension.get(2));
        listOfRooms.add(room);
        roomDomainService.addRoom(room);
        // ACT
        RoomDto roomDto = MapperRoom.toDto(room);
        ResponseEntity<Object> result = ctr1051.createNewRoom(roomDto);
        //ASSERT
        assertEquals(HttpStatus.CONFLICT,result.getStatusCode());
    }

}
