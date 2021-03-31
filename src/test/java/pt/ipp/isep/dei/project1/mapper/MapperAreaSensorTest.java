package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

class MapperAreaSensorTest {

    @Test
    void testConstructor() {
        try {
            MapperAreaSensor mapperAreaSensor = new MapperAreaSensor();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}