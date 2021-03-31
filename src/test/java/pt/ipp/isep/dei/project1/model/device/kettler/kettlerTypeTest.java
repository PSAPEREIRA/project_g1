package pt.ipp.isep.dei.project1.model.device.kettler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class kettlerTypeTest {

    @Test
    void getTypeCode() {
        KettlerType ehwType = new KettlerType();
        String result = ehwType.getTypeCode();
        String expectedResult = "KLR";

        assertEquals(expectedResult,result);
    }

    @Test
    void getType() {
        KettlerType ehwType = new KettlerType();
        String result = ehwType.getType();
        String expectedResult = "Kettler";

        assertEquals(expectedResult,result);
    }

    @Test
    void createKettler() {
        KettlerType klrType = new KettlerType();
        Kettler kettler = klrType.createNewDevice("newKettler");
        assertEquals("newKettler",kettler.getName());
    }

}