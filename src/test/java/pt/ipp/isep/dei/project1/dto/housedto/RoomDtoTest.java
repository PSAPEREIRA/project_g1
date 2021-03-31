package pt.ipp.isep.dei.project1.dto.housedto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDtoTest {

    @Test
    public void equalsTrueTest() {
        //ARRANGE
        RoomDto roomDto = new RoomDto();
        roomDto.setName("porto");
        Object obj = new RoomDto();
        ((RoomDto) obj).setName("porto");
        //ACT
        boolean result = roomDto.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        RoomDto roomDto = new RoomDto();
        roomDto.setName("Porto");
        Object obj = roomDto;
        boolean result = roomDto.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        RoomDto roomDto = new RoomDto();
        roomDto.setName("Porto");
        Object obj = new RoomDto();
        ((RoomDto) obj).setName("kitchen");
        //ACT
        boolean result = roomDto.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        RoomDto roomDto = new RoomDto();
        roomDto.setName("bedroom");
        Object obj = new SensorType("temperature");
        //ACT
        boolean result = roomDto.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void hashCodeTest() {
        //ARRANGE
        RoomDto roomDto = new RoomDto();
        roomDto.setName("bedroom");
        Object obj = new RoomDto();
        ((RoomDto) obj).setName("bedroom");
        //ACT
        int result = roomDto.getName().charAt(0);
        //ASSERT
        Assertions.assertEquals(roomDto.hashCode(), result);
    }

    @Test
    void testGetDescription() {

        //ARRANGE
        RoomDto roomDto = new RoomDto();
        roomDto.setDescription("city");
        //ACT
        String result = roomDto.getDescription();
        //ASSERT
        Assertions.assertEquals("city", result);
    }

}