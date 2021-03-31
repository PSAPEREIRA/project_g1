package pt.ipp.isep.dei.project1.mapper;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;


public final class MapperSensorType {

    protected MapperSensorType()  {
        throw new IllegalStateException("Utility class");
    }

    public static SensorTypeDto toDto(SensorType sensorType) {
        SensorTypeDto sensorTypeDto = new SensorTypeDto();
        sensorTypeDto.setDesignation(sensorType.getType());
        return sensorTypeDto;
    }

}
