package pt.ipp.isep.dei.project1.model.device.walltowelheater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterTypeTest {

    @Test
    void getTypeCode() {
        WallTowelHeaterType wthType = new WallTowelHeaterType();
        String result = wthType.getTypeCode();
        String expectedResult = "WATH";

        assertEquals(expectedResult, result);
    }

    @Test
    void getType() {
        WallTowelHeaterType wthType = new WallTowelHeaterType();
        String result = wthType.getType();
        String expectedResult = "WallTowelHeater";

        assertEquals(expectedResult, result);
    }

    @Test
    void createWallTowelHeater() {
        WallTowelHeaterType wthType = new WallTowelHeaterType();
        WallTowelHeater wallTowelHeater = wthType.createNewDevice("newWallTowelHeater");
        assertEquals("newWallTowelHeater",wallTowelHeater.getName());
    }
}