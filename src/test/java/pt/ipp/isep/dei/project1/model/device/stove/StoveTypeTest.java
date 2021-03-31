package pt.ipp.isep.dei.project1.model.device.stove;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StoveTypeTest {

    @Test
    void getTypeCode() {
        StoveType stoveType = new StoveType();
        String result = stoveType.getTypeCode();
        String expectedResult = "STOVE";

        assertEquals(expectedResult, result);
    }

    @Test
    void getType() {
        StoveType stoveType = new StoveType();
        String result = stoveType.getType();
        String expectedResult = "Stove";

        assertEquals(expectedResult,result);
    }

    @Test
    void createStove() {
        StoveType stoveType = new StoveType();
        Stove stove = stoveType.createNewDevice("NewStove");
        assertEquals("NewStove",stove.getName());
    }
}