package pt.ipp.isep.dei.project1.model.device;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import static org.junit.jupiter.api.Assertions.*;

class GeneralDeviceTest {



    @Test
    public void equalsTrueTest() {
        //ARRANGE
        GeneralDevice s1 = new GeneralDevice("d1",new DishwasherSpec(),new DishwasherType());
        Object obj = new GeneralDevice("d1",new DishwasherSpec(),new DishwasherType());
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTest1() {
        //ARRANGE
        GeneralDevice s1 = new GeneralDevice("d1",new DishwasherSpec(),new DishwasherType());
        Object obj = new GeneralDevice("d2",new DishwasherSpec(),new DishwasherType());
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        GeneralDevice s1 = new GeneralDevice("d1",new DishwasherSpec(),new DishwasherType());
        Object obj = s1;
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        GeneralDevice s1 = new GeneralDevice("d1",new DishwasherSpec(),new DishwasherType());
        Object obj = new SensorType("d1");
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeturntest() {
        //ARRANGE
        GeneralDevice s1 = new GeneralDevice("d1",new DishwasherSpec(),new DishwasherType());
        Object obj = new GeneralDevice("d1",new DishwasherSpec(),new DishwasherType());
        //ACT
        int result = s1.getName().charAt(0);
        //ASSERT
        assertEquals(s1.hashCode(), result);
    }


}