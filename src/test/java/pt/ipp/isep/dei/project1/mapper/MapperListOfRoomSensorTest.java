package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfRoomSensorTest {

    @Test
    void testConstructor() {
        try {
            MapperListOfRoomSensor mapperListOfRoomSensor = new MapperListOfRoomSensor();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}