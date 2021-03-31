package pt.ipp.isep.dei.project1.model.device.freezer;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

import static org.junit.jupiter.api.Assertions.*;

class FreezerTypeTest {

    @Test
    void testGetType() {
        FreezerType freType = new FreezerType();
        String result = freType.getType();
        String expectedResult = "Freezer";

        assertEquals(expectedResult,result);
    }


    @Test
    void getTypeCode() {
        FreezerType freType = new FreezerType();
        String result = freType.getTypeCode();
        String expectedResult = "FRZR";

        assertEquals(expectedResult,result);
    }

    @Test
    void createNewDevice() {
        FreezerType freType = new FreezerType();
        Device result = freType.createNewDevice("Freezer");
        Device expectedResult = new Freezer("Freezer",new FreezerSpec(),new FreezerType());

        assertEquals(expectedResult.getName(),result.getName());
    }

}