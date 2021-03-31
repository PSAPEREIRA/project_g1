package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperRoomSensorTest {

    @Test
    void testConstructor() {
        try {
            MapperRoomSensor mapperRoomSensor = new MapperRoomSensor();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}