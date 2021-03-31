package pt.ipp.isep.dei.project1.model.house;

import lombok.Getter;
import lombok.Setter;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class House {

    @Getter @Setter
    @Id
    private String nameOfHouse;
    private Location location;
    @Getter @Setter
    private String geographicAreaID;
    @Getter @Setter
    @Embedded
    private Address address;

    public House() {
    }

    public House(String nameOfHouse, Location locationOfHouse, String geographicAreaID) {
        this.location = locationOfHouse;
        this.nameOfHouse = nameOfHouse;
        this.geographicAreaID = geographicAreaID;
        validateBeforeCreate();
    }

    public House(String nameOfHouse, Location locationOfHouse, String geographicAreaID, Address address){
        this.location = locationOfHouse;
        this.nameOfHouse = nameOfHouse;
        this.geographicAreaID = geographicAreaID;
        this.address = address;
        validateBeforeCreate();
    }

    private void validateBeforeCreate() {
        validateName();
    }

    private void validateName() {
        if (nameOfHouse == null || "".equals(nameOfHouse))
            throw new RuntimeException("Insert a valid name for House");
    }

    public Location getLocationOfHouse() {
        return location;
    }

    public void setLocationOfHouse(Location mLocationOfHouse) {
        this.location = mLocationOfHouse;
    }


}


