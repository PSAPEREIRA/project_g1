package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfGeographicAreaTypeTest {

    @Test
    void testConstructor() {
        try {
            MapperListOfGeographicAreaType mapperListOfGeographicAreaType = new MapperListOfGeographicAreaType();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}