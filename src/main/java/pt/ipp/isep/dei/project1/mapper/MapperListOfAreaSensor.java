package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;

import java.util.List;

public final class MapperListOfAreaSensor {

    protected MapperListOfAreaSensor() {
        throw new IllegalStateException("Utility class");
    }

        public static ListOfAreaSensorsDto toDto(List<AreaSensor> areaSensorList) {
        ListOfAreaSensorsDto listOfAreaSensorsDto = new ListOfAreaSensorsDto();
        listOfAreaSensorsDto.setListOfSensorDto(areaSensorList);
        return listOfAreaSensorsDto;
    }
}
