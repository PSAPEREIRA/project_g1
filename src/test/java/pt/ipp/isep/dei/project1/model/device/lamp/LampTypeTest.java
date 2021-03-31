package pt.ipp.isep.dei.project1.model.device.lamp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LampTypeTest {

    @Test
    void getTypeCode() {
        LampType lmpType = new LampType();
        String result = lmpType.getTypeCode();
        String expectedResult = "LAMP";

        assertEquals(expectedResult, result);
    }

    @Test
    void getType() {
        LampType ehwType = new LampType();
        String result = ehwType.getType();
        String expectedResult = "Lamp";

        assertEquals(expectedResult, result);
    }

    @Test
    void createLamp() {
        LampType klrType = new LampType();
        Lamp lamp = klrType.createNewDevice("newLamp");
        assertEquals("newLamp",lamp.getName());
    }

}