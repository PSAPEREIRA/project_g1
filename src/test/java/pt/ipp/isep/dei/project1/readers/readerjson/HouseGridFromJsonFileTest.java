package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseGridFromJsonFileTest {

    @Test
    void getName() {
        HouseGridFromJsonFile houseGridFromJsonFile = new HouseGridFromJsonFile();
        houseGridFromJsonFile.setName("B building");
        String expectedResult = "B building";
        String result = houseGridFromJsonFile.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void getListOfRoomsByName() {
        List<String> list = new ArrayList<>();
        list.add("B016");
        list.add("B109");
        HouseGridFromJsonFile houseGridFromJsonFile = new HouseGridFromJsonFile();
        houseGridFromJsonFile.setListOfRoomsByName(list);
        List<String> expectedResult = list;
        List<String> result = houseGridFromJsonFile.getListOfRoomsByName();
        assertEquals(expectedResult, result);
    }

}