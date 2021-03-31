package pt.ipp.isep.dei.project1.model.repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfSensorType;
import pt.ipp.isep.dei.project1.mapper.MapperSensorType;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import java.util.Collections;
import java.util.List;


@Service
public class SensorTypeDomainService {

    @Getter
    private final List<SensorType> listOfSensorsType;
    private final SensorTypeRepository sensorTypeRepository;

    @Autowired
    public SensorTypeDomainService(SensorTypeRepository sensorTypeRepository) {
        this.sensorTypeRepository = sensorTypeRepository;
        this.listOfSensorsType = sensorTypeRepository.findAll();
    }

    public SensorType newSensorType(String type) {
        return new SensorType(type);
    }

    public boolean add(SensorType type) {
        if (!listOfSensorsType.contains(type)) {
            listOfSensorsType.add(type);
            sensorTypeRepository.save(type);
            return true;
        } else
            return false;
    }

    public SensorType getSensorTypeByName(String sensorDesignation) {
        for (SensorType sensorType : listOfSensorsType) {
            if (sensorType.getType().equals(sensorDesignation))
                return sensorType;
        }
        return null;
    }

    public SensorTypeDto getSensorTypeByNameDTO(String sensorDesignation) {
        for (SensorType sensorType : listOfSensorsType) {
            if (sensorType.getType().equals(sensorDesignation))
                return MapperSensorType.toDto(sensorType);
        }
        return null;
    }

    public List<SensorTypeDto> getListOfSensorTypesDTO() {
        if (!listOfSensorsType.isEmpty())
            return MapperListOfSensorType.toDto(listOfSensorsType).getSensorTypeDtos();
        return Collections.emptyList();
    }


}
