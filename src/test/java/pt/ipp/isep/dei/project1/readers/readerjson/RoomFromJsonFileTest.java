package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomFromJsonFileTest {

    @Test
    void getId() {
        RoomFromJsonFile roomFromJsonFile = new RoomFromJsonFile();
        roomFromJsonFile.setId("B107");
        String expectedResult = "B107";
        String result = roomFromJsonFile.getId();
        assertEquals(expectedResult, result);
    }

    @Test
    void getDescription() {
        RoomFromJsonFile roomFromJsonFile = new RoomFromJsonFile();
        roomFromJsonFile.setDescription("Classroom");
        String expectedResult = "Classroom";
        String result = roomFromJsonFile.getDescription();
        assertEquals(expectedResult, result);
    }

    @Test
    void getFloor() {
        RoomFromJsonFile roomFromJsonFile = new RoomFromJsonFile();
        roomFromJsonFile.setFloor(1);
        double expectedResult = 1;
        double result = roomFromJsonFile.getFloor();
        assertEquals(expectedResult, result);
    }

    @Test
    void getWidth() {
        RoomFromJsonFile roomFromJsonFile = new RoomFromJsonFile();
        roomFromJsonFile.setWidth(1);
        double expectedResult = 1;
        double result = roomFromJsonFile.getWidth();
        assertEquals(expectedResult, result);
    }

    @Test
    void getLength() {
        RoomFromJsonFile roomFromJsonFile = new RoomFromJsonFile();
        roomFromJsonFile.setLength(1);
        double expectedResult = 1;
        double result = roomFromJsonFile.getLength();
        assertEquals(expectedResult, result);
    }

    @Test
    void getHeight() {
        RoomFromJsonFile roomFromJsonFile = new RoomFromJsonFile();
        roomFromJsonFile.setHeight(1);
        double expectedResult = 1;
        double result = roomFromJsonFile.getHeight();
        assertEquals(expectedResult, result);
    }


}