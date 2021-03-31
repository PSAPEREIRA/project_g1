package pt.ipp.isep.dei.project1.model.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.sensordto.SensorTypeDto;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class SensorTypeDomainServiceTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;

    private SensorTypeDomainService sensorTypeDomainService;

    @BeforeEach
    void initUseCase() {
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
    }

   @Test
    void checkIfNewSensorType(){
        String type = "Pressure";

       SensorType result = sensorTypeDomainService.newSensorType(type);

       result.getType();

       assertEquals(type,result.getType());

   }

    @Test
    void getSensorTypeByNameDTO(){
        String type = "Pressure";

        SensorTypeDto result = sensorTypeDomainService.getSensorTypeByNameDTO("Pressure");

        assertEquals(null,result);

    }

    @Test
    void getSensorTypeByNameDTOTRue(){
        String type = "Pressure";
        SensorType sensorType = new SensorType(type);

        sensorTypeDomainService.add(sensorType);

        SensorTypeDto result = sensorTypeDomainService.getSensorTypeByNameDTO("Pressure");

        assertEquals(sensorType.getType(),result.getDesignation());

    }

    @Test
    void getSensorTypeByNameDTOMoreSensorTypes(){
        String type = "Pressure";
        String type2 = "Temperature";
        String type3 = "Humidity";

        SensorType sensorType = new SensorType(type);
        SensorType sensorType2 = new SensorType(type2);
        SensorType sensorType3 = new SensorType(type3);

        sensorTypeDomainService.add(sensorType);
        sensorTypeDomainService.add(sensorType2);
        sensorTypeDomainService.add(sensorType3);

        SensorTypeDto result = sensorTypeDomainService.getSensorTypeByNameDTO("Humidity");

        assertEquals(sensorType3.getType(),result.getDesignation());

    }



    @Test
    void getListSensorTypeByNameDTO(){
        String type = "Pressure";

        List<SensorTypeDto> result = sensorTypeDomainService.getListOfSensorTypesDTO();

        assertEquals(Collections.emptyList(),result);

    }

    @Test
    void getListSensorTypeByNameDTOTRue(){
        String type = "Pressure";
        SensorType sensorType = new SensorType(type);

        sensorTypeDomainService.add(sensorType);

        List<SensorTypeDto> result  = sensorTypeDomainService.getListOfSensorTypesDTO();

        assertEquals(sensorType.getType(),result.get(0).getDesignation());

    }



}