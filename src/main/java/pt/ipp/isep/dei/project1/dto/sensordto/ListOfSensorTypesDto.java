package pt.ipp.isep.dei.project1.dto.sensordto;

import lombok.Getter;
import pt.ipp.isep.dei.project1.mapper.MapperSensorType;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.util.ArrayList;
import java.util.List;

public class ListOfSensorTypesDto {

    @Getter
    private final List<SensorTypeDto> sensorTypeDtos = new ArrayList<>();


    public void setListOfSensorTypesDTO  (List<SensorType> mListOfSensorType) {
        for (SensorType sensorType:mListOfSensorType)
            sensorTypeDtos.add(MapperSensorType.toDto(sensorType));
    }



}
