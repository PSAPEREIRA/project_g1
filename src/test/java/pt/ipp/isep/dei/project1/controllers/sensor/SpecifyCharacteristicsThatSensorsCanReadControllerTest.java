package pt.ipp.isep.dei.project1.controllers.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfSensorTypesDto;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SpecifyCharacteristicsThatSensorsCanReadControllerTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;

    @BeforeEach
    void initUseCase() {
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
    }

    @Test
    void newSensorType() {
        String type = "temperature";
        sensorTypeDomainService.newSensorType(type);
        SpecifyCharacteristicsThatSensorsCanReadController ctrll5 = new SpecifyCharacteristicsThatSensorsCanReadController(sensorTypeDomainService);
        boolean result = ctrll5.specifyTheCharacteristics(type);
        assertTrue(result);
    }

    @Test
    void newSensorType2() {
        try {
            String type = "";
            sensorTypeDomainService.newSensorType(type);
            SpecifyCharacteristicsThatSensorsCanReadController ctrll5 = new SpecifyCharacteristicsThatSensorsCanReadController(sensorTypeDomainService);
        }catch (RuntimeException e){
            String message = "sensorType exception";
            assertEquals(message, e.getMessage());
            throw e;
        }
    }


    @Test
    void newSensorTypeRepetead() {
        String type = "temperature";
        SpecifyCharacteristicsThatSensorsCanReadController ctrl1 = new SpecifyCharacteristicsThatSensorsCanReadController(sensorTypeDomainService);
        SpecifyCharacteristicsThatSensorsCanReadController ctrl2 = new SpecifyCharacteristicsThatSensorsCanReadController(sensorTypeDomainService);
        sensorTypeDomainService.newSensorType(type);
        ctrl1.specifyTheCharacteristics(type);
        boolean result = ctrl2.specifyTheCharacteristics(type);
        assertFalse(result);
    }

    @Test
    void newSensorTypeNonValid() {
        String type = "temperature";
        SpecifyCharacteristicsThatSensorsCanReadController ctrl2 = new SpecifyCharacteristicsThatSensorsCanReadController(sensorTypeDomainService);
        sensorTypeDomainService.newSensorType(type);
        boolean result = ctrl2.specifyTheCharacteristics(null);
        assertFalse(result);
    }

    @Test
    void newSensorTypeNonValid2() {
        String type = "temperature";
        SensorType sensorType = new SensorType(type);
        sensorTypeDomainService.add(sensorType);
        SpecifyCharacteristicsThatSensorsCanReadController ctrl2 = new SpecifyCharacteristicsThatSensorsCanReadController(sensorTypeDomainService);
        ListOfSensorTypesDto result = ctrl2.getListSensorTypes();
        assertEquals(result.getSensorTypeDtos().get(0).getDesignation(),type);
    }


}