package pt.ipp.isep.dei.project1.readers.xml;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import pt.ipp.isep.dei.project1.readers.readerjson.LocalDateDeserializer;
import pt.ipp.isep.dei.project1.readers.readerjson.LocationFromFile;

import java.time.LocalDate;

public class SensorFromFile {


    private String id;
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    private String type;
    private String units;
    private LocationFromFile location;
    private String room;
    private boolean onRoom;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonGetter("start_date")
    public LocalDate getStartDate() {
        return startDate;
    }

    public String getType() {
        return type;
    }

    public String getUnits() {
        return units;
    }

    public LocationFromFile getLocation() {
        return location;
    }

    public String getRoom() {
        return room;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setLocation(LocationFromFile location) {
        this.location = location;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isOnRoom() {
        return onRoom;
    }

    public void setOnRoom(boolean onRoom) {
        this.onRoom = onRoom;
    }

    @Override public String toString(){
        return id +"\n"+name +"\n"+ startDate +"\n"+type+"\n"+units+"\n" + location+"\n";
    }
}
