package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class HouseFromJsonFile {

    @JsonProperty("adress")
    private AddressFromJsonFile address;
    @JsonProperty("room")
    private List<RoomFromJsonFile> listOfRooms;
    @JsonProperty("grid")
    private List<HouseGridFromJsonFile> listOfHouseGrids;

    public AddressFromJsonFile getAddress() {
        return address;
    }

    public void setAddress(AddressFromJsonFile address) {
        this.address = address;
    }

    public List<RoomFromJsonFile> getListOfRooms() {
        return Collections.unmodifiableList(listOfRooms);
    }

    public void setListOfRooms(List<RoomFromJsonFile> listOfRooms) {
        this.listOfRooms = Collections.unmodifiableList(listOfRooms);
    }

    public List<HouseGridFromJsonFile> getListOfHouseGrids() {
        return Collections.unmodifiableList(listOfHouseGrids);
    }

    public void setListOfHouseGrids(List<HouseGridFromJsonFile> listOfHouseGrids) {
        this.listOfHouseGrids = Collections.unmodifiableList(listOfHouseGrids);
    }
}
