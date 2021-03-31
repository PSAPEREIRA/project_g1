package pt.ipp.isep.dei.project1.controllersweb.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class EditRoomWebControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;

    private EditRoomWebController controller;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        controller = new EditRoomWebController(roomDomainService);
    }

    @Test
    void setNewRoomAttributeName() {
        Room room = new Room("room1","bed",1,1,1,1);
        Room room2 = new Room("room2","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setDescription("new");
        ResponseEntity responseEntity = controller.setNewRoomAttributeName("room1", roomDto);
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    void setNewRoomAttributeName2() {
        Room room = new Room("room1","bed",1,1,1,1);
        Room room2 = new Room("room2","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setHouseFloor(20);
        ResponseEntity responseEntity = controller.setNewRoomAttributeName("room1", roomDto);
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    void setNewRoomAttributeName3() {
        Room room = new Room("room1","bed",1,1,1,1);
        Room room2 = new Room("room2","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setHeight(20);
        ResponseEntity responseEntity = controller.setNewRoomAttributeName("room1", roomDto);
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    void setNewRoomAttributeName4() {
        Room room = new Room("room1","bed",1,1,1,1);
        Room room2 = new Room("room2","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setLength(20);
        ResponseEntity responseEntity = controller.setNewRoomAttributeName("room1", roomDto);
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    void setNewRoomAttributeName5() {
        Room room = new Room("room1","bed",1,1,1,1);
        Room room2 = new Room("room2","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        RoomDto roomDto = new RoomDto();
        roomDto.setWidth(20);
        ResponseEntity responseEntity = controller.setNewRoomAttributeName("room1", roomDto);
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    void setNewRoomAttributeSadPath() {
        RoomDto roomDto = new RoomDto();
        ResponseEntity responseEntity = controller.setNewRoomAttributeName("room1", roomDto);
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.NOT_FOUND;
        assertEquals(expectedResult,result);
    }




}