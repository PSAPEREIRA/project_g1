package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;

public class MapperRoomSensor {

    protected MapperRoomSensor() {
        throw new IllegalStateException("Utility class");
    }

    public static RoomSensorDto toDto(RoomSensor roomSensor) {
        RoomSensorDto roomSensorDto = new RoomSensorDto();
        roomSensorDto.setName(roomSensor.getName());
        roomSensorDto.setSensorType(roomSensor.getSensorType());
        roomSensorDto.setIdOfSensor(roomSensor.getIdOfRoomSensor());
        return roomSensorDto;
    }

}
