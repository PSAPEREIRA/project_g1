package pt.ipp.isep.dei.project1.model.device.winecooler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WineCoolerTypeTest {

    @Test
    void getTypeCode() {
        WineCoolerType ehwType = new WineCoolerType();
        String result = ehwType.getTypeCode();
        String expectedResult = "WC";

        assertEquals(expectedResult,result);
    }

    @Test
    void getType() {
        WineCoolerType ehwType = new WineCoolerType();
        String result = ehwType.getType();
        String expectedResult = "Wine cooler";

        assertEquals(expectedResult,result);
    }

    @Test
    void createKettler() {
        WineCoolerType klrType = new WineCoolerType();
        WineCooler wineCooler = klrType.createNewDevice("newWC");
        assertEquals("newWC",wineCooler.getName());
    }

}