package pt.ipp.isep.dei.project1.dto.geographicareadto;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeographicAreaDtoWebTest {

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        GeographicAreaDtoWeb geo1 = new GeographicAreaDtoWeb();
        geo1.setName("porto");
        Object obj = new GeographicAreaDtoWeb();
        ((GeographicAreaDtoWeb) obj).setName("porto");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        GeographicAreaDtoWeb geo1 = new GeographicAreaDtoWeb();
        geo1.setName("Porto");
        Object obj = geo1;
        boolean result = geo1.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        GeographicAreaDtoWeb geo1 = new GeographicAreaDtoWeb();
        geo1.setName("Porto");
        Object obj = new GeographicAreaDtoWeb();
        ((GeographicAreaDtoWeb) obj).setName("Braga");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        GeographicAreaDtoWeb geo1 = new GeographicAreaDtoWeb();
        geo1.setName("porto");
        Object obj = new SensorType("temperature");
        //ACT
        boolean result = geo1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeTest() {
        //ARRANGE
        GeographicAreaDtoWeb geo1 = new GeographicAreaDtoWeb();
        geo1.setName("Porto");
        Object obj = new GeographicAreaDtoWeb();
        ((GeographicAreaDtoWeb) obj).setName("Porto");
        //ACT
        int result = geo1.getName().charAt(0);
        //ASSERT
        assertEquals(geo1.hashCode(), result);
    }

    @Test
    void testGetDescription() {

        //ARRANGE
        GeographicAreaDtoWeb newAg01 = new GeographicAreaDtoWeb();
        newAg01.setDescription("city");
        //ACT
        String result = newAg01.getDescription();
        //ASSERT
        assertEquals("city", result);
    }


}