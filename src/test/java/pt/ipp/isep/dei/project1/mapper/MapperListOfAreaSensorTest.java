package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfAreaSensorTest {

    @Test
    void testConstructor() {
        try {
            MapperListOfAreaSensor mapperListOfAreaSensor = new MapperListOfAreaSensor();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}