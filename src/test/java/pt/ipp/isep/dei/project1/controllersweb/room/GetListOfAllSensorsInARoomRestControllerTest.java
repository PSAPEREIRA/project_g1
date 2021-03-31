package pt.ipp.isep.dei.project1.controllersweb.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomRepository;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GetListOfAllSensorsInARoomRestControllerTest {

    @Mock
    private RoomDomainService roomDomainService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private GetListOfAllSensorsInARoomRestController ctrl;

    @BeforeEach
    void initUseCase() throws Exception {
        roomDomainService = new RoomDomainService(roomRepository);
        ctrl = new GetListOfAllSensorsInARoomRestController(roomDomainService);
    }

    @Test
    void getListOfAllSensorsInARoomHappyPath() {
        //ARRANGE
        Room room1 = new Room("room1","bed",1,1,1,1);
        RoomSensor roomSensor1 = new RoomSensor("1", "s1", new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        RoomSensor roomSensor2 = new RoomSensor("2", "s1", new SensorType("hum"), LocalDate.of(2018, 12, 10), "C");
        room1.addSensor(roomSensor1);
        room1.addSensor(roomSensor2);
        roomDomainService.addRoom(room1);
        //ACT
        HttpStatus expectedResult = HttpStatus.OK;
        HttpStatus result = ctrl.getListOfAllSensorsInARoom(room1.getName()).getStatusCode();
        //ASSERT
        assertEquals(expectedResult,result);
    }

    @Test
    void getListOfAllSensorsInARoomSadPath() {
        //ARRANGE
        Room room1 = new Room("room1","bed",1,1,1,1);
        RoomSensor roomSensor1 = new RoomSensor("1", "s1", new SensorType("temp"), LocalDate.of(2018, 12, 10), "C");
        RoomSensor roomSensor2 = new RoomSensor("2", "s1", new SensorType("hum"), LocalDate.of(2018, 12, 10), "C");
        room1.addSensor(roomSensor1);
        room1.addSensor(roomSensor2);
        roomDomainService.addRoom(room1);
        //ACT
        HttpStatus expectedResult = HttpStatus.NOT_FOUND;
        HttpStatus result = ctrl.getListOfAllSensorsInARoom("sss").getStatusCode();
        //ASSERT
        assertEquals(expectedResult,result);
    }

}