package pt.ipp.isep.dei.project1.dto.sensordto;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.mapper.MapperAreaSensor;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.sensor.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AreaSensorDtoTest {

    @Test
    void getIdOfSensor() {
        AreaSensor areaSensor = new AreaSensor("1", "sensor1", new Location(40.7486, -73.9864, 0), new SensorType("temperature"), LocalDate.of(2018, 12, 10), "ºC");
        AreaSensorDto areaSensorDto = MapperAreaSensor.toDto(areaSensor);
        String result = areaSensorDto.getIdOfSensor();
        assertEquals("1", result);
    }

    @Test
    void getLocation() {
        SensorType m1 = new SensorType("temperature");
        AreaSensor areaSensor = new AreaSensor("1", "s1", new Location(10.0001, 20.00001, 0), m1, LocalDate.of(2018, 12, 10), "C");
        AreaSensorDto s = MapperAreaSensor.toDto(areaSensor);
        assertEquals(s.getLocation(), new Location(10.0001, 20.00001, 0));
    }

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        AreaSensorDto s1 = new AreaSensorDto();
        s1.setIdOfSensor("12");
        Object obj = new AreaSensorDto();
        ((AreaSensorDto) obj).setIdOfSensor("12");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTest1() {
        //ARRANGE
        AreaSensorDto s1 = new AreaSensorDto();
        s1.setIdOfSensor("12");
        Object obj = new Object();
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        AreaSensorDto s1 = new AreaSensorDto();
        s1.setIdOfSensor("12");
        Object obj = s1;
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTest() {
        //ARRANGE
        AreaSensorDto s1 = new AreaSensorDto();
        s1.setIdOfSensor("12");
        Object obj = new AreaSensorDto();
        ((AreaSensorDto) obj).setIdOfSensor("15");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        AreaSensorDto s1 = new AreaSensorDto();
        s1.setIdOfSensor("12");
        Object obj = new SensorType("temperature");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeTurnTest() {
        //ARRANGE
        AreaSensorDto s1 = new AreaSensorDto();
        s1.setIdOfSensor("12");
        Object obj = new AreaSensorDto();
        ((AreaSensorDto) obj).setIdOfSensor("12");
        //ACT
        int result = s1.getIdOfSensor().charAt(0);
        //ASSERT
        assertEquals(s1.hashCode(), result);
    }



}
