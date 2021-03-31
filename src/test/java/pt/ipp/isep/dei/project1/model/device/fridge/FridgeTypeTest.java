package pt.ipp.isep.dei.project1.model.device.fridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FridgeTypeTest {


    @Test
    void getTypeCode() {
        FridgeType friType = new FridgeType();
        String result = friType.getTypeCode();
        String expectedResult = "FRDG";

        assertEquals(expectedResult,result);
    }
}