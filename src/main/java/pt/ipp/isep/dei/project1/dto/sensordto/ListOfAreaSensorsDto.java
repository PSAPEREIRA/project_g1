package pt.ipp.isep.dei.project1.dto.sensordto;

import lombok.Getter;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;

import java.util.ArrayList;
import java.util.List;

public class ListOfAreaSensorsDto {

    @Getter
    private final List<AreaSensorDto> listOfAreaSensorDto = new ArrayList<>();

    public void setListOfSensorDto (List<AreaSensor> listOfAreaSensor) {
        for (AreaSensor areaSensor : listOfAreaSensor)
            this.listOfAreaSensorDto.add(MapperAreaSensor.toDto(areaSensor));
        }
    }
