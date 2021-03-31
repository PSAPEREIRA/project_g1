package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfHouseGridsTest {

    @Test
    void testConstructor() {
        try {
            MapperListOfHouseGrids mapperListOfHouseGrids = new MapperListOfHouseGrids();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}