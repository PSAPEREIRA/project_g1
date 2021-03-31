package pt.ipp.isep.dei.project1.model.device.tv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TvTypeTest {

    @Test
    void getTypeCode() {
        TvType tvType = new TvType();
        String result = tvType.getTypeCode();
        String expectedResult = "Tv";

        assertEquals(expectedResult, result);
    }

    @Test
    void getType() {
        TvType tvType = new TvType();
        String result = tvType.getType();
        String expectedResult = "Tv";

        assertEquals(expectedResult, result);
    }

    @Test
    void createTv() {
        TvType tvType = new TvType();
        Tv tv = tvType.createNewDevice("newTv");
        assertEquals("newTv",tv.getName());
    }
}
