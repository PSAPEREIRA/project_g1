package pt.ipp.isep.dei.project1.model.device.fan;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

import static org.junit.jupiter.api.Assertions.*;

class FanTypeTest {
    @Test
    void getTypeCode() {
        FanType moType = new FanType();
        String result = moType.getTypeCode();
        String expectedResult = "FAN";

        assertEquals(expectedResult,result);
    }

    @Test
    void getType() {
        FanType moType = new FanType();
        String result = moType.getType();
        String expectedResult = "Fan";

        assertEquals(expectedResult,result);
    }

    @Test
    void createFanTypeTest() {
        FanType moType = new FanType();
        Device d =moType.createNewDevice("newFan");
        assertEquals("newFan",d.getName());
    }

}
