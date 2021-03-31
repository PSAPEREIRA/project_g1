package pt.ipp.isep.dei.project1.model.sensor;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        Status s1 = new Status();
        s1.setSensorStatus(true);
        s1.setData(LocalDate.of(2018,12,10));
        Object obj = new Status(true, LocalDate.of(2018,12,10));
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTest1() {
        //ARRANGE
        Status s1 = new Status(true, LocalDate.of(2018,12,10));
        Object obj = new Object();
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        Status s1 = new Status(true, LocalDate.of(2018,12,10));
        Object obj = s1;
        boolean result = s1.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsFalseTest() {
        //ARRANGE
        Status s1 = new Status(true, LocalDate.of(2018,12,10));
        Object obj = new Status(false, LocalDate.of(2018,12,15));
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        Status s1 = new Status(true, LocalDate.of(2018,12,10));
        Object obj = new SensorType();
        //ACT
        boolean result = s1.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeturntest() {
        //ARRANGE
        Status s1 = new Status(true, LocalDate.of(2018,12,10));
        Object obj =  new Status(true, LocalDate.of(2018,12,10));
        //ACT
        int result = s1.getData().getDayOfMonth();
        //ASSERT
        assertEquals(s1.hashCode(), result);
    }


}