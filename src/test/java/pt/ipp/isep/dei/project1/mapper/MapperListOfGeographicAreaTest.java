package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfGeographicAreaTest {

    @Test
    void testConstructor() {
        try {
            MapperListOfGeographicArea mapperListOfGeographicArea = new MapperListOfGeographicArea();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}