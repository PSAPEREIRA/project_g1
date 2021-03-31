package pt.ipp.isep.dei.project1.dto.housedto;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseGridDtoTest {

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        HouseGridDto hg = new HouseGridDto();
        hg.setCode("xpt01");
        Object obj = new HouseGridDto();
        ((HouseGridDto) obj).setCode("xpt01");
        //ACT
        boolean result = hg.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        HouseGridDto hg = new HouseGridDto();
        hg.setCode("xpt01");
        Object obj = hg;
        boolean result = hg.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        HouseGridDto hg = new HouseGridDto();
        hg.setCode("xpt01");
        Object obj = new HouseGridDto();
        ((HouseGridDto) obj).setCode("xpt02");
        //ACT
        boolean result = hg.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        HouseGridDto hg = new HouseGridDto();
        hg.setCode("xpt01");
        Object obj = new AreaSensor("1","s1", new Location(40.7486, -73.9864, 0), new SensorType("Temperature"), LocalDate.of(2019, 01, 05), "ºC");
        ;
        //ACT
        boolean result = hg.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        //ARRANGE
        HouseGridDto hg = new HouseGridDto();
        hg.setCode("xpt01");
        //ACT
        int result = hg.getCode().charAt(0);
        //ASSERT
        assertEquals(hg.hashCode(), result);
    }

    @Test
    void setAndGetPowerLimiter() {
        //ARRANGE
        HouseGridDto hg = new HouseGridDto();
        //ACT
        hg.setPowerLimiter(30);
        double result = hg.getPowerLimiter();
        double expectedResult = 30;
        //ASSERT
        assertEquals(expectedResult,result);
    }




}