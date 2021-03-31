package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class HouseGridFromJsonFile {

    private String name;
    @JsonProperty("rooms")
    private List<String> listOfRoomsByName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListOfRoomsByName() {
        return Collections.unmodifiableList(listOfRoomsByName);
    }

    public void setListOfRoomsByName(List<String> listOfRoomsByName) {
        this.listOfRoomsByName = Collections.unmodifiableList(listOfRoomsByName);
    }
}
