package pt.ipp.isep.dei.project1.model.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.SensorTypeDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AreaSensorTypeTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;
    @Mock
    private SensorTypeDomainService sensorTypeDomainService;


    @BeforeEach
    void initMocks() {
        sensorTypeDomainService = new SensorTypeDomainService(sensorTypeRepository);
    }

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        SensorType m1 = new SensorType();
        m1.setType("Temperature");
        Object obj = new SensorType();
        ((SensorType) obj).setType("Temperature");
        //ACT
        boolean result = m1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        SensorType m1 = new SensorType();
        m1.setType("Porto");
        Object obj = m1;
        boolean result = m1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        SensorType m1 = new SensorType();
        m1.setType("temp");
        Object obj = new SensorType();
        ((SensorType) obj).setType("temp2");
        //ACT
        boolean result = m1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        SensorType m1 = new SensorType();
        m1.setType("temp");
        Object obj = new AreaSensor();
        //ACT
        boolean result = m1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        //ARRANGE
        SensorType m1 = new SensorType();
        m1.setType("temperature");
        //ACT
        int result = m1.getType().charAt(0);
        //ASSERT
        assertEquals(m1.hashCode(), result);
    }

    @Test
    void testAddTrue() {
        //Arrange
        SensorType m1 = new SensorType("temperature");
        //Act
        boolean result = sensorTypeDomainService.add(m1);
        //Assert
        assertTrue(result);
    }

    @Test
    void testAddFalse() {
        //Arrange
        SensorType m1 = new SensorType("temperature");
        SensorType m2 = new SensorType("temperature");
        //Act
        sensorTypeDomainService.add(m1);
        boolean result = sensorTypeDomainService.add(m2);
        //Assert
        assertFalse(result);
    }

    @Test
    void testGetListOfSensors() {
        //Arrange
        SensorType m1 = new SensorType("temperature");
        SensorType m2 = new SensorType("wind");
        List<SensorType> expectedResult = new ArrayList<>();
        expectedResult.add(m1);
        expectedResult.add(m2);
        //Act
        sensorTypeDomainService.add(m1);
        sensorTypeDomainService.add(m2);
        List<SensorType> result = sensorTypeDomainService.getListOfSensorsType();
        //Assert
        assertEquals(expectedResult, result);
    }

}

