package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfAreaSensorTypeTest {

    @Test
    void testConstructor() {
        try {
            MapperListOfSensorType mapperListOfSensorType = new MapperListOfSensorType();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}