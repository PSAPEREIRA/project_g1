package pt.ipp.isep.dei.project1.mapper;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;


class MapperListOfDeviceTest {

    @Test
    void testConstructor() {
        try {
            MapperListOfDevice mapperListOfDevice = new MapperListOfDevice();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}