package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;

public final class MapperAreaSensor {

    protected MapperAreaSensor() {
        throw new IllegalStateException("Utility class");
    }

      public static AreaSensorDto toDto(AreaSensor areaSensor) {
        AreaSensorDto areaSensorDto = new AreaSensorDto();
        areaSensorDto.setName(areaSensor.getName());
        areaSensorDto.setLocation(areaSensor.getLocation());
        areaSensorDto.setSensorType(areaSensor.getSensorType());
        areaSensorDto.setIdOfSensor(areaSensor.getIdOfAreaSensor());
        areaSensorDto.setInstallationDate(areaSensor.getInstallationDate());
        areaSensorDto.setUnit(areaSensor.getUnit());
        return areaSensorDto;
    }
}
