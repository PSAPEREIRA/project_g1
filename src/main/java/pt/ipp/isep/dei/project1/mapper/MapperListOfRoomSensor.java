package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.sensordto.ListOfRoomSensorsDto;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import java.util.List;


public class MapperListOfRoomSensor {

    protected MapperListOfRoomSensor() {
        throw new IllegalStateException("Utility class");
    }

    public static ListOfRoomSensorsDto toDto(List<RoomSensor> roomSensors) {
        ListOfRoomSensorsDto listOfAreaSensorsDto = new ListOfRoomSensorsDto();
        listOfAreaSensorsDto.setListOfSensorDto(roomSensors);
        return listOfAreaSensorsDto;
    }

}
