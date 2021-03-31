package pt.ipp.isep.dei.project1.readers.readerjson;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseFromJsonFileTest {

    @Test
    void getAddress() {
        AddressFromJsonFile addressFromJsonFile = new AddressFromJsonFile();
        HouseFromJsonFile houseFromJsonFile = new HouseFromJsonFile();
        houseFromJsonFile.setAddress(addressFromJsonFile);
        AddressFromJsonFile expecetdResult = addressFromJsonFile;
        AddressFromJsonFile result = houseFromJsonFile.getAddress();
        assertEquals(expecetdResult, result);
    }

    @Test
    void getListOfRooms() {
        RoomFromJsonFile roomFromJsonFile = new RoomFromJsonFile();
        List<RoomFromJsonFile> roomFromJsonFileList = new ArrayList<>();
        roomFromJsonFileList.add(roomFromJsonFile);
        HouseFromJsonFile houseFromJsonFile = new HouseFromJsonFile();
        houseFromJsonFile.setListOfRooms(roomFromJsonFileList);
        List<RoomFromJsonFile> expectedresult = roomFromJsonFileList;
        List<RoomFromJsonFile> result = houseFromJsonFile.getListOfRooms();
        assertEquals(expectedresult, result);

    }

    @Test
    void getListOfHouseGrids() {
        HouseGridFromJsonFile houseGridFromJsonFile = new HouseGridFromJsonFile();
        List<HouseGridFromJsonFile> houseGridFromJsonFileList = new ArrayList<>();
        houseGridFromJsonFileList.add(houseGridFromJsonFile);
        HouseFromJsonFile houseFromJsonFile = new HouseFromJsonFile();
        houseFromJsonFile.setListOfHouseGrids(houseGridFromJsonFileList);
        List<HouseGridFromJsonFile> expectedResult = houseGridFromJsonFileList;
        List<HouseGridFromJsonFile> result = houseFromJsonFile.getListOfHouseGrids();
        assertEquals(expectedResult, result);
    }

}