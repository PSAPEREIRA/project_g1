package pt.ipp.isep.dei.project1.controllers.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.mapper.MapperRoom;
import pt.ipp.isep.dei.project1.mapper.MapperSensorType;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;
import java.time.LocalDate;

import static org.junit.Assert.*;


@ExtendWith(MockitoExtension.class)
class CreateNewSensorAndAttachToRoomControllerTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private SensorTypeRepository sensorTypeRepository;
    private RoomDomainService roomDomainService;
    private SensorTypeDomainService sensorTypeDomainService;

    private CreateNewSensorAndAttachToRoomController controller;

    @BeforeEach
    void initMocks() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
        controller = new CreateNewSensorAndAttachToRoomController(sensorTypeDomainService, roomDomainService);
    }

    @Test
    void getListOfRooms() {
        //ARRANGE
        Room room = new Room("room","bed",1,2,2,2);
        roomDomainService.addRoom(room);
        //ACT
        ListOfRoomsDto result = controller.getListOfRooms();
        ListOfRoomsDto expectedResult = new ListOfRoomsDto();
        expectedResult.getRoomDto().add(MapperRoom.toDto(room));

        //ASSERT
        assertEquals(result.getRoomDto().get(0).getName(),expectedResult.getRoomDto().get(0).getName());
    }

    @Test
    void getListOfRoomsEmpty() {
        //ACT
        ListOfRoomsDto result = controller.getListOfRooms();
        ListOfRoomsDto expectedResult = new ListOfRoomsDto();

        //ASSERT
        assertEquals(result.getRoomDto(),expectedResult.getRoomDto());

    }

    @Test
    void getListOfSensorTypes() {
        //ARRANGE
        SensorType sensorType = new SensorType("temperature");
        SensorType sensorType2 = new SensorType("rainfall");
        sensorTypeDomainService.add(sensorType);
        sensorTypeDomainService.add(sensorType2);
        //ACT
        ListOfSensorTypesDto result = controller.getListOfSensorsType();
        ListOfSensorTypesDto expectedResult = new ListOfSensorTypesDto();
        expectedResult.getSensorTypeDtos().add(MapperSensorType.toDto(sensorType));
        expectedResult.getSensorTypeDtos().add(MapperSensorType.toDto(sensorType2));
        //ASSERT
        assertEquals(result.getSensorTypeDtos().get(0).getDesignation(),expectedResult.getSensorTypeDtos().get(0).getDesignation());
    }

    @Test
    void getListOfSensorTypes2() {
        //ARRANGE
        SensorType sensorType = new SensorType("temperature");
        SensorType sensorType2 = new SensorType("rainfall");
        sensorTypeDomainService.add(sensorType);
        sensorTypeDomainService.add(sensorType2);
        //ACT
        ListOfSensorTypesDto result = controller.getListOfSensorsType();
        ListOfSensorTypesDto expectedResult = new ListOfSensorTypesDto();
        expectedResult.getSensorTypeDtos().add(MapperSensorType.toDto(sensorType));
        expectedResult.getSensorTypeDtos().add(MapperSensorType.toDto(sensorType2));
        //ASSERT
        assertEquals(result.getSensorTypeDtos().get(1).getDesignation(),expectedResult.getSensorTypeDtos().get(1).getDesignation());
    }

    @Test
    void getListOfSensorTypesEmpty() {
        //ARRANGE
        //ACT
        ListOfSensorTypesDto result = controller.getListOfSensorsType();
        ListOfSensorTypesDto expectedResult = new ListOfSensorTypesDto();
        //ASSERT
        assertEquals(result.getSensorTypeDtos(),expectedResult.getSensorTypeDtos());
    }

    @Test
    void createNewSensor()  {
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
        SensorTypeDto sensorTypeDto = MapperSensorType.toDto(m1);
        boolean result = controller.createNewSensor(idOfSensor,roomDto,nameOfSensor, sensorTypeDto, localDate, unit);
        //Assert
        assertTrue(result);
    }

    @Test
    void createNewSensorRepeated()  {
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
        SensorTypeDto sensorTypeDto = MapperSensorType.toDto(m1);
        controller.createNewSensor(idOfSensor,roomDto,nameOfSensor, sensorTypeDto, localDate, unit);
        boolean result = controller.createNewSensor(idOfSensor,roomDto,nameOfSensor, sensorTypeDto, localDate, unit);
        //Assert
        assertFalse(result);
    }

}
