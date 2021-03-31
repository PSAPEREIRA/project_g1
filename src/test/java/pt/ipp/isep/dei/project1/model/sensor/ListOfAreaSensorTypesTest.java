package pt.ipp.isep.dei.project1.model.sensor;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ListOfAreaSensorTypesTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;


    @BeforeEach
    void initMocks() {
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
    }

    @Test
    public void testGetSensorTypeByName() {
        //ARRANGE
        SensorType s = new SensorType("temperature");
        //ACT
        sensorTypeDomainService.add(s);
        s.setType("temperature");
        SensorType result = sensorTypeDomainService.getSensorTypeByName("temperature");
        SensorType expectedResult = s;
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSensorTypeByIDNotInList() {
        //ARRANGE
        SensorType s = new SensorType("temperature");
        //ACT
        sensorTypeDomainService.add(s);
        s.setType("temperature");
        SensorType result = sensorTypeDomainService.getSensorTypeByName("rainfall");
        //ASSERT
        assertEquals(null, result);
    }

    @Test
    public void testNewSensorTypeNull() {
        //ARRANGE
        SensorType s = new SensorType("");
        //ACT
        sensorTypeDomainService.add(s);
        s.setType("");
        SensorType result = sensorTypeDomainService.getSensorTypeByName("");
        SensorType expectedResult = s;
        //ASSERT
        assertEquals(expectedResult, result);
    }

}