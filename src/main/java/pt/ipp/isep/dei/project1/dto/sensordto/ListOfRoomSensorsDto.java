package pt.ipp.isep.dei.project1.dto.sensordto;

import lombok.Getter;
import pt.ipp.isep.dei.project1.mapper.MapperRoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;

import java.util.ArrayList;
import java.util.List;

public class ListOfRoomSensorsDto {

    @Getter
    private final List<RoomSensorDto> listOfRoomSensorDto = new ArrayList<>();

    public void setListOfSensorDto(List<RoomSensor> roomSensorList) {
        for (RoomSensor roomSensor : roomSensorList)
            this.listOfRoomSensorDto.add(MapperRoomSensor.toDto(roomSensor));
    }

}
