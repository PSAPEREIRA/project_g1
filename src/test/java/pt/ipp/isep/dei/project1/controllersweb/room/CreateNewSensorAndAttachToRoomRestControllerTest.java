package pt.ipp.isep.dei.project1.controllersweb.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.mapper.MapperRoomSensor;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.*;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.services.RoomSensorTypeService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CreateNewSensorAndAttachToRoomRestControllerTest {

    @Mock
    private RoomSensorTypeService appService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomDomainService roomDomainService;
    @Mock
    private CreateNewSensorAndAttachToRoomRestController controller;
    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;



    @BeforeEach
    void initMocks() throws Exception {

        roomDomainService = new RoomDomainService(roomRepository);
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
        appService= new RoomSensorTypeService(roomDomainService, sensorTypeDomainService);
        controller = new CreateNewSensorAndAttachToRoomRestController(appService);
    }

    @Test
    void createNewSensorHappyPath() {
        //Arrange
        Room room = new Room("room","bed",1,2,2,2);
        roomDomainService.addRoom(room);
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        //Act
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018, 12, 20);
        String unit = "ºC";
        String idOfSensor = "123";
        RoomDto roomDto = MapperRoom.toDto(room);
        RoomSensor s1 = new RoomSensor(idOfSensor,nameOfSensor,m1,localDate,unit);
        RoomSensorDto roomSensorDto = MapperRoomSensor.toDto(s1);
        ResponseEntity<Object> result = controller.createNewSensor(roomDto.getName(),roomSensorDto);
        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void createNewSensorSadPathRoomNotFound() {
        //Arrange
        Room room = new Room("room","bed",1,2,2,2);
        SensorType m1 = new SensorType("temperature");
        sensorTypeDomainService.add(m1);
        //Act
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018, 12, 20);
        String unit = "ºC";
        String idOfSensor = "123";
        RoomDto roomDto = MapperRoom.toDto(room);
        RoomSensor s1 = new RoomSensor(idOfSensor,nameOfSensor,m1,localDate,unit);
        RoomSensorDto roomSensorDto = MapperRoomSensor.toDto(s1);
        ResponseEntity<Object> result = controller.createNewSensor(roomDto.getName(),roomSensorDto);
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void createNewSensorSadPathSensorTypeNotFound() {
        //Arrange
        Room room = new Room("room","bed",1,2,2,2);
        roomDomainService.addRoom(room);
        SensorType m1 = new SensorType("buga");
        sensorTypeDomainService.add(m1);
        //Act
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018, 12, 20);
        String unit = "ºC";
        SensorType m2 = new SensorType("siga");
        String idOfSensor = "123";
        RoomDto roomDto = MapperRoom.toDto(room);
        RoomSensor s1 = new RoomSensor(idOfSensor,nameOfSensor,m2,localDate,unit);
        RoomSensorDto roomSensorDto = MapperRoomSensor.toDto(s1);
        ResponseEntity<Object> result = controller.createNewSensor(roomDto.getName(),roomSensorDto);
        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void createNewSensorSadPathSensorIdNull() {
        //Arrange
        Room room = new Room("room","bed",1,2,2,2);
        roomDomainService.addRoom(room);
        SensorType m1 = new SensorType("buga");
        sensorTypeDomainService.add(m1);
        //Act
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018, 12, 20);
        String unit = "ºC";
        SensorType m2 = new SensorType("buga");
        String idOfSensor = "123";
        RoomDto roomDto = MapperRoom.toDto(room);
        RoomSensor s1 = new RoomSensor(idOfSensor,nameOfSensor,m2,localDate,unit);
        RoomSensorDto roomSensorDto = MapperRoomSensor.toDto(s1);
        roomSensorDto.setIdOfSensor(null);
        ResponseEntity<Object> result = controller.createNewSensor(roomDto.getName(),roomSensorDto);
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void createNewSensorSadPathSensorIdEmpty() {
        //Arrange
        Room room = new Room("room","bed",1,2,2,2);
        roomDomainService.addRoom(room);
        SensorType m1 = new SensorType("buga");
        sensorTypeDomainService.add(m1);
        //Act
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018, 12, 20);
        String unit = "ºC";
        SensorType m2 = new SensorType("buga");
        String idOfSensor = "123";
        RoomDto roomDto = MapperRoom.toDto(room);
        RoomSensor s1 = new RoomSensor(idOfSensor,nameOfSensor,m2,localDate,unit);
        RoomSensorDto roomSensorDto = MapperRoomSensor.toDto(s1);
        roomSensorDto.setIdOfSensor("");
        ResponseEntity<Object> result = controller.createNewSensor(roomDto.getName(),roomSensorDto);
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

    @Test
    void createNewSensorSadPathImpossibleToAdd() {
        //Arrange
        Room room = new Room("room","bed",1,2,2,2);
        roomDomainService.addRoom(room);
        SensorType m1 = new SensorType("buga");
        sensorTypeDomainService.add(m1);
        //Act
        String nameOfSensor = "sensor1";
        LocalDate localDate = LocalDate.of(2018, 12, 20);
        String unit = "ºC";
        SensorType m2 = new SensorType("buga");
        String idOfSensor = "123";
        RoomDto roomDto = MapperRoom.toDto(room);
        RoomSensor s1 = new RoomSensor(idOfSensor,nameOfSensor,m2,localDate,unit);
        RoomSensorDto roomSensorDto = MapperRoomSensor.toDto(s1);
        roomDomainService.getRoomByName("room").addSensor(s1);
        ResponseEntity<Object> result = controller.createNewSensor(roomDto.getName(),roomSensorDto);
        //Assert
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, result.getStatusCode());
    }
}