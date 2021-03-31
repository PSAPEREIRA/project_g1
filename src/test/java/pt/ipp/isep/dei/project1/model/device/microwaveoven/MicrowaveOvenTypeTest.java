package pt.ipp.isep.dei.project1.model.device.microwaveoven;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTypeTest {
    @Test
    void getTypeCode() {
        MicrowaveOvenType moType = new MicrowaveOvenType();
        String result = moType.getTypeCode();
        String expectedResult = "MICROWAVE";

        assertEquals(expectedResult,result);
    }

    @Test
    void getType() {
        MicrowaveOvenType moType = new MicrowaveOvenType();
        String result = moType.getType();
        String expectedResult = "MicrowaveOven";

        assertEquals(expectedResult,result);
    }

    @Test
    void createMicrowaveOvenType() {
        MicrowaveOvenType moType = new MicrowaveOvenType();
        Device d =moType.createNewDevice("newMO");
        assertEquals("newMO",d.getName());
    }

}
