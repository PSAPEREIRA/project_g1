package pt.ipp.isep.dei.project1.model.geographicarea;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@Embeddable
public class OccupationArea {



    @Embedded
    private Location centerLocation;
    private double width;
    private double length;

    protected OccupationArea() {
    }


    public OccupationArea(Location locationCenter, double width, double lenght) {
        this.centerLocation = locationCenter;
        this.width = width;
        this.length = lenght;
        validateBeforeCreate();
    }


    @Override
    public String toString() {
        return "\n{Center point the Area: " + centerLocation + "\n" + " Width: " + width + "\n" + " Length: " + length + "}";
    }


    private void validateBeforeCreate() {
        validateWidth();
        validateLength();
    }

    private void validateLength() {
        if (length <= 0 || Double.isNaN(length))
            throw new RuntimeException("Insert a valid Length");
    }

    private void validateWidth() {
        if (width <= 0 || Double.isNaN(width))
            throw new RuntimeException("Insert a valid Width");
    }


    public boolean occupationAreaLimits(Location location) {
        List<Double> doubleList = new ArrayList<>();
        double maxLatitude = centerLocation.getLatitude() + (width / 110.574);
        double minLatitude = centerLocation.getLatitude() - (width / 110.574);
        double minLongitude = centerLocation.getLongitude() + (111.320 * Math.cos(length));
        doubleList.add(minLongitude);
        double maxLongitude = centerLocation.getLongitude() - (111.320 * Math.cos(length));
        doubleList.add(maxLongitude);
        double minLongitude2 = Collections.min(doubleList);
        double maxLongitude2 = Collections.max(doubleList);

        return (location.getLatitude() >= minLatitude && location.getLatitude() <= maxLatitude
                && location.getLongitude() >= minLongitude2 && location.getLongitude() <= maxLongitude2);
    }
}
