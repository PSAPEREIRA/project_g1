package pt.ipp.isep.dei.project1.dto.housedto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class RoomDto extends ResourceSupport {

    private String name;
    private String description;
    private double houseFloor;
    private double height;
    private double width;
    private double length;
    private String houseGrid;


    public RoomDto() {
        this.houseFloor=Double.NaN;
        this.height=Double.NaN;
        this.width=Double.NaN;
        this.length=Double.NaN;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RoomDto))
            return false;
        RoomDto c = (RoomDto) obj;
        return this.name.equals(c.name);
    }

    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }

}
