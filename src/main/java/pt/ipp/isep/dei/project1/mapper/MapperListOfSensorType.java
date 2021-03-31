package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import java.util.List;

public final class MapperListOfSensorType {

    protected MapperListOfSensorType()  {
        throw new IllegalStateException("Utility class");
    }

    public static ListOfSensorTypesDto toDto(List<SensorType> sensorTypeList) {
            ListOfSensorTypesDto listOfSensorTypeDto = new ListOfSensorTypesDto();
            listOfSensorTypeDto.setListOfSensorTypesDTO(sensorTypeList);
            return listOfSensorTypeDto;
        }
}
