package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperAreaSensorTypeTest {

    @Test
    void testConstructor() {
        try {
            MapperSensorType mapperSensorType = new MapperSensorType();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}