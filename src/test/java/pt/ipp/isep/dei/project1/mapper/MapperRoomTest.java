package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertEquals;


class MapperRoomTest {

    @Test
    void testConstructor() {
        try {
            MapperRoom mapperRoom = new MapperRoom();
        } catch (IllegalStateException e) {
            String message = "Utility class";
           assertEquals(message, e.getMessage());

        }

    }

}