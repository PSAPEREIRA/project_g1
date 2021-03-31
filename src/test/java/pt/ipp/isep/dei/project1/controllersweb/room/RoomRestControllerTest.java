package pt.ipp.isep.dei.project1.controllersweb.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoomRestControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;

    private RoomRestController controller;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        controller = new RoomRestController(roomDomainService, sensorTypeDomainService);
    }

    @Test
    public void testGetRoom() {
        Room room = new Room("room1","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        ResponseEntity responseEntity = controller.getRoom(room.getName());
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetRoomEmpty() {

        ResponseEntity responseEntity = controller.getRoom("room1");
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.NO_CONTENT;
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetRoomNotExist() {
        Room room = new Room("room1","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        ResponseEntity responseEntity = controller.getRoom("room2");
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.NO_CONTENT;
        assertEquals(expectedResult,result);
    }

    @Test
    public void testGetListOfRooms() {
        Room room = new Room("room1","bed",1,1,1,1);
        Room room2 = new Room("room2","bed",1,1,1,1);
        roomDomainService.addRoom(room);
        roomDomainService.addRoom(room2);
        ResponseEntity responseEntity = controller.getListOfRooms();
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }

    @Test
    void getListOfSensorTypes() {
        SensorType sensorType = new SensorType("temperature");
        SensorType sensorType1 = new SensorType("rainfall");
        sensorTypeDomainService.add(sensorType);
        sensorTypeDomainService.add(sensorType1);
        ResponseEntity responseEntity = controller.getListOfSensorTypes();
        HttpStatus result = responseEntity.getStatusCode();
        HttpStatus expectedResult = HttpStatus.OK;
        assertEquals(expectedResult,result);
    }
}