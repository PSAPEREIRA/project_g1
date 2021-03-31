package pt.ipp.isep.dei.project1.controllers.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfSensorType;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

@Controller
public class SpecifyCharacteristicsThatSensorsCanReadController {

    private final SensorTypeDomainService sensorTypeDomainService;

    @Autowired
    public SpecifyCharacteristicsThatSensorsCanReadController(SensorTypeDomainService sensorTypeDomainService) {
        this.sensorTypeDomainService = sensorTypeDomainService;
    }

    public boolean specifyTheCharacteristics(String type) {
        SensorType newType = new SensorType(type);
        if (newType.getType()!=null)
            return sensorTypeDomainService.add(newType);
        return false;
    }

    public ListOfSensorTypesDto getListSensorTypes(){
        return MapperListOfSensorType.toDto(sensorTypeDomainService.getListOfSensorsType());
    }

}

