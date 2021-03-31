package pt.ipp.isep.dei.project1.model.device.electricoven;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

import static org.junit.jupiter.api.Assertions.*;

class ElectricOvenTypeTest {

    @Test
    void getTypeCode() {
        ElectricOvenType ehwType = new ElectricOvenType();
        String result = ehwType.getTypeCode();
        String expectedResult = "EH";

        assertEquals(expectedResult,result);
    }

    @Test
    void createOvenTypeTest() {
        ElectricOvenType ehwType = new ElectricOvenType();
        Device d =ehwType.createNewDevice("newEH");
        assertEquals("newEH",d.getName());
    }

}