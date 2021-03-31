package pt.ipp.isep.dei.project1.model.sensor;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReadingTest {


    @Test
    public void equalsTrueTest() {
        //ARRANGE
        Reading reading = new Reading();
        reading.setDateTime(LocalDateTime.of(2018,12,10,10,10));
        Object obj = new Reading();
        ((Reading) obj).setDateTime(LocalDateTime.of(2018,12,10,10,10));
        //ACT
        boolean result = reading.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        Reading reading = new Reading();
        Object obj = reading;
        boolean result = reading.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        Reading reading = new Reading();
        reading.setDateTime(LocalDateTime.of(2018,12,10,10,10));
        Object obj = new Reading();
        ((Reading) obj).setDateTime(LocalDateTime.of(2018,12,10,20,10));

        //ACT
        boolean result = reading.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        Reading reading = new Reading();
        reading.setDateTime(LocalDateTime.of(2018,12,10,10,10));
        Object obj = new SensorType();
        //ACT
        boolean result = reading.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeturntest() {
        //ARRANGE
        Reading reading = new Reading();
        reading.setDateTime(LocalDateTime.of(2018,12,10,10,10));
        Object obj = new Reading();
        ((Reading) obj).setDateTime(LocalDateTime.of(2018,12,10,20,10));
        //ACT
        int result = reading.getDateTime().getDayOfMonth();
        //ASSERT
        assertEquals(reading.hashCode(), result);
    }


    @Test
    void getDate() {
        //ARRANGE
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime expectedResult = LocalDateTime.of(2018, 12, 21, 12, 30);
        //ACT
        LocalDateTime result = r1.getDateTime();
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testReadingToString() {
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);

        Reading r1 = new Reading(date1,12);
        r1.setUnit("ºC");

        String expectedResult =  "Reading{" +
                "Value: " + r1.getValue() + '\'' + "\n" +
                ", Local Date time: " + r1.getDateTime() + "\n" +
                ", Unit: " + r1.getUnit() + "\n" +
                '}';
        String result = r1.toString();
        assertEquals(expectedResult,result);

    }
}