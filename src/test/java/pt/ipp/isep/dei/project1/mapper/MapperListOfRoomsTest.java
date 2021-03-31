package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfRoomsTest {


    @Test
    void testConstructor() {
        try {
            MapperListOfRooms mapperListOfRooms = new MapperListOfRooms();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }

}