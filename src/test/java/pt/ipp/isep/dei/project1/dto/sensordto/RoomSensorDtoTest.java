package pt.ipp.isep.dei.project1.dto.sensordto;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.mapper.MapperRoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RoomSensorDtoTest {


    @Test
    void getListOfStatus() {
        LocalDate lStatus = LocalDate.of(2018, 12, 30);
        ListOfStatus listOfStatus = new ListOfStatus();
        Status status = new Status(true, lStatus);
        listOfStatus.addStatusToSensor(status);
        RoomSensor roomSensor = new RoomSensor();


        RoomSensorDto roomSensorDto = MapperRoomSensor.toDto(roomSensor);
        roomSensorDto.setListOfStatus(listOfStatus);
        ListOfStatus result = roomSensorDto.getListOfStatus();
        assertEquals(listOfStatus.getStatusList().get(0).isSensorStatus(),result.getStatusList().get(0).isSensorStatus());

    }

    @Test
    void getIdOfSensor() {
        RoomSensor roomSensor = new RoomSensor();
        roomSensor.setIdOfRoomSensor("");

        RoomSensorDto areaSensorDto = MapperRoomSensor.toDto(roomSensor);
        String result = areaSensorDto.getIdOfSensor();


        assertEquals("",result);
    }

    @Test
    void getTypeSensor() {
        RoomSensor roomSensor = new RoomSensor();
        roomSensor.setSensorType(new SensorType("temperature"));

        RoomSensorDto areaSensorDto = MapperRoomSensor.toDto(roomSensor);
        String result = areaSensorDto.getSensorType().getType();

        assertEquals("temperature",result);
    }
}